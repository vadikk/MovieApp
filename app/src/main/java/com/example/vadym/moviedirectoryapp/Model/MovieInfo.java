package com.example.vadym.moviedirectoryapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Vadym on 22.01.2018.
 */

public class MovieInfo {

    @SerializedName("Title")
    @Expose
    public String title;
    @SerializedName("Year")
    @Expose
    public String year;
    @SerializedName("imdbID")
    @Expose
    public String id;
    @SerializedName("Type")
    @Expose
    public String type;
    @SerializedName("Poster")
    @Expose
    public String poster;

    public MovieInfo(){

    }

    public MovieInfo(String title, String year, String id, String type, String poster) {
        this.title = title;
        this.year = year;
        this.id = id;
        this.type = type;
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
