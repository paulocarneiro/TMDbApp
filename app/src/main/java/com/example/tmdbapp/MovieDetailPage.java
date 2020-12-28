package com.example.tmdbapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MovieDetailPage extends AppCompatActivity {
    private static String JSON_URL = "https://api.themoviedb.org/3/movie/popular?api_key=cc9500330334ca14025230bc5295ff4b";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_page);
        TextView title = findViewById(R.id.title);
        TextView average_note = findViewById(R.id.average_txt);
        TextView overview_txt = findViewById(R.id.overview_txt);
        TextView original_title = findViewById(R.id.original_title);



        String id = "test";
        String title_str = "title";
        String overview = "overview";
        String original_lang = "original_lang";
        String original_title_str = "original_title";
        int image_poster = 1;


        Bundle extras = getIntent().getExtras();

        if(extras != null){
            id = extras.getString("id");
            title_str =  extras.getString("title");
            overview =  extras.getString("overview");
            original_title_str =  extras.getString("original_title");
            image_poster = extras.getInt("image");

        }

        average_note.setText(id);
        title.setText(title_str);
        overview_txt.setText(overview);
        original_title.setText(original_title_str);
        ImageView image = findViewById(R.id.imageView);
        image.setImageResource(image_poster);



    }

}