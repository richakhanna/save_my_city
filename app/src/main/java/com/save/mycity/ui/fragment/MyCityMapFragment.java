package com.save.mycity.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.save.mycity.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyCityMapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyCityMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyCityMapFragment extends SupportMapFragment implements OnMapReadyCallback {

  private OnFragmentInteractionListener mListener;

  public static MyCityMapFragment newInstance() {
    MyCityMapFragment fragment = new MyCityMapFragment();
    return fragment;
  }

  public MyCityMapFragment() {
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
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
}
