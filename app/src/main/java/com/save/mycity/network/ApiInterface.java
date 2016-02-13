package com.save.mycity.network;

import com.save.mycity.model.SampleModel;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by harshikesh.kumar on 13/02/15.
 */

//mApiManager = ApiManager.getInstance();
//    apiInterface = mApiManager.getRestAdapter().create(ApiInterface.class);
//    iMovieInterface.videos(mPage, Language.LANGUAGE_EN.toString(), new CallBack<SampleModel>);

public interface ApiInterface {

  @GET("/movie/{id}/videos") void videos(@Path("id") String tmdbId,
      @Query("language") String language, Callback<SampleModel> videosCallback);
}
