package com.example.vadym.moviedirectoryapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Vadym on 22.01.2018.
 */

public class MovieDetail {

    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Director")
    @Expose
    private String director;
    @SerializedName("Year")
    @Expose
    private String year;
    @SerializedName("Runtime")
    @Expose
    private String runTime;
    @SerializedName("imdbID")
    @Expose
    private String imdbId;
    @SerializedName("Poster")
    @Expose
    private String poster;
    @SerializedName("Genre")
    @Expose
    private String genre;
    @SerializedName("Writer")
    @Expose
    private String writer;
    @SerializedName("Actors")
    @Expose
    private String actors;
    @SerializedName("Plot")
    @Expose
    private String plot;
//    @SerializedName("Ratings")
//    @Expose
//    private String rating;
    @SerializedName("DVD")
    @Expose
    private String dvdRelease;
    @SerializedName("Production")
    @Expose
    private String productionCompany;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("Awards")
    @Expose
    private String awards;
    @SerializedName("Rated")
    @Expose
    private String tvRated;
    @SerializedName("Type")
    @Expose
    private String movieType;
    @SerializedName("BoxOffice")
    @Expose
    private String boxOffice;

    public String getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

//    public String getRating() {
//        return rating;
//    }
//
//    public void setRating(String rating) {
//        this.rating = rating;
//    }

    public String getDvdRelease() {
        return dvdRelease;
    }

    public void setDvdRelease(String dvdRelease) {
        this.dvdRelease = dvdRelease;
    }

    public String getProductionCompany() {
        return productionCompany;
    }

    public void setProductionCompany(String productionCompany) {
        this.productionCompany = productionCompany;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getTvRated() {
        return tvRated;
    }

    public void setTvRated(String tvRated) {
        this.tvRated = tvRated;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }
}
