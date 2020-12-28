package com.example.tmdbapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static String JSON_URL = "https://api.themoviedb.org/3/movie/popular?api_key=cc9500330334ca14025230bc5295ff4b";
    private Adaptery.RecyclerViewClickListener listener;
    List<MovieModelClass> movieList;
    RecyclerView recyclerView;
    ImageView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        header = findViewById(R.id.imageView);

        Glide.with(this)
                .load(R.drawable.header)
                .into(header);

        GetData getData = new GetData();
        getData.execute();

    }

    public class GetData extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... strings) {

            String current ="";

            try {
                URL url;
                HttpURLConnection urlConnection = null;

                try {
                    url = new URL(JSON_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream is = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);

                    int data = isr.read();
                    while (data != -1) {
                        current += (char) data;
                        data = isr.read();


                    }
                    return current;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(urlConnection != null){
                        urlConnection.disconnect();
                    }
                }
                } catch (Exception e) {
                e.printStackTrace();
                }


            return current;
        }

        @Override
        protected void onPostExecute(String s) {

            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    MovieModelClass model = new MovieModelClass();
                    model.setId(jsonObject1.getString("vote_average"));
                    model.setName(jsonObject1.getString("title"));
                    model.setImg(jsonObject1.getString("poster_path"));

                    model.setOriginal_title(jsonObject1.getString("original_title"));
                    model.setOverview(jsonObject1.getString("overview"));

                    movieList.add(model);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            PutDataIntoRecyclerView( movieList);

        }
    }







    private void PutDataIntoRecyclerView(List<MovieModelClass> movieList){
        setOnClickListener();
        Adaptery adaptery = new  Adaptery( this, movieList, listener);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        recyclerView.setAdapter(adaptery);
    }

    private void setOnClickListener() {
        listener = new Adaptery.RecyclerViewClickListener() {
            @Override
            public void OnClick(View itemView, int position) {
                Intent intent = new Intent(getApplicationContext(),MovieDetailPage.class);
                intent.putExtra("id", movieList.get(position).getId());
                intent.putExtra("title", movieList.get(position).getName());
                intent.putExtra("original_title", movieList.get(position).getOriginal_title());
                intent.putExtra("image", movieList.get(position).getImg());
                intent.putExtra("overview", movieList.get(position).getOverview());
                startActivity(intent);
            }
        };

        }
    }
