package com.save.mycity.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.save.mycity.R;
import com.save.mycity.util.Constants;

public class MyCityMapFragment extends SupportMapFragment
    implements OnMapReadyCallback, View.OnClickListener {

  private static final int DEFAULT_MAP_ZOOM_LEVEL = 15;
  private static final String TAG = MyCityMapFragment.class.getSimpleName();
  private GoogleMap mGoogleMap;
  private double mLatitude;
  private double mLongitude;
  private LatLng mLocation = null;
  private OnReportIncidentClickedListener mListener;
  private FloatingActionButton mBtnReportIncident;

  public static MyCityMapFragment newInstance(double latitude, double longitude) {
    MyCityMapFragment fragment = new MyCityMapFragment();
    Bundle bundle = new Bundle();
    bundle.putDouble(Constants.LATITUDE, latitude);
    bundle.putDouble(Constants.LONGITUDE, longitude);
    fragment.setArguments(bundle);
    return fragment;
  }

  public MyCityMapFragment() {
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mLatitude = getArguments().getDouble(Constants.LATITUDE);
    mLongitude = getArguments().getDouble(Constants.LONGITUDE);
    mLocation = new LatLng(mLatitude, mLongitude);
    Log.d(TAG, "location" + mLatitude + " " + mLongitude);
    moveToLocation(mLocation);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View mapView = super.onCreateView(inflater, container, savedInstanceState);
    View view = inflater.inflate(R.layout.fragment_my_city_map, container, false);
    ((FrameLayout) view.findViewById(R.id.map_container)).addView(mapView, 0);
    getMapAsync(this);
    mBtnReportIncident = (FloatingActionButton) view.findViewById(R.id.fab);
    mBtnReportIncident.setOnClickListener(this);
    return view;
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnReportIncidentClickedListener) {
      mListener = (OnReportIncidentClickedListener) context;
    } else {
      throw new RuntimeException(
          context.toString() + " must implement OnReportIncidentClickedListener");
    }
  }

  @Override public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  @Override public void onMapReady(GoogleMap googleMap) {
    mGoogleMap = googleMap;
    mGoogleMap.setMyLocationEnabled(true);
    if (mLocation != null) {
      CameraPosition newCameraPosition =
          new CameraPosition.Builder().target(mLocation).zoom(DEFAULT_MAP_ZOOM_LEVEL).build();
      mGoogleMap.moveCamera(CameraUpdateFactory.newCameraPosition(newCameraPosition));
    }
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.fab:
        Toast.makeText(getActivity(), "Report incident button pressed", Toast.LENGTH_LONG).show();
        if (mListener != null) {
          mListener.openReportScreenIfAlreadyLoggedIn();
        }
        break;
    }
  }

  public interface OnReportIncidentClickedListener {

    void openReportScreenIfAlreadyLoggedIn();
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
