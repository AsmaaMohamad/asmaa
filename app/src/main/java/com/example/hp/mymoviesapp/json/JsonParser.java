package com.example.hp.mymoviesapp.json;


import com.example.hp.mymoviesapp.models.modelmovie;
import com.example.hp.mymoviesapp.models.modelreview;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 06/11/2016.
 */

public final class JsonParser {
    public static List<modelmovie> parseStringToJson(String data) {
        List<modelmovie> modelMovies;
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.optJSONArray("results");
            modelMovies = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject movieJsonObject = jsonArray.getJSONObject(i);
                String ID = movieJsonObject.optString("id");
                String poster_path = "http://image.tmdb.org/t/p/w185/" + movieJsonObject.optString("poster_path");
                String backdrop_path = "http://image.tmdb.org/t/p/w185/" + movieJsonObject.optString("backdrop_path");
                String overview = movieJsonObject.optString("overview");
                String release_date = movieJsonObject.optString("release_date");
                String vote_average = movieJsonObject.optString("vote_average");
                String original_title = movieJsonObject.optString("original_title");
                modelmovie movie = new modelmovie();
                movie.setID(ID);
                movie.setPoster(poster_path);
                movie.setOverview(overview);
                movie.setRelease_date(release_date);
                movie.setVote_average(vote_average);
                movie.setOriginal_title(original_title);
                movie.setBackdrop_path(backdrop_path);
                modelMovies.add(movie);
            }

            return modelMovies;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<modelreview> parseDataToReview(String data) {
        List<modelreview> reviewMovies;
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.optJSONArray("results");
            reviewMovies = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject reviewJsonObject = jsonArray.getJSONObject(i);
                String auther = reviewJsonObject.optString("author");
                String content = reviewJsonObject.optString("content");
                String url = reviewJsonObject.optString("url");
                modelreview review = new modelreview();
                review.setAuther(auther);
                review.setContent(content);
                review.setUrl(url);
                reviewMovies.add(review);
            }
            return reviewMovies;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getVideoID(String data) {
        String key = "";
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.optJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject JsonObject = jsonArray.getJSONObject(i);
                String type = JsonObject.optString("type");
                if (type.equalsIgnoreCase("Trailer"))
                    key = JsonObject.optString("key");
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return key;
    }
}