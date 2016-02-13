package com.save.mycity.network;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by harshikesh.kumar on 20/11/15.
 */
public class ApiManager {

  private static ApiManager mApiManager;

  private ApiManager() {
  }

  public static ApiManager getInstance() {
    if (mApiManager == null) {
      mApiManager = new ApiManager();
    }
    return mApiManager;
  }

  public final static String API_BASE_ENDPOINT = "https://api.themoviedb.org/3";

  private RestAdapter mRestAdapter;

  public RestAdapter getRestAdapter() {
    if (mRestAdapter == null) {
      RestAdapter.Builder builder = new RestAdapter.Builder();

      builder.setEndpoint(API_BASE_ENDPOINT);
      builder.setConverter(new GsonConverter(ApiHelper.getGsonBuilder().create()));
      builder.setRequestInterceptor(new RequestInterceptor() {
        public void intercept(RequestFacade requestFacade) {
        }
      });
      mRestAdapter = builder.build();
    }

    return mRestAdapter;
  }
}
