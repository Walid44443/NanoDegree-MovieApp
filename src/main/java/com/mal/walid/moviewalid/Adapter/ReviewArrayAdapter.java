package com.mal.walid.moviewalid.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mal.walid.moviewalid.R;
import com.mal.walid.moviewalid.model.ReviewObj;

/**
 * Created by walid4444 on 9/13/16.
 */
public class ReviewArrayAdapter extends ArrayAdapter<ReviewObj> {


    java.util.ArrayList<ReviewObj> ArrayList;
    int mResource;
    LayoutInflater inflater;
    ReviewObj mObject;

    public ReviewArrayAdapter(Context context, int resource, java.util.ArrayList<ReviewObj> objects) {
        super(context, resource, objects);
        this.ArrayList = objects;
        mResource = resource;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater = LayoutInflater.from(getContext());
        mObject = ArrayList.get((int) getItemId(position));
        View view = inflater.inflate(R.layout.review_layout, null);
        TextView TraillerName = (TextView) view.findViewById(R.id.RAuthor);
        TextView TraillerSite = (TextView) view.findViewById(R.id.RContent);
        TraillerName.setText(mObject.getRAuthor() + ":");
        TraillerSite.setText(mObject.getRContent());
        if (position % 2 == 0) {
            view.findViewById(R.id.review_box).setBackgroundColor(Color.parseColor("#FFCCCCCC"));
        }
        return view;
    }
}

