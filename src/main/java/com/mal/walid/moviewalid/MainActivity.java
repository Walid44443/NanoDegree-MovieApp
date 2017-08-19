package com.mal.walid.moviewalid;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mal.walid.moviewalid.Fragment.DetailesFragment;
import com.mal.walid.moviewalid.Fragment.MainActivityFragment;
import com.mal.walid.moviewalid.SqlLite.MovieDataContract;
import com.mal.walid.moviewalid.model.MovieObj;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    public static Realm realm;
    public static int fragNum;
    static View mView;
    static View view;
    static Context mContext;

    static FragmentManager fragmentManager;
    private static Bundle savedInstanceState;

    public static void CheckFragment(int postion) {
        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(mContext);

        final String sortedBy = getData.getString("sortedby", "popular");
        Bundle bundle = new Bundle();
        bundle.putSerializable("Movie", MainActivityFragment.MovieArray.get(postion));
        bundle.putInt("id", MainActivityFragment.MovieArray.get(postion).getMovie_id());

        if (fragNum == 2) {

            DetailesFragment df = new DetailesFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.DetaileFragment, df);
            df.setArguments(bundle);
            transaction.commit();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.savedInstanceState = savedInstanceState;
        overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_top);

        getSupportLoaderManager().initLoader(1, MainActivity.savedInstanceState, this);

        setContentView(R.layout.activity_main);
        fragmentManager = getFragmentManager();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        realm = Realm.getInstance(new RealmConfiguration.Builder(this).name("favourite").build());
        realm.beginTransaction();
        realm.commitTransaction();
        fragNum = (findViewById(R.id.DetaileFragment) != null) ? 2 : 1;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivityFragment.updateAdabter();
            }
        });

        if (savedInstanceState == null) {
            MainActivityFragment mainActivityFragment = new MainActivityFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.MainActivity, mainActivityFragment, "MainFragment");
            transaction.commit();
        }
        view = mView;
        mContext = this;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    public void OpenTrailer(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("MovieID", ((MovieObj) bundle.getSerializable("Movie")).getMovie_id());
        Intent i = new Intent(this, TraillersActivity.class);
        i.putExtras(bundle);
        startActivity(i);
    }

    public void OpenReviews(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("MovieID", ((MovieObj) bundle.getSerializable("Movie")).getMovie_id());
        Intent i = new Intent(this, ReviewActivity.class);
        i.putExtras(bundle);
        startActivity(i);

    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        /*SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(mContext);
        String sortedBy = getData.getString("sortedby", "popular");

        if (sortedBy.equals("popular")||sortedBy.equals("top_rated")) {
            return null;
        } else {*/
        return new CursorLoader(getApplication(),
                MovieDataContract.MovieEntry.CONTENT_URI,
                null,
                null,
                null,
                null);
        //    }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null) {
            MainActivityFragment.MovieArray = Functions.MovieCursorToList(data);
            MainActivityFragment.makeAdabter(Functions.MovieCursorToList(data));
            MainActivityFragment.adapter.notifyDataSetChanged();

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().initLoader(1, savedInstanceState, this);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
