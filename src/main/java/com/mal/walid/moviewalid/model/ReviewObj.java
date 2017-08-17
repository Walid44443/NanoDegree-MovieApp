package com.mal.walid.moviewalid.model;

import java.io.Serializable;

/**
 * Created by walid4444 on 9/13/16.
 */
public class ReviewObj implements Serializable {

    int Movie_id;
    String RAuthor;
    String RContent;
    String RUrl;

    public int getMovie_id() {
        return Movie_id;
    }

    public void setMovie_id(int movie_id) {
        Movie_id = movie_id;
    }

    public String getRAuthor() {
        return RAuthor;
    }

    public void setRAuthor(String RAuthor) {
        this.RAuthor = RAuthor;
    }

    public String getRContent() {
        return RContent;
    }

    public void setRContent(String RContent) {
        this.RContent = RContent;
    }

    public String getRUrl() {
        return RUrl;
    }

    public void setRUrl(String RUrl) {
        this.RUrl = RUrl;
    }
}
