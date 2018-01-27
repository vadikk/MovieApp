package com.example.vadym.moviedirectoryapp.Data;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vadym.moviedirectoryapp.Activities.MovieDetailActivity;
import com.example.vadym.moviedirectoryapp.Model.Movie;
import com.example.vadym.moviedirectoryapp.R;

import java.util.List;

/**
 * Created by Vadym on 17.01.2018.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

     TextView title;
     TextView year;
     TextView type;
     ImageView poster;
     Context context;
     Movie movie;

    public MovieViewHolder(View itemView, Context ctx) {
        super(itemView);

        context = ctx;
        title = (TextView) itemView.findViewById(R.id.movieTitleID);
        poster = (ImageView) itemView.findViewById(R.id.movieImageID);
        year = (TextView) itemView.findViewById(R.id.movieReleaseID);
        type = (TextView) itemView.findViewById(R.id.movieCatID);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra("movie",movie);
                context.startActivity(intent);
            }
        });
    }

    public void setMovie(Movie movie){
        this.movie = movie;
    }

    @Override
    public void onClick(View view) {

    }
}

