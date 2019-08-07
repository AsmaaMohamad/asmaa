package com.example.hp.mymoviesapp.Store;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hp on 24/11/2016.
 */

public class Storefavorite {
    private Context mContext;
    private SharedPreferences sharedPreferences;

    public Storefavorite(Context mContext) {
        this.mContext = mContext;
        sharedPreferences = this.mContext.getSharedPreferences(
                "favoritePref", Context.MODE_APPEND
        );
    }

    public List<String> findAllFavoriteMovies(){
        Map<String , ?> iDsMap = this.sharedPreferences.getAll();

        List<String> moviesIDs = new ArrayList<>();
        for (String key: iDsMap.keySet()
             ) {

            moviesIDs.add(this.sharedPreferences.getString(key, ""));

        }

        return moviesIDs;
    }

    public void SaveMovieID(String movieID){
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putString(movieID, movieID);
        editor.apply();
    }

    public String getMovieID(String movieID){
        return this.sharedPreferences.getString(movieID, "");
    }
}
