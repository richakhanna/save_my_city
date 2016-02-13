package com.save.mycity.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.save.mycity.R;
import com.save.mycity.util.Constants;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by harshikesh.kumar on 13/02/16.
 */
public class CreateIssueActivity extends Activity implements View.OnClickListener{

  private FloatingActionButton mSaveButton;
  private TextView mName;
  private EditText mEditText;
  private TextView mAddress;
  private ImageView mCamImage;
  private TextView mDate;
  private SharedPreferences mSharedpreferences;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_category);
    mSharedpreferences =getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE);
    mSaveButton = (FloatingActionButton) findViewById(R.id.save);
    mName = (TextView) findViewById(R.id.name);
    mAddress = (TextView)findViewById(R.id.address);
    mDate = (TextView)findViewById(R.id.date);
    mEditText = (EditText)findViewById(R.id.edit_text);
    mCamImage = (ImageView) findViewById(R.id.camera_img);

    setCurrentLocation();
    mDate.setText(getCurrentDate());
  }

  private void setCurrentLocation() {
    String address = mSharedpreferences.getString(Constants.ADDRESS,"India");
    mAddress.setText(address);
  }

  private String getCurrentDate() {
    Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
    String date = df.format(c.getTime());
    return date;
  }

  @Override public void onClick(View v) {

    switch (v.getId())
    {
      case R.id.save:
        Toast.makeText(getApplicationContext()," You will be heard! ", Toast.LENGTH_SHORT);
        finish();
        break;
    }
  }
}
