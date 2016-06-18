package com.codepath.flixster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.flixster.Movie;
import com.codepath.flixster.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Movie movie = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        //HANDLES RECYCLING
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
        }

        // Find the image view
        ImageView ivMovieImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);
        // Clear out image from convertView
        ivMovieImage.setImageResource(0);

        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);

        // Populate the data into the template view using the data object
        tvTitle.setText(movie.getOriginalTitle());
        tvOverview.setText(movie.getOverview());

        Log.d("MovieArrayAdapter", "Position: " + position);

        // Sets images depending on orientation
        int orientation = convertView.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Picasso.with(getContext()).load(movie.getPosterPath()).fit()
                    .placeholder(R.drawable.placeholder)
                    .transform(new RoundedCornersTransformation(10, 10))
                    .into(ivMovieImage);
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Picasso.with(getContext()).load(movie.getBackdropPath()).fit().centerCrop()
                    .placeholder(R.drawable.placeholder)
                    .transform(new RoundedCornersTransformation(10, 10))
                    .into(ivMovieImage);
        }


        // Return the completed view to render on screen
        return convertView;


    }
}
