package com.save.mycity.ui.adapter;

/**
 * Created by harshikesh.kumar on 14/02/16.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.save.mycity.R;

public class PartnerListAdapter extends BaseAdapter {

  private Context mContext;
  private LayoutInflater mLayoutInflater;
  private String[] mPartnerList;
  private int[] mPartnerList_image = {
      R.drawable.helpage, R.drawable.pratham, R.drawable.careimage, R.drawable.cryimage, R.drawable.goonj ,R.drawable.plan,
      R.drawable.smile
  };

  public PartnerListAdapter(Context context, String[] categoryList) {
    mContext = context;
    mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    mPartnerList = categoryList;
  }

  public int getCount() {
    return (null != mPartnerList ? mPartnerList.length : 0);
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
      convertView = mLayoutInflater.inflate(R.layout.item_list_partner, parent, false);
      holder = new ViewHolder();
      holder.tvName = (TextView) convertView.findViewById(R.id.title_partner);
      holder.tvRating = (TextView) convertView.findViewById(R.id.rating_text_view);
      holder.tvDescription = (TextView) convertView.findViewById(R.id.title_detail);
      holder.tvissues = (TextView) convertView.findViewById(R.id.issues_text_view);
      holder.imageView = (ImageView) convertView.findViewById(R.id.img_grid);
      // SetTag is used to associate an object with a View.
      convertView.setTag(holder);
    } else {
      // Returns the Object Stored in this view.
      holder = (ViewHolder) convertView.getTag();
    }

    int issues = 100 + (int) (Math.random() * ((100) + 1));
    int rating = 1 + (int) (Math.random() * ((6) + 1));
    String categoryName = mPartnerList[position];
    holder.tvissues.setText(issues + " issues resolved.");
    holder.tvName.setText(categoryName);
    holder.imageView.setBackgroundResource(mPartnerList_image[position]);
    //holder.tvRating.setText(rating);
    return convertView;
  }

  private static class ViewHolder {
    private ImageView imageView;
    private TextView tvName;
    private TextView tvRating;
    private TextView tvDescription;
    private TextView tvissues;
  }
}
