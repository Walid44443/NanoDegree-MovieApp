package com.mal.walid.moviewalid.JsonParsing;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mal.walid.moviewalid.Fragment.MainActivityFragment;
import com.mal.walid.moviewalid.MainActivity;
import com.mal.walid.moviewalid.SqlLite.MovieDataContract;

import org.json.JSONObject;

/**
 * Created by walid4444 on 9/3/16.
 */
public class JsonParsing {
    private static Context mContext;
    private static View view;

    public JsonParsing(Context mContext, View view) {
        this.mContext = mContext;
        this.view = view;

    }

    public static void updateMovieData() {
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Please Wait , Loading");
        progressDialog.setTitle("Update data");
        progressDialog.show();
        progressDialog.setCancelable(false);
        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(mContext);
        String sortedBy = getData.getString("sortedby", "popular");
        String url = "";
        switch (sortedBy) {
            case "popular":
            case "top_rated":
                url = "http://api.themoviedb.org/3/movie/" + sortedBy + "?api_key=bcf286540bf4916e0df587d22b51035b";
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        BoxOfficeMovieResponse boxOffice = new BoxOfficeMovieResponse();
                        boxOffice.getJsonTOArray(response);
                        progressDialog.cancel();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //TODO show snakebar error
                        DatabaseFetch();
                        Snackbar.make(view, "No internet connection", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        progressDialog.cancel();

                    }
                });

                Volley.newRequestQueue(mContext).add(request);

                Volley.newRequestQueue(mContext).getSequenceNumber();
                break;
            case "favourite":
                //TODO database fetch
                // DatabaseFetch();
                progressDialog.cancel();

                break;
            default:
                //TODO snakebar unknown error
                break;
        }

    }

    protected static void DatabaseFetch() {
        /*SQLite sqLite = new SQLite(mContext);
        ArrayList<MovieObj> result = sqLite.getAllMovies() ;
        MainActivityFragment.MovieArray.clear();
        for (int i = 0;i < result.size();i++){
            MainActivityFragment.MovieArray.add(result.get(i));
        }*/

        Cursor cursor = mContext.getContentResolver().query(MovieDataContract.MovieDataEntity.BASE_CONTENT_URI, null, null, null, null);
        cursor.moveToFirst();
        if (MainActivityFragment.MovieArray.size() > 0)
            MainActivity.CheckFragment(0);
    }

    public static void updateTraillerData(int movieID) {


        String url = "http://api.themoviedb.org/3/movie/" + movieID + "/videos?api_key=bcf286540bf4916e0df587d22b51035b";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                BoxOfficeMovieResponse boxOffice = new BoxOfficeMovieResponse();
                boxOffice.getJsonTraillerTOArray(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //TODO show snakebar error
                Snackbar.make(view, "No internet connection", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                DatabaseFetch();

            }
        });

        Volley.newRequestQueue(mContext).add(request);

    }


    public static void updateReviewData(int movieID) {


        String url = "http://api.themoviedb.org/3/movie/" + movieID + "/reviews?api_key=bcf286540bf4916e0df587d22b51035b";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                BoxOfficeMovieResponse boxOffice = new BoxOfficeMovieResponse();
                boxOffice.getJsonReviewTOArray(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //TODO show snakebar error
                Snackbar.make(view, "No internet connection", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                DatabaseFetch();

            }
        });
        Volley.newRequestQueue(mContext).add(request);
    }


}
