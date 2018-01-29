package com.example.vadym.moviedirectoryapp.Data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vadym.moviedirectoryapp.Activities.OnMovieClickListener;
import com.example.vadym.moviedirectoryapp.Model.Movie;
import com.example.vadym.moviedirectoryapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadym on 17.01.2018.
 */

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    @Nullable
    private OnMovieClickListener movieClickListener;
    @NonNull
    private List<Movie> movieList = new ArrayList<>();

    public MovieRecyclerViewAdapter() {

    }

    public void addAll(List<Movie> movies) {
        int insertStart = getItemCount();
        notifyItemRangeInserted(insertStart, movies.size());
        movieList.addAll(movies);
        //даний метод дає адаптеру зрозумітиЮ що в ньому додлися елементи.
        //тоді і сам ресайкл це буде знати і їх покаже.
    }

    @Nullable
    public Movie getMovie(int position) {
        if (position < 0 || position >= getItemCount()) {
            return null;
        }
        return movieList.get(position);
    }

    public void addItem(Movie movie) {
        notifyItemInserted(getItemCount() - 1);
        movieList.add(movie);
    }

    public void clear() {
        notifyItemRangeRemoved(0, getItemCount());
        movieList.clear();
    }


    public void setMovieClickListener(OnMovieClickListener listener) {
        this.movieClickListener = listener;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.moview_row, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, int position) {
        Movie movie = getMovie(position);
        if (movie == null) {
            return;
        }
        holder.setMovie(movie);

        holder.itemView.setOnClickListener((v)->{
            Log.d("TAG","OK");
            onMovieClick(holder.getAdapterPosition());});
    }


    public void onMovieClick(int position) {
        if (movieClickListener != null) {
            movieClickListener.onMovieClick(position);
        }
    }


    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
