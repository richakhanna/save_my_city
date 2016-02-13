package com.save.mycity.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.save.mycity.R;
import com.save.mycity.util.Constants;

public class LoginFragment extends Fragment {

  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";
  private String mParam1;
  private String mParam2;
  private LoginButton mLoginButton;
  SharedPreferences sharedpreferences;

  private OnFragmentInteractionListener mListener;
  private CallbackManager callbackManager;

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
    sharedpreferences = getActivity().getSharedPreferences(Constants.SHARED_PREF,
        Context.MODE_PRIVATE);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_login, container, false);
    FacebookSdk.sdkInitialize(getContext().getApplicationContext());
    callbackManager = CallbackManager.Factory.create();
    mLoginButton = (LoginButton) view.findViewById(R.id.login_button);
    mLoginButton.setReadPermissions("user_friends");
    // If using in a fragment
    mLoginButton.setFragment(this);

    // Callback registration
    mLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
      @Override public void onSuccess(LoginResult loginResult) {
        Toast.makeText(getActivity(), "Fb login success", Toast.LENGTH_LONG).show();
        Log.v("LoginFragment", "Fb login success");
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(Constants.PREF_LOGIN,true);
        editor.commit();
        // App code
      }

      @Override public void onCancel() {
        Toast.makeText(getActivity(), "Fb login cancel", Toast.LENGTH_LONG).show();
        Log.v("LoginFragment", "Fb login cancel");
        // App code
      }

      @Override public void onError(FacebookException exception) {
        Toast.makeText(getActivity(), "Fb login error", Toast.LENGTH_LONG).show();
        Log.v("LoginFragment", "Fb login error");
        // App code
      }
    });
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

  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    callbackManager.onActivityResult(requestCode, resultCode, data);
    Toast.makeText(getActivity(), "onActivityResult", Toast.LENGTH_LONG).show();
    Log.v("LoginFragment", "onActivityResult");
  }
}
