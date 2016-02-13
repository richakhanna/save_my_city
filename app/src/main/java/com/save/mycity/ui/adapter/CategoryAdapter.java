package com.save.mycity.ui.adapter;

/**
 * Created by richa.khanna on 2/13/16.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.save.mycity.R;

public class CategoryAdapter extends BaseAdapter {

  private Context mContext;
  private LayoutInflater mLayoutInflater;
  private String[] mCategoryList;

  public CategoryAdapter(Context context, String[] categoryList) {
    mContext = context;
    mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    mCategoryList = categoryList;
  }

  public int getCount() {
    return (null != mCategoryList ? mCategoryList.length : 0);
  }

  public Object getItem(int position) {
    return position;
  }

  public long getItemId(int position) {
    return position;
  }

  @Override public View getView(final int position, View convertView, ViewGroup parent) {
    ViewHolder holder;

    if (convertView == null) {
      convertView = mLayoutInflater.inflate(R.layout.category_item_layout, parent, false);
      holder = new ViewHolder();
      holder.tvCategoryName = (TextView) convertView.findViewById(R.id.tv_category_name);
      // SetTag is used to associate an object with a View.
      convertView.setTag(holder);
    } else {
      // Returns the Object Stored in this view.
      holder = (ViewHolder) convertView.getTag();
    }

    String categoryName = mCategoryList[position];
    holder.tvCategoryName.setText(categoryName);
    return convertView;
  }

  private static class ViewHolder {
    private TextView tvCategoryName;
  }
}
