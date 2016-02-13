package com.save.mycity.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.save.mycity.R;
import com.save.mycity.util.MarkerData;

/**
 * Created by harshikesh.kumar on 13/02/16.
 */
public class DetailFragment extends Fragment {

  private static final String MARKER_DATA = "marker_data";

  public static DetailFragment newInstance(MarkerData markerData) {
    DetailFragment fragment = new DetailFragment();
    Bundle bundle = new Bundle();
    bundle.putParcelable(MARKER_DATA,markerData);
    fragment.setArguments(bundle);
    return fragment;
  }

  public DetailFragment() {
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    MarkerData markerData = getArguments().getParcelable(MARKER_DATA);
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view  = inflater.inflate(R.layout.fragment_issue_details, container, false);

    return view;
  }
}
