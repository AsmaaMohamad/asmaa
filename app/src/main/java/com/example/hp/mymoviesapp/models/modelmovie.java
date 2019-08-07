package com.example.hp.mymoviesapp.models;

import java.io.Serializable;

/**
 * Created by hp on 04/11/2016.
 */

public class modelmovie implements Serializable {
    private String original_title;
    private String poster;
    private String overview ;
    private String vote_average ;
    private String release_date ;
    private String backdrop_path;
    private String ID;




    public modelmovie(String original_title, String poster, String overview, String vote_average, String release_date, String backdrop_path) {
        this.original_title = original_title;
        this.poster = poster;
        this.overview = overview;
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.backdrop_path=backdrop_path;

    }

    public modelmovie() {

    }

    public String getOrgnaltitl() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

}
