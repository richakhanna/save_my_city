package com.save.mycity.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.save.mycity.R;
import com.save.mycity.ui.adapter.CategoryAdapter;
import java.util.ArrayList;

public class CategoryListFragment extends Fragment {

  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  private String mParam1;
  private String mParam2;
  private ArrayList<String> mCategoryList;
  private ListView mListView;
  private CategoryAdapter mCategoryAdapter;

  private OnFragmentInteractionListener mListener;

  public static CategoryListFragment newInstance(String param1, String param2) {
    CategoryListFragment fragment = new CategoryListFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  public CategoryListFragment() {
    // Required empty public constructor
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_category_list, container, false);
    mListView = (ListView) view.findViewById(R.id.listview_route);
    mCategoryList = new ArrayList<String>();
    mCategoryList.add("Electricity");
    mCategoryList.add("Drainage");
    mCategoryList.add("Filth");
    mCategoryList.add("Hazard");
    mCategoryList.add("Road Lamp");
    mCategoryAdapter = new CategoryAdapter(getActivity(), mCategoryList);
    mListView.setAdapter(mCategoryAdapter);
    return view;
  }

  public void onButtonPressed(Uri uri) {
    if (mListener != null) {
      mListener.onFragmentInteraction(uri);
    }
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnFragmentInteractionListener) {
      mListener = (OnFragmentInteractionListener) context;
    } else {
      throw new RuntimeException(
          context.toString() + " must implement OnFragmentInteractionListener");
    }
  }

  @Override public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  public interface OnFragmentInteractionListener {
    void onFragmentInteraction(Uri uri);
  }
}
