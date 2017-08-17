package com.mal.walid.moviewalid.model;


import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class MovieObj extends RealmObject implements Serializable {

    private String image_film, Img_poster, Overview_text, publish_time, Movie_name;
    private int Movie_rate;

    @PrimaryKey
    private int Movie_id;

    public MovieObj() {
    }

    public MovieObj(String name, int id, int rate, String overview, String imgPosterUrl, String imgUrl, String time) {
        this.Movie_id = id;
        this.Movie_name = name;
        this.Overview_text = overview;
        this.Img_poster = imgPosterUrl;
        this.image_film = imgUrl;
        this.Movie_rate = rate;
        this.publish_time = time;
    }

    public int getMovie_id() {
        return Movie_id;
    }

    public void setMovie_id(int movie_id) {
        Movie_id = movie_id;
    }

    public String getImage_film() {
        return image_film;
    }

    public void setImage_film(String image_film) {
        this.image_film = image_film;
    }

    public String getImg_poster() {
        return Img_poster;
    }

    public void setImg_poster(String img_poster) {
        Img_poster = img_poster;
    }

    public String getOverview_text() {
        return Overview_text;
    }

    public void setOverview_text(String overview_text) {
        Overview_text = overview_text;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public String getMovie_name() {
        return Movie_name;
    }

    public void setMovie_name(String movie_name) {
        Movie_name = movie_name;
    }

    public int getMovie_rate() {
        return Movie_rate;
    }

    public void setMovie_rate(int movie_rate) {
        Movie_rate = movie_rate;
    }
}
