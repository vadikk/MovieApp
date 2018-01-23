package com.example.vadym.moviedirectoryapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.vadym.moviedirectoryapp.Constant.Constants;
import com.example.vadym.moviedirectoryapp.Constant.Prefs;
import com.example.vadym.moviedirectoryapp.Data.MovieRecyclerViewAdapter;
import com.example.vadym.moviedirectoryapp.Model.Movie;
import com.example.vadym.moviedirectoryapp.Model.MovieApi;
import com.example.vadym.moviedirectoryapp.Model.MovieInfo;
import com.example.vadym.moviedirectoryapp.Model.MovieRetrofit;
import com.example.vadym.moviedirectoryapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MovieRecyclerViewAdapter adapter;
    private List<Movie> movieList;
    private RequestQueue queue;

    private AlertDialog.Builder builder;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        queue = Volley.newRequestQueue(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            showInputDialog();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        Prefs prefs = new Prefs(MainActivity.this);
        String search = prefs.getSearch();

        movieList = new ArrayList<>();

        //getMovieList(search);
        getMovieRetrofit(search);

        adapter = new MovieRecyclerViewAdapter(this, movieList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public List<Movie> getMovieRetrofit(String searchTerm){

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieApi movieApi = retrofit.create(MovieApi.class);

        Call<MovieRetrofit> search = movieApi.getMovie( searchTerm );
        search.enqueue(new Callback<MovieRetrofit>() {
            @Override
            public void onResponse(Call<MovieRetrofit> call, retrofit2.Response<MovieRetrofit> response) {
//                Log.d("TAG",response.code()+"");
//                Log.d("TAG",String.valueOf(response.raw()));
                MovieRetrofit movieRetrofit = response.body();
                List<Movie> movieInfoList = movieRetrofit.getSearchList();
//                Log.d("TAG",String.valueOf(movieInfoList.size()));
                for (int i=0; i<movieInfoList.size();i++){
                    Movie info = movieInfoList.get(i);

                    Movie movie = new Movie();
                    movie.setTitle(info.getTitle());
                    movie.setYear("Year Released: " + info.getYear());
                    movie.setMovieType("Type: " + info.getMovieType());
                    movie.setPoster(info.getPoster());
                    movie.setImdbId(info.getImdbId());
                    movieList.add(movie);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieRetrofit> call, Throwable t) {

            }
        });
        return null;
    }

    //Get movies
    public List<Movie> getMovieList(String searchTerm){
        movieList.clear();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constants.URL_LEFT + searchTerm + Constants.API_KEY, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray array = response.getJSONArray("Search");
                            for(int i = 0; i<array.length();i++){
                                JSONObject object = array.getJSONObject(i);

                                Movie movie = new Movie();
                                movie.setTitle(object.getString("Title"));
                                movie.setYear("Year Released: " + object.getString("Year"));
                                movie.setMovieType("Type: " + object.getString("Type"));
                                movie.setPoster(object.getString("Poster"));
                                movie.setImdbId(object.getString("imdbID"));

                                //Log.d("Movies: ", movie.getTitle());
                                movieList.add(movie);
                            }

                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(jsonObjectRequest);
        return movieList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.new_search) {
            showInputDialog();
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showInputDialog(){
        builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_view,null);

        final EditText searchText = (EditText) view.findViewById(R.id.searchEdt);
        Button submitBtn = (Button) view.findViewById(R.id.submitBtn);
        builder.setView(view);
        dialog = builder.create();
        dialog.show();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Prefs prefs = new Prefs(MainActivity.this);
                if(!searchText.getText().equals("")) {
                    String search = searchText.getText().toString();
                    prefs.setSearch(search);

                    movieList.clear();
                    getMovieList(search);
                    //adapter.notifyDataSetChanged();
                }
                dialog.dismiss();
            }
        });
    }

}
