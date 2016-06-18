package com.codepath.flixster;

import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieInfoActivity extends AppCompatActivity {

    TextView tvOverview;
    ImageView ivMovieInfoBackground;
    TextView tvPopularity;
    TextView tvMovieTitle;
    TextView tvRating;
    String url ="";

    public static String showPopularity(float popularity){
        return "Popularity: " + popularity;
    }

    public static String showRating(float rating){
        return "Critics' rating: " + rating + " out of 5 stars!";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        String title = getIntent().getStringExtra("original_title");

        tvMovieTitle = (TextView) findViewById(R.id.tvMovieTitle);
        tvMovieTitle.setText(title);

        String overview = getIntent().getStringExtra("overview");

        tvOverview = (TextView) findViewById(R.id.tvOverview);
        tvOverview.setText(overview);

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            url = getIntent().getStringExtra("poster_path");
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            url = getIntent().getStringExtra("backdrop_path");
        }

        ivMovieInfoBackground = (ImageView) findViewById(R.id.ivMovieInfoBackground);
        Picasso.with(MovieInfoActivity.this).load(url).into(ivMovieInfoBackground);




        float rating = (Float.parseFloat(getIntent().getStringExtra("vote_average")))/2; //Parses string to float type, which is what rating property of ratingBar accepts
        tvRating = (TextView) findViewById(R.id.tvRating);
        tvRating.setText(showRating(rating));

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP); // Changes the color of the stars to yellow
        ratingBar.setRating(rating);

        float popularity = Float.parseFloat(getIntent().getStringExtra("popularity"));
        tvPopularity = (TextView) findViewById(R.id.tvPopularity);
        tvPopularity.setText(showPopularity(popularity));

        /*
        ProgressDialog progressBar = new ProgressDialog(this);
        progressBar.setMax(50);
        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        progressBar.setProgress((int)popularity);
        progressBar.show();
        */




            //String inReplyTo = getIntent().getStringExtra("in_reply_to");
            //int code = getIntent().getIntExtra("code", 0);

    }

}


