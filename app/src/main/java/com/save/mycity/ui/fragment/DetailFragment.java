package com.save.mycity.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.save.mycity.R;
import com.save.mycity.util.MarkerData;

/**
 * Created by harshikesh.kumar on 13/02/16.
 */
public class DetailFragment extends Fragment {

  private static final String MARKER_DATA = "marker_data";
  private MarkerData markerData;

  public static DetailFragment newInstance(MarkerData markerData) {
    DetailFragment fragment = new DetailFragment();
    Bundle bundle = new Bundle();
    bundle.putParcelable(MARKER_DATA, markerData);
    fragment.setArguments(bundle);
    return fragment;
  }

  public DetailFragment() {
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    markerData = getArguments().getParcelable(MARKER_DATA);
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_issue_details, container, false);
    if (markerData != null) {
      ((TextView) view.findViewById(R.id.tvTitle)).setText(
          Html.fromHtml("<u>" + markerData.getTitle() + "</u>"));
      ((TextView) view.findViewById(R.id.tvNoOfLikes)).setText(
          markerData.getLikeCount() + " Times Reported");
      ((TextView) view.findViewById(R.id.tvDiscription)).setText(markerData.getDiscription());
      ((TextView) view.findViewById(R.id.tvDateReported)).setText(markerData.getDateReported());
      ((TextView) view.findViewById(R.id.tvDateResolved)).setText(markerData.isClosed()?markerData.getDateClosed():" - - -  ");
      ((ImageView) view.findViewById(R.id.btnPlusOne)).setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          v.setAlpha(0.6f);
          Toast.makeText(getActivity(),"Issue reported",Toast.LENGTH_LONG).show();
        }
      });
    }
    view.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

      }
    });
    return view;
  }

  public long getIssueId() {
    return markerData.getId();
  }
}
