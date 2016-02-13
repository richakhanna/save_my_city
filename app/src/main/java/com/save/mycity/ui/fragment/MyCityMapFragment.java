package com.save.mycity.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.save.mycity.R;
import com.save.mycity.util.Constants;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyCityMapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyCityMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyCityMapFragment extends SupportMapFragment implements OnMapReadyCallback {

  private static final int DEFAULT_MAP_ZOOM_LEVEL = 15;
  private static final String TAG = MyCityMapFragment.class.getSimpleName();
  private OnFragmentInteractionListener mListener;
  private GoogleMap mGoogleMap;
  private double mLatitude;
  private double mLongitude;
  private LatLng mLocation = null;

  public static MyCityMapFragment newInstance(double latitude ,double longitude) {
    MyCityMapFragment fragment = new MyCityMapFragment();
    Bundle bundle = new Bundle();
    bundle.putDouble(Constants.LATITUDE,latitude);
    bundle.putDouble(Constants.LONGITUDE,longitude);
    fragment.setArguments(bundle);
    return fragment;
  }

  public MyCityMapFragment() {
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mLatitude = getArguments().getDouble(Constants.LATITUDE);
    mLongitude = getArguments().getDouble(Constants.LONGITUDE);
    mLocation = new LatLng(mLatitude,mLongitude);
    Log.d(TAG, "location" + mLatitude + " " + mLongitude);
    moveToLocation(mLocation);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View mapView = super.onCreateView(inflater, container, savedInstanceState);
    View view = inflater.inflate(R.layout.fragment_my_city_map, container, false);
    ((FrameLayout) view.findViewById(R.id.map_container)).addView(mapView, 0);
    getMapAsync(this);
    return view;
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
  }

  @Override public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  @Override public void onMapReady(GoogleMap googleMap) {
    mGoogleMap = googleMap;
    mGoogleMap.setMyLocationEnabled(true);
    if(mLocation != null) {
      CameraPosition newCameraPosition =
          new CameraPosition.Builder().target(mLocation).zoom(DEFAULT_MAP_ZOOM_LEVEL).build();
      mGoogleMap.moveCamera(CameraUpdateFactory.newCameraPosition(newCameraPosition));
    }
  }

  /**
   * This interface must be implemented by activities that contain this
   * fragment to allow an interaction in this fragment to be communicated
   * to the activity and potentially other fragments contained in that
   * activity.
   * <p/>
   * See the Android Training lesson <a href=
   * "http://developer.android.com/training/basics/fragments/communicating.html"
   * >Communicating with Other Fragments</a> for more information.
   */
  public interface OnFragmentInteractionListener {
  }

  public boolean moveToLocation(LatLng location) {
    if (mGoogleMap != null) {
      CameraPosition newCameraPosition =
          new CameraPosition.Builder().target(location).zoom(DEFAULT_MAP_ZOOM_LEVEL).build();
      mGoogleMap.moveCamera(CameraUpdateFactory.newCameraPosition(newCameraPosition));
      return true;
    }
    return false;
  }

  public boolean animateToLocation(LatLng location) {
    if (mGoogleMap != null) {
      CameraPosition newCameraPosition =
          new CameraPosition.Builder().target(location).zoom(DEFAULT_MAP_ZOOM_LEVEL).build();
      mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(newCameraPosition));
      return true;
    }
    return false;
  }
}
