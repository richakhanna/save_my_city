package com.save.mycity.ui.fragment;

/**
 * Created by harshikesh.kumar on 14/02/16.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.save.mycity.R;
import com.save.mycity.ui.adapter.PartnerListAdapter;

public class PartnerListFragment extends Fragment {

  private String[] mCategoryList;
  private ListView mListView;
  private Context mContext;
  private PartnerListAdapter mPartnerAdapter;

  public static PartnerListFragment newInstance() {
    PartnerListFragment fragment = new PartnerListFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  public PartnerListFragment() {
    // Required empty public constructor
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
     mCategoryList = getResources().getStringArray(R.array.ngo_list);
    if (getArguments() != null) {
    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_category_list, container, false);
    mListView = (ListView) view.findViewById(R.id.listview_route);
    mListView.setOnItemClickListener(mItemClickListener);
    mPartnerAdapter = new PartnerListAdapter(getActivity(), mCategoryList);
    mListView.setAdapter(mPartnerAdapter);
    return view;
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    mContext = context;
  }

  @Override public void onDetach() {
    super.onDetach();
  }

  AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
  };

}
