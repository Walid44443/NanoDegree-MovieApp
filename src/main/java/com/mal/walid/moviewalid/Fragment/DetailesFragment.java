package com.mal.walid.moviewalid.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mal.walid.moviewalid.Functions;
import com.mal.walid.moviewalid.MainActivity;
import com.mal.walid.moviewalid.R;
import com.mal.walid.moviewalid.ReviewActivity;
import com.mal.walid.moviewalid.TraillersActivity;
import com.mal.walid.moviewalid.model.MovieObj;
import com.squareup.picasso.Picasso;

/**
 * Created by walid4444 on 9/16/16.
 */
public class DetailesFragment extends Fragment {

    int MovieID;
    MovieObj obj;
    String sortedBy;
    Bundle bundle;
    boolean IS_FAV;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bundle = getArguments();
        SharedPreferences getData = PreferenceManager.getDefaultSharedPreferences(getActivity());
        sortedBy = getData.getString("sortedby", "popular");
        obj = (MovieObj) bundle.getSerializable("Movie");
        MovieID = obj.getMovie_id();


        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(obj.getMovie_name());

        final FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        IS_FAV = Functions.IsFavroiut(getActivity(), MovieID);

        if (IS_FAV)
            fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorSaved)));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!IS_FAV) {
                    int r = Functions.saveMovieToDB(view.getContext(), obj);
                    IS_FAV = Functions.IsFavroiut(getActivity(), MovieID);
                    fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorSaved)));
                    Toast.makeText(view.getContext(), "Move marked as Favorite ", Toast.LENGTH_SHORT).show();

                } else {
                    int r = Functions.DelMovieFromDB(view.getContext(), MovieID);
                    IS_FAV = Functions.IsFavroiut(getActivity(), MovieID);
                    fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
                    Toast.makeText(view.getContext(), "Move unmarked as Favorite ", Toast.LENGTH_SHORT).show();

                }
            }
        });

        if (MainActivity.realm.where(MovieObj.class).equalTo("Movie_id", obj.getMovie_id()).count() > 0) {
            fab.setImageDrawable(getResources().getDrawable(R.drawable.star_filled));
        }

        TextView Overview = (TextView) getActivity().findViewById(R.id.MovieOverview);
        Picasso.with(getActivity()).load("http://image.tmdb.org/t/p/w185/" + obj.getImg_poster()).into((ImageView) getView().findViewById(R.id.bar_image));
        Overview.setText(obj.getOverview_text());
        ImageView[] RatingStars = new ImageView[5];
        RatingStars[0] = (ImageView) getView().findViewById(R.id.star1);
        RatingStars[1] = (ImageView) getView().findViewById(R.id.star2);
        RatingStars[2] = (ImageView) getView().findViewById(R.id.star3);
        RatingStars[3] = (ImageView) getView().findViewById(R.id.star4);
        RatingStars[4] = (ImageView) getView().findViewById(R.id.star5);
        makeRatingStars(RatingStars, obj.getMovie_rate());

        TextView realase_date = (TextView) getView().findViewById(R.id.realase_date);
        realase_date.setText(obj.getPublish_time());

        Button trailers = (Button) getActivity().findViewById(R.id.trailersButton);
        Button reviews = (Button) getActivity().findViewById(R.id.reviewsButton);

        trailers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putInt("MovieID", obj.getMovie_id());
                Intent i = new Intent(getActivity(), TraillersActivity.class);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bundle.putInt("MovieID", obj.getMovie_id());
                Intent i = new Intent(getActivity(), ReviewActivity.class);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_detailes_fragment, container, false);
    }


    private void makeRatingStars(ImageView[] RatingStars, float rate) {
        float rRate = rate / 2;
        int i = (int) rRate;
        int j = (int) rRate;
        float x = rRate - i;
        String uri;
        int imageResource;
        Drawable res;
        for (; i >= 0; i--) {
            uri = "@drawable/star_filled";
            imageResource = getResources().getIdentifier(uri, null, getActivity().getPackageName());
            res = getResources().getDrawable(imageResource);
            RatingStars[i].setImageDrawable(res);
        }
        if (x >= 0.5 && j < 5) {
            uri = "@drawable/star_half";
            imageResource = getResources().getIdentifier(uri, null, getActivity().getPackageName());
            res = getResources().getDrawable(imageResource);
            RatingStars[j].setImageDrawable(res);

        }
    }


}