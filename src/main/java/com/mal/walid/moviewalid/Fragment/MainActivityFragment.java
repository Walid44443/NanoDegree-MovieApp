package com.mal.walid.moviewalid.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.mal.walid.moviewalid.Adapter.GridViewAdabter;
import com.mal.walid.moviewalid.Functions;
import com.mal.walid.moviewalid.JsonParsing.BoxOfficeMovieResponse;
import com.mal.walid.moviewalid.JsonParsing.JsonParsing;
import com.mal.walid.moviewalid.MainActivity;
import com.mal.walid.moviewalid.MovieDetailes;
import com.mal.walid.moviewalid.R;
import com.mal.walid.moviewalid.SqlLite.MovieDataContract;
import com.mal.walid.moviewalid.model.MovieObj;

import java.util.ArrayList;

public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    public static final int MOVIE_LOADER = 0;
    public static ArrayList<MovieObj> MovieArray = new ArrayList<MovieObj>();
    public static GridViewAdabter adapter;
    static GridView grid;
    static View view;
    static Context mContext;

    public static void makeAdabter(ArrayList list) {
        grid = (GridView) view.findViewById(R.id.MainGrid);
        adapter = new GridViewAdabter(mContext, R.layout.movie_layout, list);
        grid.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public static void updateAdabter() {
        new BoxOfficeMovieResponse();
        JsonParsing jsonParsing = new JsonParsing(mContext, view);
        jsonParsing.updateMovieData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getActivity());
        view = getView();
        mContext = getActivity();

        grid = (GridView) view.findViewById(R.id.MainGrid);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (MainActivity.fragNum == 1) {
                    Intent detail = new Intent(getActivity(), MovieDetailes.class);
                    Bundle be = new Bundle();
                    MovieObj obj = MovieArray.get(position);
                    be.putSerializable("Movie", obj);
                    if (MainActivity.realm.where(MovieObj.class).equalTo("Movie_id", obj.getMovie_id()).count() > 0) {
                        detail.putExtra("MovieID", obj.getMovie_id());
                    }
                    detail.putExtras(be);
                    startActivity(detail);
                }
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        updateAdabter();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(mContext);
        String sortedBy = getData.getString("sortedby", "popular");

        if (sortedBy.equals("popular") || sortedBy.equals("top_rated")) {
            return null;
        } else {
            return new CursorLoader(getActivity(),
                    MovieDataContract.MovieEntry.CONTENT_URI,
                    new String[]{MovieDataContract.MovieEntry._ID, MovieDataContract.MovieEntry.COLUMN_IMAGE_URL},
                    null,
                    null,
                    null);
        }


    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null) {
            MovieArray.clear();
            MovieArray = Functions.MovieCursorToList(data);
            makeAdabter(MovieArray);
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
