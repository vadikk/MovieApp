package com.example.vadym.moviedirectoryapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.vadym.moviedirectoryapp.Constant.Prefs;
import com.example.vadym.moviedirectoryapp.Data.MovieRecyclerViewAdapter;
import com.example.vadym.moviedirectoryapp.Model.Movie;
import com.example.vadym.moviedirectoryapp.Model.MovieApi;
import com.example.vadym.moviedirectoryapp.Model.MovieRetrofit;
import com.example.vadym.moviedirectoryapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements OnMovieClickListener, MovieSearchDialog.OnMovieSearchListener {

    private RecyclerView recyclerView;
    private MovieRecyclerViewAdapter adapter;

    private AlertDialog.Builder builder;
    private AlertDialog dialog;

    private int total;
    private ProgressBar bar;
    private boolean isLoading = false;
    private int page = 1;
    private Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bar = (ProgressBar) findViewById(R.id.progressBar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialog();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //recyclerView.setHasFixedSize(true);
        final LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        prefs = new Prefs(MainActivity.this);
        final String search = prefs.getSearch();
        getMovieRetrofit(search,page);
        Log.d("TAG","Search " + search);

        adapter = new MovieRecyclerViewAdapter();
        adapter.setMovieClickListener(this);

        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //якщо ми вже вантажимо нові елементи, то можна подальшу логіку ігнорувати
                if (isLoading) return;

                //перевіряємо на те, чи користувач вже прогортав до потрібної позиції
                int lastVisibleItemPosition = manager.findLastVisibleItemPosition();
                //ось тут ти мені потім відповісиш, чому я заюзав таку конструкцію
                //оскільки в нас адптер завжди знає, скільки елементів в нас є - ми отримаємо ту позицію, з якої має відбутися завантаження


                //тут трохи є хитрість, розкажу яка.
                //в тебе при кожному русі викликається цей метод, ти раз скрольнув і він може викликатися 99 разів.
                //візьмем до уваги ту інфу, що нам тре зробити типу безперервний скролі, тому ми будемо довантажувати
                //нові елементи, коли количтувач прогортав вже 75% елементів
                int allLoadedItemsCount = adapter.getItemCount();
                int loadShouldStartPosition = (int) ((double) allLoadedItemsCount * 0.75);

                //тут ми маємо перевірити, чи в нас дійшло до потрібної позиції і чи ще є що підвантажувати
                //isLoading = loadShouldStartPosition >= lastVisibleItemPosition && allLoadedItemsCount < total;

                //тре буде глянути, як тут обпрацювати кейс, коли в нас більше стало елементів, ніж могло буть.
                if(loadShouldStartPosition<=lastVisibleItemPosition && allLoadedItemsCount<total) {
                    page++;
                    isLoading = true;
                }

                Log.d("TAG", "last " + lastVisibleItemPosition + " allLoadedItem " + allLoadedItemsCount
                        + " loadshouldStart " + loadShouldStartPosition);

                Log.d("TAG", "Boolean " + isLoading);

                if (isLoading) {
                    loadMore(search,page);
                }

            }
        });

    }


    private void loadMore(final String search, int page) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isLoading) {
                    isLoading = false;
                    bar.setVisibility(View.VISIBLE);
                    final String search = prefs.getSearch();
                    getMovieRetrofit(search, page);
                }
            }
        }, 3000);
    }

    public void getMovieRetrofit(String searchTerm, int page) {

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieApi movieApi = retrofit.create(MovieApi.class);

        Call<MovieRetrofit> search = movieApi.getMovie(searchTerm, page);
        search.enqueue(new Callback<MovieRetrofit>() {
            @Override
            public void onResponse(Call<MovieRetrofit> call, retrofit2.Response<MovieRetrofit> response) {
//                Log.d("TAG", response.code() + "");
//                Log.d("TAG", String.valueOf(response.raw()));
                MovieRetrofit movieRetrofit = response.body();
                total = Integer.parseInt(movieRetrofit.getTotal());
                List<Movie> movieInfoList = movieRetrofit.getSearchList();
                adapter.addAll(movieInfoList);

                bar.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        bar.setVisibility(View.GONE);
                    }
                }, 2000);

                Log.d("TAG", "Total " + total + " Size list " + adapter.getItemCount());

                //таку штуку тре робити тільки тоді, коли в тебе всі дані міняються і ти не можеш вказати чи знати, які були зміни.
                //наприклад, коли ти пеертасував рандомно список із фільмами, чи оновив сторінку повністю.
                //adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieRetrofit> call, Throwable t) {

            }
        });
    }

    @Override
    public void onMovieClick(int position) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("movie", adapter.getMovie(position));
        startActivity(intent);
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


    public void showInputDialog() {

        MovieSearchDialog searchDialog = new MovieSearchDialog(this);
        searchDialog.setListener(this);
        searchDialog.show();
    }


    @Override
    public void startSearch(String searchString) {

        Prefs prefs = new Prefs(MainActivity.this);
        prefs.setSearch(searchString);
        adapter.clear();
        page = 1;
        getMovieRetrofit(searchString,page);

    }
}
