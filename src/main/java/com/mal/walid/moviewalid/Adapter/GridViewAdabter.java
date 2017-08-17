package com.mal.walid.moviewalid.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.mal.walid.moviewalid.R;
import com.mal.walid.moviewalid.model.MovieObj;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by walid4444 on 9/4/16.
 */
public class GridViewAdabter extends BaseAdapter {
    ArrayList<MovieObj> ArrayList;
    Context mContext;
    MovieObj mObject;
    int mResource;
    LayoutInflater inflater;

    public GridViewAdabter(Context context, int resource, ArrayList<MovieObj> ArrayList) {
        mContext = context;
        this.ArrayList = ArrayList;
        this.mResource = resource;

    }

    public void UpdateList(ArrayList NList) {
        this.ArrayList = NList;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return ArrayList.size();

    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater = LayoutInflater.from(mContext);
        mObject = ArrayList.get((int) getItemId(position));
        Log.d("item view", mObject.getMovie_name() + " " + mObject.getImage_film());
        View view = inflater.inflate(R.layout.movie_layout, null);
        Picasso.with(mContext).load("http://image.tmdb.org/t/p/w185/" + mObject.getImage_film()).into((ImageView) view.findViewById(R.id.MovieImg));
        return view;
    }
}
