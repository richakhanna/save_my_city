package com.save.mycity.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Toast;
import com.save.mycity.R;

/**
 * Created by harshikesh.kumar on 13/02/16.
 */
public class CreateIssueActivity extends Activity implements View.OnClickListener{

  private FloatingActionButton mSaveButton;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_category);
    mSaveButton = (FloatingActionButton) findViewById(R.id.save);

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
