package com.save.mycity.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.gms.maps.model.LatLng;
import com.save.mycity.R;
import com.save.mycity.ui.fragment.CategoryListFragment;
import com.save.mycity.ui.fragment.LoginFragment;
import com.save.mycity.ui.fragment.MyCityMapFragment;
import com.save.mycity.ui.fragment.PartnerListFragment;
import com.save.mycity.util.Constants;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener,
    MyCityMapFragment.OnReportIncidentClickedListener, LoginFragment.OnFbLoginListener,
    CategoryListFragment.OnFragmentInteractionListener {

  private DrawerLayout drawer;
  private FragmentManager fragmentManager;
  private LatLng mLocation;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

    ActionBarDrawerToggle toggle =
        new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
    fragmentManager = getSupportFragmentManager();
    // load map Fragment Default
    double lat = getIntent().getDoubleExtra(Constants.LATITUDE, 0.0);
    double lon = getIntent().getDoubleExtra(Constants.LONGITUDE, 0.0);
    mLocation = new LatLng(lat, lon);
    fragmentManager.beginTransaction()
        .replace(R.id.main_fragment_container,
            MyCityMapFragment.newInstance(getIntent().getDoubleExtra(Constants.LATITUDE, 0.0),
                getIntent().getDoubleExtra(Constants.LONGITUDE, 0.0)))
        .commit();
  }

  @Override public void onBackPressed() {
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody") @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();
    Fragment fragment = fragmentManager.findFragmentById(R.id.main_fragment_container);

    if (id == R.id.nav_report) {
      if (fragment instanceof MyCityMapFragment) {
        fragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container,
                MyCityMapFragment.newInstance(mLocation.latitude, mLocation.longitude))
            .commit();
      }
    } else if (id == R.id.nav_gallery) {

    } else if (id == R.id.nav_slideshow) {

    } else if (id == R.id.nav_manage) {
      fragmentManager.beginTransaction()
          .replace(R.id.main_fragment_container, PartnerListFragment.newInstance()).addToBackStack("TAG")
          .commit();
    } else if (id == R.id.nav_share) {

    } else if (id == R.id.nav_send) {

    }

    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  @Override public void openLoginFragment() {
    //replace the MyCityMap Fragment with Login fragment
    fragmentManager.beginTransaction()
        .replace(R.id.main_fragment_container, LoginFragment.newInstance("hello", "world"))
        .addToBackStack(null)
        .commit();

  }

  @Override public void onLoginSuccess() {
    //replace the Login Fragment with CategoryList fragment
    fragmentManager.popBackStack();
    fragmentManager.beginTransaction()
        .replace(R.id.main_fragment_container, CategoryListFragment.newInstance("hello", "world"))
        .addToBackStack(null)
        .commit();
  }

  @Override public void onFragmentInteraction(Uri uri) {

  }
}
