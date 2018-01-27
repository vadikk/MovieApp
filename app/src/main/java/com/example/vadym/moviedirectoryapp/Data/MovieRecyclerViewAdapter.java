package com.example.vadym.moviedirectoryapp.Data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vadym.moviedirectoryapp.Model.Movie;
import com.example.vadym.moviedirectoryapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadym on 17.01.2018.
 */

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieViewHolder> implements View.OnClickListener {

    private Context context;
    @NonNull
    private List<Movie> movieList = new ArrayList<>();

    public MovieRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void addAll(List<Movie> movies){
        int insertStart = getItemCount();
        notifyItemRangeInserted(insertStart, movies.size());
        movieList.addAll(movies);
        //даний метод дає адаптеру зрозумітиЮ що в ньому додлися елементи.
        //тоді і сам ресайкл це буде знати і їх покаже.
    }

    public void addItem(Movie movie){
        notifyItemInserted(getItemCount()-1);
        movieList.add(movie);
    }

    public void clear(){
        notifyItemRangeRemoved(0, getItemCount());
        movieList.clear();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.moview_row, parent, false);
        return new MovieViewHolder(view, context, movieList);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

        Movie movie = movieList.get(position);
        String posterLink = movie.getPoster();

        holder.title.setText(movie.getTitle());
        holder.type.setText("Type: " + movie.getMovieType());
        holder.year.setText("Year Released: " + movie.getYear());

        Picasso.with(context)
                .load(posterLink)
                .placeholder(android.R.drawable.ic_btn_speak_now)
                .into(holder.poster);

        holder.setMovie(movie);

    }


    @Override
    public int getItemCount() {
        return movieList.size();
    }

    @Override
    public void onClick(View view) {

    }
}
