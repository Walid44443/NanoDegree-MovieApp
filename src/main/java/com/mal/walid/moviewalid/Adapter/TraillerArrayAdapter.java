package com.mal.walid.moviewalid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mal.walid.moviewalid.R;
import com.mal.walid.moviewalid.model.TraillerObj;

import java.util.ArrayList;

/**
 * Created by walid4444 on 9/11/16.
 */


public class TraillerArrayAdapter extends ArrayAdapter<TraillerObj> {

    java.util.ArrayList<TraillerObj> ArrayList;
    int mResource;
    LayoutInflater inflater;
    TraillerObj mObject;

    public TraillerArrayAdapter(Context context, int resource, ArrayList<TraillerObj> objects) {
        super(context, resource, objects);
        this.ArrayList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        inflater = LayoutInflater.from(getContext());
        mObject = ArrayList.get((int) getItemId(position));
        View view = inflater.inflate(R.layout.trailler_layout, null);
        TextView TraillerName = (TextView) view.findViewById(R.id.TraillerName);
        TextView TraillerSite = (TextView) view.findViewById(R.id.TraillerSite);
        TextView TraillerSize = (TextView) view.findViewById(R.id.TraillerSize);
        TraillerName.setText(mObject.getTraillerName());
        TraillerSite.setText(mObject.getTraillerSite());
        TraillerSize.setText("#" + mObject.getTraillerSize());

        return view;
    }
}
