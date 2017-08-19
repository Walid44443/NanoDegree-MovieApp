package com.mal.walid.moviewalid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mal.walid.moviewalid.Adapter.ReviewArrayAdapter;
import com.mal.walid.moviewalid.JsonParsing.JsonParsing;
import com.mal.walid.moviewalid.model.ReviewObj;

import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity {

    public static ArrayList<ReviewObj> ReviewList = new ArrayList<ReviewObj>();
    public static ReviewArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_top);
        Bundle bundle = getIntent().getExtras();
        int MovieID = bundle.getInt("MovieID");
        JsonParsing jsonParsing = new JsonParsing(getApplicationContext(), findViewById(R.id.RRoot));
        JsonParsing.updateReviewData(MovieID);
        ListView listView = (ListView) findViewById(R.id.reviews);
        adapter = new ReviewArrayAdapter(this, R.layout.movie_layout, ReviewList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ReviewList.get(position).getRUrl()));
                startActivity(browserIntent);
            }
        });
    }
}
