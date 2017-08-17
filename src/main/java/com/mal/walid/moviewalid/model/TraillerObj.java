package com.mal.walid.moviewalid.model;

import java.io.Serializable;

/**
 * Created by walid4444 on 9/11/16.
 */
public class TraillerObj implements Serializable {
    int Movie_id;
    String traillerKey, traillerSite, traillerName, traillerSize;

    public int getMovie_id() {
        return Movie_id;
    }

    public void setMovie_id(int movie_id) {
        Movie_id = movie_id;
    }

    public String getTraillerKey() {
        return traillerKey;
    }

    public void setTraillerKey(String traillerKey) {
        this.traillerKey = traillerKey;
    }

    public String getTraillerSite() {
        return traillerSite;
    }

    public void setTraillerSite(String traillerSite) {
        this.traillerSite = traillerSite;
    }

    public String getTraillerName() {
        return traillerName;
    }

    public void setTraillerName(String traillerName) {
        this.traillerName = traillerName;
    }

    public String getTraillerSize() {
        return traillerSize;
    }

    public void setTraillerSize(String traillerSize) {
        this.traillerSize = traillerSize;
    }
}
