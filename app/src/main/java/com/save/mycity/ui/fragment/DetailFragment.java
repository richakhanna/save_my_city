package com.save.mycity.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.save.mycity.R;

/**
 * Created by harshikesh.kumar on 13/02/16.
 */
public class DetailFragment extends Fragment {


  public static DetailFragment newInstance() {
    DetailFragment fragment = new DetailFragment();
    Bundle bundle = new Bundle();
    fragment.setArguments(bundle);
    return fragment;
  }

  public DetailFragment() {
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view  = inflater.inflate(R.layout.fragment_detail, container, false);

    return view;
  }
}
