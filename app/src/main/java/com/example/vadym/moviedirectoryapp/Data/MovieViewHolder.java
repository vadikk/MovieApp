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

public class MovieViewHolder extends RecyclerView.ViewHolder

//todo це тут навіщо, якщо не юзається?
//        implements View.OnClickListener
{


    TextView title;
    TextView year;
    TextView type;
    ImageView poster;


    // TODO: 1/27/18 Я ж писав тобі - НЕ зберігати тут поточний фільм,
    // TODO: тре написати свій власний інтерфейс, який буде тобі в актівіті казати,
    // TODO: який елемент викликався і вже в актівіті ти маєш писати логіку обпрацювння натиску.
    // TODO: Це все робитсья для того, щоб було простіше підтримувати код.
//     Context context;
//     Movie movie;

    public MovieViewHolder(View itemView
//            , Context ctx
    ) {
        super(itemView);

//        context = ctx;
        title = (TextView) itemView.findViewById(R.id.movieTitleID);
        poster = (ImageView) itemView.findViewById(R.id.movieImageID);
        year = (TextView) itemView.findViewById(R.id.movieReleaseID);
        type = (TextView) itemView.findViewById(R.id.movieCatID);

//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, MovieDetailActivity.class);
//                intent.putExtra("movie",movie);
//                context.startActivity(intent);
//            }
//        });


    }

//    public void setMovie(Movie movie){
//        this.movie = movie;
//    }

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


    //    @Override
//    public void onClick(View view) {
//
//    }
}

