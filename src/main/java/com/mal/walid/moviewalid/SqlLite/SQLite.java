package com.mal.walid.moviewalid.SqlLite;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Khaled on 2017-06-07.
 */

public class SQLite extends android.database.sqlite.SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MoviesDB.db";
    private static final int DATABASE_VERSION = 2;

    public SQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String createMoviesTable = "CREATE TABLE " + MovieDataContract.MovieEntry.TABLE_NAME + " ( "
                + MovieDataContract.MovieEntry._ID + " INTEGER PRIMARY KEY, "
                + MovieDataContract.MovieEntry.COLUMN_MOVIE_ID + " INTEGER NOT NULL, "
                + MovieDataContract.MovieEntry.COLUMN_TITLE + " TEXT NOT NULL, "
                + MovieDataContract.MovieEntry.COLUMN_RELEASE_DATE + " TEXT NOT NULL, "
                + MovieDataContract.MovieEntry.COLUMN_VOTE_AVERAGE + " REAL NOT NULL, "
                + MovieDataContract.MovieEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, "
                + MovieDataContract.MovieEntry.COLUMN_IMAGE_URL + " TEXT NOT NULL, "
                + MovieDataContract.MovieEntry.COLUMN_FILM_URL + " TEXT NOT NULL, "
                + " UNIQUE (" + MovieDataContract.MovieEntry.COLUMN_TITLE + ") ON CONFLICT REPLACE);";

        db.execSQL(createMoviesTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MovieDataContract.MovieEntry.TABLE_NAME);
        onCreate(db);

    }

}
