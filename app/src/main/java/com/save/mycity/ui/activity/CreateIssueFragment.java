package com.save.mycity.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.save.mycity.R;
import com.save.mycity.util.Constants;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by harshikesh.kumar on 14/02/16.
 */
public class CreateIssueFragment extends Fragment implements View.OnClickListener {

  private Context mContext;
  private SharedPreferences mSharedPreferences;
  private FloatingActionButton mSaveButton;
  private TextView mName;
  private TextView mAddress;
  private ImageView mCamImage;
  private EditText mEditText;
  private TextView mDate;
  private ImageView mImageView;
  static final int REQUEST_IMAGE_CAPTURE = 1;

  public static CreateIssueFragment newInstance() {
    CreateIssueFragment fragment = new CreateIssueFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  public CreateIssueFragment() {
    // Required empty public constructor
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    mContext = context;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
    }
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_create_issue, container, false);
    mSharedPreferences =
        mContext.getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE);
    mSaveButton = (FloatingActionButton) view.findViewById(R.id.save);
    mSaveButton.setOnClickListener(this);
    mName = (TextView) view.findViewById(R.id.name);
    mAddress = (TextView) view.findViewById(R.id.address);
    mDate = (TextView) view.findViewById(R.id.date);
    mEditText = (EditText) view.findViewById(R.id.edit_text);
    mCamImage = (ImageView) view.findViewById(R.id.camera_img);
    mCamImage.setOnClickListener(this);

    setCurrentLocation();
    mDate.setText(getCurrentDate());
    mImageView = (ImageView) view.findViewById(R.id.iv_thumbnail);
    return view;
  }

  private void dispatchTakePictureIntent() {
    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
      startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }
  }

  private void setCurrentLocation() {
    String address = mSharedPreferences.getString(Constants.ADDRESS, "India");
    String userEmail = mSharedPreferences.getString(Constants.USER_EMAIL, "");
    String userName = mSharedPreferences.getString(Constants.USER_NAME, "");
    mAddress.setText(address);
  }

  private String getCurrentDate() {
    Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
    String date = df.format(c.getTime());
    return date;
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.save:
        Toast.makeText(mContext, " You will be heard! ", Toast.LENGTH_SHORT).show();
        break;
      case R.id.camera_img:
        dispatchTakePictureIntent();
        break;
    }
  }

  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
      Bundle extras = data.getExtras();
      Bitmap imageBitmap = (Bitmap) extras.get("data");
      mImageView.setImageBitmap(imageBitmap);
      mImageView.setVisibility(View.VISIBLE);
    }
  }
}
