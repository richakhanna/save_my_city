package com.save.mycity.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.save.mycity.R;
import com.save.mycity.util.Constants;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginFragment extends Fragment {

  private static final String LOGTAG = LoginFragment.class.getSimpleName();
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";
  private String mParam1;
  private String mParam2;
  private LoginButton mLoginButton;
  private AccessToken accessToken;
  private Profile mProfile;

  private OnFbLoginListener mListener;
  private CallbackManager callbackManager;
  private SharedPreferences mSharedPreferences;

  public static LoginFragment newInstance(String param1, String param2) {
    LoginFragment fragment = new LoginFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  public LoginFragment() {
    // Required empty public constructor
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
    mSharedPreferences = getContext().getApplicationContext()
        .getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_login, container, false);
    FacebookSdk.sdkInitialize(getContext().getApplicationContext());
    callbackManager = CallbackManager.Factory.create();
    mLoginButton = (LoginButton) view.findViewById(R.id.login_button);
    mLoginButton.setReadPermissions("email");
    // If using in a fragment
    mLoginButton.setFragment(this);

    // If the access token is available already assign it.
    accessToken = AccessToken.getCurrentAccessToken();
    mProfile = Profile.getCurrentProfile();

    if (accessToken != null && mListener != null) {
      Toast.makeText(getActivity(), "Already Logged In", Toast.LENGTH_LONG).show();
      mListener.onLoginSuccess();
    }

    // Callback registration
    mLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
      @Override public void onSuccess(LoginResult loginResult) {
        Toast.makeText(getActivity(), "Fb login success", Toast.LENGTH_LONG).show();
        // App code
        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
            new GraphRequest.GraphJSONObjectCallback() {
              @Override public void onCompleted(JSONObject object, GraphResponse response) {
                // Application code
                try {
                  String email = object.getString("email");
                  String name = object.getString("name");
                  Log.v(LOGTAG, email);
                  Log.v(LOGTAG, name);
                  mSharedPreferences.edit().putString(Constants.USER_EMAIL, email).apply();
                  mSharedPreferences.edit().putString(Constants.USER_NAME, name).apply();

                  mListener.onLoginSuccess();
                } catch (JSONException e) {
                  Log.e(LOGTAG, e.getMessage());
                }
              }
            });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "name,email");
        request.setParameters(parameters);
        request.executeAsync();
      }

      @Override public void onCancel() {
        Toast.makeText(getActivity(), "Fb login cancel", Toast.LENGTH_LONG).show();
        // App code
      }

      @Override public void onError(FacebookException exception) {
        Toast.makeText(getActivity(), "Fb login error", Toast.LENGTH_LONG).show();
        // App code
      }
    });

    return view;
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnFbLoginListener) {
      mListener = (OnFbLoginListener) context;
    } else {
      throw new RuntimeException(context.toString() + " must implement OnFbLoginListener");
    }
  }

  @Override public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  public interface OnFbLoginListener {
    void onLoginSuccess();
  }

  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    callbackManager.onActivityResult(requestCode, resultCode, data);
    Toast.makeText(getActivity(), "onActivityResult", Toast.LENGTH_LONG).show();
  }
}
