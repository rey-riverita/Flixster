package com.codepath.flixster;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Movie {
    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdrop_path);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String posterPath;
    public String originalTitle;
    public String overview;
    public String vote_average;
    public String popularity;
    public String backdrop_path;

    // Constructor can take a JSON object and within it extract out the field that we want for each property
    public Movie(JSONObject jsonObject) throws JSONException {
        this.posterPath = jsonObject.getString("poster_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overview = jsonObject.getString("overview");
        this.vote_average = jsonObject.getString("vote_average");
        this.popularity = jsonObject.getString("popularity");
        this.backdrop_path = jsonObject.getString("backdrop_path");
    }

    // Accepts a JSON array, iterates through each element and converts it to Movie
    public static ArrayList<Movie> fromJSONArray(JSONArray array) {
        ArrayList<Movie> results = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            try {
                results.add(new Movie(array.getJSONObject(i))); // Use new constructor and convert each element in the JSON array into a Movie
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return results;

    }


}

/*
    @Override
    public String toString() {
        return title +  " - " + rating;
    }

}
*/