package com.save.mycity.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.save.mycity.R;
import com.save.mycity.ui.activity.CreateIssueFragment;
import com.save.mycity.ui.adapter.CategoryAdapter;

public class CategoryListFragment extends Fragment {

  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  private String mParam1;
  private String mParam2;
  private String[] mCategoryList;
  private ListView mListView;
  private CategoryAdapter mCategoryAdapter;

  private OnFragmentInteractionListener mListener;
  private Context mContext;

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
     mCategoryList = getResources().getStringArray(R.array.category_list);
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
    mListView.setOnItemClickListener(mItemClickListener);
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
    mContext = context;
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

  AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      //Intent intent = new Intent(mContext, CreateIssueActivity.class);
      //intent.putExtra("category_tag", mCategoryList[position]);
      //startActivity(intent);
      FragmentManager fragmentManager = getFragmentManager();
      fragmentManager.beginTransaction()
          .replace(R.id.main_fragment_container, CreateIssueFragment.newInstance())
          .addToBackStack(null)
          .commit();
    }
  };

  public interface OnFragmentInteractionListener {
    void onFragmentInteraction(Uri uri);
  }
}
