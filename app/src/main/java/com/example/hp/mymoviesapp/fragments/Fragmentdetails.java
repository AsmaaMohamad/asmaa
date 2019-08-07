package com.example.hp.mymoviesapp.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.hp.mymoviesapp.R;
import com.example.hp.mymoviesapp.Store.Storefavorite;
import com.example.hp.mymoviesapp.adapter.reviewAdapter;
import com.example.hp.mymoviesapp.app.AppController;
import com.example.hp.mymoviesapp.json.JsonParser;
import com.example.hp.mymoviesapp.models.modelmovie;
import com.example.hp.mymoviesapp.models.modelreview;
import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.hp.mymoviesapp.R.id.imageView;

/**
 * Created by hp on 04/11/2016.
 */

public class Fragmentdetails extends Fragment {

    @BindView(R.id.textView)
    TextView titel;
    @BindView(R.id.txtview3)
    TextView releasDate;
    @BindView(R.id.txtview2)
    TextView overView;
    @BindView(R.id.ratingBar)
    RatingBar rate;
    @BindView(imageView)
    ImageView background;
    @BindView(R.id.button)
    Button review;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView1;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton fab;
    @BindView(R.id.imageButton)
    Button button;

    public static final String ard_item_id = "item_id";
    private static modelmovie model;
    private String video_id;
    protected List<modelreview> reviewData;
    private reviewAdapter reviewAdapter;
    public LinearLayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reviewData = new ArrayList<>();

        if (getArguments().containsKey(ard_item_id)) {
            model = (modelmovie) getArguments().getSerializable(ard_item_id);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detials_fragment, container, false);
        ButterKnife.bind(this, view);

        recyclerView1.setHasFixedSize(true);
        reviewAdapter = new reviewAdapter(reviewData);
        recyclerView1.setAdapter(reviewAdapter);

        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestReviews(model.getID(), 1);

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestReviews(model.getID(), 0);
                watchYoutubeVideo(video_id);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Storefavorite(getActivity()).SaveMovieID(model.getID());
            }
        });

        titel.setText(model.getOrgnaltitl());
        releasDate.setText(model.getRelease_date());
        overView.setText(model.getOverview());
        rate.setRating(Float.parseFloat(model.getVote_average()) / 2);

        Picasso.with(getActivity()).load(model.getBackdrop_path()).into(background);
        return view;
    }

    public void watchYoutubeVideo(String id) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=" + id));
            startActivity(intent);
        }
    }

    public void requestReviews(String id, int i) {
        String Url = "";
        if (i == 0) {
            Url = "http://api.themoviedb.org/3/movie/" + id + "/videos?api_key=b18d2dc479e956b1b0a211f54153cfad";
        } else {
            Url = "http://api.themoviedb.org/3/movie/" + id + "/reviews?api_key=b18d2dc479e956b1b0a211f54153cfad";
        }
        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Cache.Entry entry = cache.get(Url);
        if (entry != null) {
            try {
                String data = new String(entry.data, "UTF-8");
                if (i == 0) {
                    video_id = JsonParser.getVideoID(data);
                }
                clearDataSet();
                Iterator iterator = JsonParser.parseDataToReview(data).iterator();

                while (iterator.hasNext()) {
                    modelreview rMovie = (modelreview) iterator.next();
                    reviewData.add(rMovie);
                    reviewAdapter.notifyItemInserted(reviewData.size() - 1);
                }

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
        StringRequest strReq = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.d("response", response);
                clearDataSet();
                Iterator iterator = JsonParser.parseDataToReview(response).iterator();
                while (iterator.hasNext()) {
                    modelreview rMovie = (modelreview) iterator.next();
                    reviewData.add(rMovie);
                    reviewAdapter.notifyItemInserted(reviewData.size() - 1);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("response", error.toString());
            }
        });
        AppController.getInstance().addToRequestQueue(strReq);
    }

    private void clearDataSet() {
        if (reviewData != null) {
            reviewData.clear();
            reviewAdapter.notifyDataSetChanged();
        }
    }
}


