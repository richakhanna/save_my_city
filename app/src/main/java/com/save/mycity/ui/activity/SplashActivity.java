package com.save.mycity.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import com.facebook.appevents.AppEventsLogger;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.save.mycity.R;
import com.save.mycity.util.Constants;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by harshikesh.kumar on 13/02/16.
 */
public class SplashActivity extends Activity {

  private LocationManager mLocationManager;

  // The minimum distance to change Updates in meters
  private static final long LOCATION_REFRESH_DISTANCE = 10; // 10 meters

  // The minimum time between updates in milliseconds
  private static final long LOCATION_REFRESH_TIME = 1000 * 60 * 1; // 1 minute
  private static final double INDIA_LAT = 21.0000;
  private static final double INDIA_LONG = 78.0000;
  private Location mLocation;
  private double latitude;
  private double longitude;
  private FrameLayout frameLayout;
  private boolean isTimerFinished = false;
  private boolean isGetLocation = false;
  Animation bounce;
  private ImageView mLocationimage;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_splash);

    mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
    frameLayout = (FrameLayout) findViewById(R.id.frame);
    mLocationimage = (ImageView) findViewById(R.id.location_img);
    mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
    bounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
    mLocationimage.startAnimation(bounce);
    TimerTask task = new TimerTask() {

      @Override public void run() {
        isTimerFinished = true;
        finishSplashActivity();
      }
    };
    Timer t = new Timer();
    t.schedule(task, 5000);
    mLocation = getCurrentLocation();
    finishSplashActivity();
  }

  private void finishSplashActivity() {

    if (isTimerFinished && isGetLocation) {
      Intent intent = new Intent(this, MainActivity.class);
      if (mLocation != null) {
        //showSnackbar(frameLayout,"true"+ mLocation.getLatitude() + " longitude "+ mLocation.getLongitude() );
        intent.putExtra(Constants.LATITUDE, mLocation.getLatitude());
        intent.putExtra(Constants.LATITUDE, mLocation.getLongitude());
      } else {
        intent.putExtra(Constants.LATITUDE, INDIA_LAT);
        intent.putExtra(Constants.LONGITUDE, INDIA_LONG);
      }
      startActivity(intent);
      finish();
    }
  }

  public Location getCurrentLocation() {
    Location location = null;
    try {
      mLocationManager =
          (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);

      // getting GPS status
      boolean isGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

      // getting network status
      boolean isNetworkEnabled =
          mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

      if (!isGPSEnabled && !isNetworkEnabled) {
        // no network provider is enabled
      } else {
        // First get location from Network Provider
        if (isNetworkEnabled) {
          mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
              LOCATION_REFRESH_TIME, LOCATION_REFRESH_DISTANCE, mLocationListener);
          Log.d("Network", "Network");
          if (mLocationManager != null) {
            location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
              latitude = location.getLatitude();
              longitude = location.getLongitude();
            }
          }
        }
        // if GPS Enabled get lat/long using GPS Services
        if (isGPSEnabled) {
          if (location == null) {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                LOCATION_REFRESH_TIME, LOCATION_REFRESH_DISTANCE, mLocationListener);
            Log.d("GPS Enabled", "GPS Enabled");
            if (mLocationManager != null) {
              location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
              if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
              }
            }
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    isGetLocation = true;
    return location;
  }

  private final LocationListener mLocationListener = new LocationListener() {
    @Override public void onLocationChanged(Location location) {

    }

    @Override public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override public void onProviderEnabled(String provider) {

    }

    @Override public void onProviderDisabled(String provider) {

    }
  };

  @Override protected void onResume() {
    super.onResume();
    // Logs 'install' and 'app activate' App Events.
    AppEventsLogger.activateApp(this);
  }

  @Override protected void onPause() {
    super.onPause();
    // Logs 'app deactivate' App Event.
    AppEventsLogger.deactivateApp(this);
  }

  protected void showSnackbar(View view, String msg) {
    Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
  }
}
