package com.example.vadym.moviedirectoryapp.Activities;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vadym.moviedirectoryapp.Model.Movie;
import com.example.vadym.moviedirectoryapp.Model.MovieApi;
import com.example.vadym.moviedirectoryapp.Model.MovieDetail;
import com.example.vadym.moviedirectoryapp.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDetailActivity extends AppCompatActivity {

    @Nullable private Movie movie;
    private TextView movieTitle;
    private ImageView movieImage;
    private TextView movieYear;
    private TextView director;
    private TextView actors;
    private TextView category;
    private TextView rating;
    private TextView writers;
    private TextView plot;
    private TextView boxOffice;
    private TextView runtime;

    private String movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Bundle bundle = getIntent().getExtras();

        if(bundle!=null) {
            movie = (Movie) bundle.getSerializable("movie");
            movieId = movie.getImdbId();
        }

        setUpUI();
        getMovieDetailsRetrofit(movieId);
    }



    private void getMovieDetailsRetrofit(String id){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieApi api = retrofit.create(MovieApi.class);

        Call<MovieDetail> detailCall = api.getMovieDetail(id);
        detailCall.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, retrofit2.Response<MovieDetail> response) {
                Log.d("TAG",response.code()+"");
                MovieDetail detail = response.body();

                movieTitle.setText(detail.getTitle());
                movieYear.setText("Released: " + detail.getDvdRelease());
                director.setText("Director: " + detail.getDirector());
                writers.setText("Writers: " + detail.getWriter());
                plot.setText("Plot: " + detail.getPlot());
                runtime.setText("Runtime: " + detail.getRunTime());
                actors.setText("Actors: " + detail.getActors());
                boxOffice.setText("Box Office: " + detail.getBoxOffice());

                Picasso.with(getApplicationContext())
                        .load(detail.getPoster())
                        .placeholder(android.R.drawable.ic_btn_speak_now)
                        .into(movieImage);
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                Log.d("TAG", t.getMessage());
            }
        });
    }


    private void setUpUI(){

        movieTitle = (TextView) findViewById(R.id.movieTitleIDDet);
        movieImage =(ImageView) findViewById(R.id.movieImageIDDet);
        movieYear = (TextView) findViewById(R.id.movieReleaseIDDet);
        director = (TextView) findViewById(R.id.directedByDet);
        category = (TextView) findViewById(R.id.movieCatIDDet);
        rating = (TextView) findViewById(R.id.movieRatingIDDet);
        writers = (TextView) findViewById(R.id.writresDet);
        plot = (TextView) findViewById(R.id.plotDet);
        boxOffice = (TextView) findViewById(R.id.boxOfficeDet);
        runtime = (TextView) findViewById(R.id.runtimeDet);
        actors = (TextView) findViewById(R.id.actorsDet);
    }
}
