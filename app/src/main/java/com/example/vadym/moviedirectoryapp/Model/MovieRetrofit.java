package com.example.vadym.moviedirectoryapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vadym on 21.01.2018.
 */

public class MovieRetrofit {

    @SerializedName("Search")
    private List<Movie> searchList;
    @SerializedName("totalResults")
    private String total;

    public List<Movie> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<Movie> searchList) {
        this.searchList = searchList;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
