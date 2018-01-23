package com.example.vadym.moviedirectoryapp.Data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vadym.moviedirectoryapp.Model.Movie;
import com.example.vadym.moviedirectoryapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Vadym on 17.01.2018.
 */

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private Context context;
    private List<Movie> movieList;

    public MovieRecyclerViewAdapter(Context context, List<Movie> movies) {
        this.context = context;
        movieList = movies;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.moview_row,parent,false);
        MovieViewHolder movieViewHolder = new MovieViewHolder(view,context,movieList);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        String posterLink = movie.getPoster();

        holder.title.setText(movie.getTitle());
        holder.type.setText(movie.getMovieType());
        holder.year.setText(movie.getYear());

        Picasso.with(context)
                .load(posterLink)
                .placeholder(android.R.drawable.ic_btn_speak_now)
                .into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
