package com.mal.walid.moviewalid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.mal.walid.moviewalid.Fragment.MainActivityFragment;
import com.mal.walid.moviewalid.SqlLite.MovieDataContract;
import com.mal.walid.moviewalid.model.MovieObj;

import java.util.ArrayList;

/**
 * Created by walid4444 on 7/26/17.
 */

public class Functions {


    public static Uri generateMovieUriFromID(int id) {
        Uri uri = Uri.parse(MovieDataContract.MovieDataEntity.BASE_CONTENT_URI.toString() + "/" + MovieDataContract.MovieDataEntity.PATH_MOVIE + "/" + id);
        return uri;
    }

    public static int fetchMovieIdFromUri(Context context, Uri movieUri) {
        long _id = MovieDataContract.MovieEntry.getIdFromUri(movieUri);

        Cursor c = context.getContentResolver().query(
                MovieDataContract.MovieEntry.CONTENT_URI,
                new String[]{MovieDataContract.MovieEntry._ID, MovieDataContract.MovieEntry.COLUMN_MOVIE_ID},
                MovieDataContract.MovieEntry._ID + " = ?",
                new String[]{String.valueOf(_id)},
                null);

        if (c.moveToFirst()) {
            int movieIdIndex = c.getColumnIndex(MovieDataContract.MovieEntry.COLUMN_MOVIE_ID);
            return c.getInt(movieIdIndex);
        } else {
            return -1;
        }
    }


    public static ArrayList<MovieObj> MovieCursorToList(Cursor mCursor) {
        ArrayList<MovieObj> movieList = new ArrayList<MovieObj>();
        mCursor.moveToFirst();
        while (!mCursor.isAfterLast()) {
            MovieObj movie = new MovieObj();
            movie.setMovie_id(mCursor.getInt(mCursor.getColumnIndex(MovieDataContract.MovieEntry.COLUMN_MOVIE_ID)));
            movie.setMovie_name(mCursor.getString(mCursor.getColumnIndex(MovieDataContract.MovieEntry.COLUMN_TITLE)));
            movie.setMovie_rate(mCursor.getInt(mCursor.getColumnIndex(MovieDataContract.MovieEntry.COLUMN_VOTE_AVERAGE)));
            movie.setOverview_text(mCursor.getString(mCursor.getColumnIndex(MovieDataContract.MovieEntry.COLUMN_DESCRIPTION)));
            movie.setImg_poster(mCursor.getString(mCursor.getColumnIndex(MovieDataContract.MovieEntry.COLUMN_IMAGE_URL)));
            movie.setImage_film(mCursor.getString(mCursor.getColumnIndex(MovieDataContract.MovieEntry.COLUMN_FILM_URL)));
            movie.setPublish_time(mCursor.getColumnName(mCursor.getColumnIndex(MovieDataContract.MovieEntry.COLUMN_RELEASE_DATE)));
            movieList.add(movie);
            mCursor.moveToNext();
        }
        return movieList;
    }

    public static MovieObj getMovieByID(int id) {
        for (int i = 0; i < MainActivityFragment.MovieArray.size(); i++)
            if (id == MainActivityFragment.MovieArray.get(i).getMovie_id())
                return MainActivityFragment.MovieArray.get(i);
        return null;
    }

    public static int saveMovieToDB(Context mContext, MovieObj movie) {
        ContentValues cValues = new ContentValues();
        cValues.put(MovieDataContract.MovieEntry.COLUMN_MOVIE_ID, movie.getMovie_id());
        cValues.put(MovieDataContract.MovieEntry.COLUMN_TITLE, movie.getMovie_name());
        cValues.put(MovieDataContract.MovieEntry.COLUMN_FILM_URL, movie.getImage_film());
        cValues.put(MovieDataContract.MovieEntry.COLUMN_IMAGE_URL, movie.getImg_poster());
        cValues.put(MovieDataContract.MovieEntry.COLUMN_VOTE_AVERAGE, movie.getMovie_rate());
        cValues.put(MovieDataContract.MovieEntry.COLUMN_RELEASE_DATE, movie.getPublish_time());
        cValues.put(MovieDataContract.MovieEntry.COLUMN_DESCRIPTION, movie.getOverview_text());
        ContentValues c[] = {cValues};
        int itemsAdded = mContext.getContentResolver().bulkInsert(MovieDataContract.MovieEntry.CONTENT_URI, c);
        return itemsAdded;
    }

    public static int DelMovieFromDB(Context mContext, int ID) {
        int itemDeleted = mContext.getContentResolver().delete(MovieDataContract.MovieEntry.CONTENT_URI,
                MovieDataContract.MovieEntry.COLUMN_MOVIE_ID + "=?",
                new String[]{String.valueOf(ID)});
        return itemDeleted;
    }


    public static boolean IsFavroiut(Context mContext, int id) {
        Cursor detailsCursor = mContext.getContentResolver()
                .query(Functions.generateMovieUriFromID(id), null, null, null, null);
        if (detailsCursor.getCount() > 0)
            return true;
        return false;
    }
}
