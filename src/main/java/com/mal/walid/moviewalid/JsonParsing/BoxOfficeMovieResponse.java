package com.mal.walid.moviewalid.JsonParsing;

import android.util.Log;

import com.mal.walid.moviewalid.Fragment.MainActivityFragment;
import com.mal.walid.moviewalid.MainActivity;
import com.mal.walid.moviewalid.ReviewActivity;
import com.mal.walid.moviewalid.TraillersActivity;
import com.mal.walid.moviewalid.model.MovieObj;
import com.mal.walid.moviewalid.model.ReviewObj;
import com.mal.walid.moviewalid.model.TraillerObj;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by walid4444 on 9/3/16.
 */
public class BoxOfficeMovieResponse {
    public BoxOfficeMovieResponse() {
        // MainActivityFragment.MovieArray.clear();
        // MainActivityFragment.adapter.notifyDataSetChanged();
        MainActivityFragment.MovieArray.size();
        //  MainActivityFragment.adapter.UpdateList(MainActivityFragment.MovieArray);
        Log.d("Movie size", MainActivityFragment.MovieArray.size() + "");

    }

    public static void getJsonTOArray(JSONObject response) {
        try {
            MainActivityFragment.MovieArray.clear();
            JSONArray result = response.getJSONArray("results");
            //movies.clear();
            for (int i = 0; i < result.length(); i++) {
                JSONObject object = result.getJSONObject(i);
                MovieObj item = new MovieObj();
                try {
                    item.setMovie_id(object.getInt("id"));
                    item.setImage_film(object.getString("poster_path"));
                    item.setImg_poster(object.getString("backdrop_path"));
                    item.setMovie_name(object.getString("title"));
                    item.setOverview_text(object.getString("overview"));
                    item.setPublish_time(object.getString("release_date"));
                    item.setMovie_rate(object.getInt("vote_average"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                MainActivityFragment.MovieArray.add(item);
                MainActivityFragment.adapter.notifyDataSetInvalidated();
                MainActivityFragment.adapter.notifyDataSetChanged();
            }
            MainActivityFragment.makeAdabter(MainActivityFragment.MovieArray);

            if (MainActivityFragment.MovieArray.size() > 0)
                MainActivity.CheckFragment(0);

        } catch (JSONException e1) {
            e1.printStackTrace();
        }

    }


    public static void getJsonTraillerTOArray(JSONObject response) {
        try {
            JSONArray result = response.getJSONArray("results");
            TraillersActivity.TraillerList.clear();
            for (int i = 0; i < result.length(); i++) {
                JSONObject object = result.getJSONObject(i);
                TraillerObj item = new TraillerObj();
                try {
                    item.setMovie_id(response.getInt("id"));
                    item.setTraillerKey(object.getString("key"));
                    item.setTraillerName(object.getString("name"));
                    item.setTraillerSite(object.getString("site"));
                    item.setTraillerSize(object.getString("size"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                TraillersActivity.TraillerList.add(item);
                TraillersActivity.adapter.notifyDataSetInvalidated();
                TraillersActivity.adapter.notifyDataSetChanged();
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        } finally {

        }
    }

    public static void getJsonReviewTOArray(JSONObject response) {
        try {
            JSONArray result = response.getJSONArray("results");
            ReviewActivity.ReviewList.clear();
            for (int i = 0; i < result.length(); i++) {
                JSONObject object = result.getJSONObject(i);
                ReviewObj item = new ReviewObj();
                try {
                    item.setMovie_id(response.getInt("id"));
                    item.setRAuthor(object.getString("author"));
                    item.setRContent(object.getString("content"));
                    item.setRUrl(object.getString("url"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ReviewActivity.ReviewList.add(item);
                ReviewActivity.adapter.notifyDataSetInvalidated();
                ReviewActivity.adapter.notifyDataSetChanged();
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        } finally {

        }
    }
}
