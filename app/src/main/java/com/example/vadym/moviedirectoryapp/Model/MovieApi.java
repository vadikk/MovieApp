package com.example.vadym.moviedirectoryapp.Model;

import com.example.vadym.moviedirectoryapp.Constant.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Vadym on 21.01.2018.
 */

public interface MovieApi {

    @GET("?apikey=6239d2e1")
    Call<MovieRetrofit> getMovie(@Query("s") String name, @Query("page") int number);

    @GET("?apikey=6239d2e1")
    Call<MovieDetail> getMovieDetail(@Query("i") String id);
}
