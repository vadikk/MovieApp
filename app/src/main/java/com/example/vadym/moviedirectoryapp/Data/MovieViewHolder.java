package com.example.vadym.moviedirectoryapp.Data;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vadym.moviedirectoryapp.Model.Movie;
import com.example.vadym.moviedirectoryapp.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Vadym on 17.01.2018.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder {

    TextView title;
    TextView year;
    TextView type;
    ImageView poster;

    public MovieViewHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.movieTitleID);
        poster = (ImageView) itemView.findViewById(R.id.movieImageID);
        year = (TextView) itemView.findViewById(R.id.movieReleaseID);
        type = (TextView) itemView.findViewById(R.id.movieCatID);
    }

    public void setMovie(@NonNull Movie movie) {
        String posterLink = movie.getPoster();

        title.setText(movie.getTitle());
        type.setText("Type: " + movie.getMovieType());
        year.setText("Year Released: " + movie.getYear());

        Picasso.with(this.itemView.getContext())
                .load(posterLink)
                .placeholder(android.R.drawable.ic_btn_speak_now)
                .into(poster);
    }

}

