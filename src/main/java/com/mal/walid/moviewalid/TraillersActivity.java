package com.mal.walid.moviewalid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mal.walid.moviewalid.Adapter.TraillerArrayAdapter;
import com.mal.walid.moviewalid.JsonParsing.BoxOfficeMovieResponse;
import com.mal.walid.moviewalid.JsonParsing.JsonParsing;
import com.mal.walid.moviewalid.model.TraillerObj;

import java.util.ArrayList;


public class TraillersActivity extends AppCompatActivity {

    public static ArrayList<TraillerObj> TraillerList = new ArrayList<TraillerObj>();
    public static TraillerArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        int MovieID = bundle.getInt("MovieID");
        setContentView(R.layout.activity_traillers);
        BoxOfficeMovieResponse boxOfficeMovieResponse = new BoxOfficeMovieResponse();
        JsonParsing jsonParsing = new JsonParsing(getApplicationContext(), findViewById(R.id.TRoot));
        jsonParsing.updateTraillerData(MovieID);
        ListView listView = (ListView) findViewById(R.id.traillers);
        adapter = new TraillerArrayAdapter(getApplicationContext(), R.layout.movie_layout, TraillerList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + TraillerList.get(position).getTraillerKey()));
                startActivity(browserIntent);
            }
        });
    }

}
