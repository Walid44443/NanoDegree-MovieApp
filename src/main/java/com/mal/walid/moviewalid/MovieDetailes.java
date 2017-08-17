package com.mal.walid.moviewalid;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.mal.walid.moviewalid.Fragment.DetailesFragment;

public class MovieDetailes extends AppCompatActivity {

    String sortedBy;
    Bundle bundle;

    @Override
    protected void onResume() {
        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        super.onResume();
        sortedBy = getData.getString("sortedby", "popular");
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detailes);

        DetailesFragment df = new DetailesFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.movie_detailes, df);
        bundle = new Bundle();
        bundle.putSerializable("Movie", getIntent().getExtras().getSerializable("Movie"));
        df.setArguments(bundle);
        transaction.commit();

    }

}
