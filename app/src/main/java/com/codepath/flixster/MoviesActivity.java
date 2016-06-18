package com.codepath.flixster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.codepath.flixster.adapters.MovieArrayAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

//import com.codepath.flixster.models.Movie;

public class MoviesActivity extends AppCompatActivity {

    ArrayList<Movie> movies;
    MovieArrayAdapter movieAdapter;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        lvItems = (ListView) findViewById(R.id.lvMovies);
        movies = new ArrayList<>();
        movieAdapter = new MovieArrayAdapter(this, movies);
        lvItems.setAdapter(movieAdapter);

        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

        // Allows Java applications to easily execute HTTP requests and asynchronously process the HTTP responses
        AsyncHttpClient client = new AsyncHttpClient();

        // JsonHttpResponseHandler is used to intercept and handle the responses from requests made using AsyncHttpClient, with automatic parsing into a JSONObject or JSONArray
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJsonResults = null;

                try {
                    // Response = entire data that comes back from the API, "results" is the key that we want in this case
                    movieJsonResults = response.getJSONArray("results");
                    movies.addAll(Movie.fromJSONArray(movieJsonResults));
                    movieAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }

        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(MoviesActivity.this, MovieInfoActivity.class);
                intent.putExtra("overview", movies.get(i).overview);
                intent.putExtra("poster_path", movies.get(i).getPosterPath());
                intent.putExtra("vote_average", movies.get(i).vote_average);
                intent.putExtra("popularity", movies.get(i).popularity);
                intent.putExtra("original_title", movies.get(i).originalTitle);
                intent.putExtra("backdrop_path", movies.get(i).getBackdropPath());
                startActivity(intent);
            }
        });
    }

}
/*
        // 1. Get the actual movies
        ArrayList<Movie> movies = Movie.getFakeMovies();

        // 2. Get the ListView that we want to populate
        ListView lvMovies = (ListView)findViewById(R.id.lvMovies);

        // 3. Create an ArrayAdapter
        //ArrayAdapter<Movie> adapter = new ArrayAdapter<Movie>(this, android.R.layout.simple_list_item_1, movies);
        MoviesAdapter adapter = new MoviesAdapter(this, movies);

        // 4. Associate the ArrayAdapter with the ListView
        if (lvMovies != null) {
            lvMovies.setAdapter(adapter);
        }
    }
}

*/