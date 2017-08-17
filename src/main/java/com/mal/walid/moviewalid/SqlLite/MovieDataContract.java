package com.mal.walid.moviewalid.SqlLite;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Khaled on 2017-06-06.
 */

public class MovieDataContract {


    public static class MovieDataEntity implements BaseColumns {

        public static final String CONTENT_AUTHORITY = "com.mal.walid.moviewalid";

        public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

        public static final String PATH_MOVIE = "MovieData";


    }

    public static final class MovieEntry implements BaseColumns {

        public static final Uri CONTENT_URI = MovieDataEntity.BASE_CONTENT_URI.buildUpon().appendPath(MovieDataEntity.PATH_MOVIE).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + MovieDataEntity.CONTENT_AUTHORITY + "/" + MovieDataEntity.PATH_MOVIE;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + MovieDataEntity.CONTENT_AUTHORITY + "/" + MovieDataEntity.PATH_MOVIE;

        public static final String TABLE_NAME = "movies";

        // columns
        public static final String COLUMN_MOVIE_ID = "movie_id"; // the movie id from the backend
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_RELEASE_DATE = "release_date";
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";
        public static final String COLUMN_DESCRIPTION = "desc";
        public static final String COLUMN_IMAGE_URL = "image_url";
        public static final String COLUMN_FILM_URL = "poster_path";


        public static Uri buildMovieWithId(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }


        public static String getPosterUrlFromUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        public static long getIdFromUri(Uri uri) {
            return ContentUris.parseId(uri);
        }
    }

}
