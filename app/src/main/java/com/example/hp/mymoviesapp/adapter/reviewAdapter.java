package com.example.hp.mymoviesapp.adapter;

/**
 * Created by hp on 11/11/2016.
 */

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.mymoviesapp.R;
import com.example.hp.mymoviesapp.models.modelreview;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
public class reviewAdapter extends RecyclerView.Adapter<reviewAdapter.ViewHolder> {
    private List<modelreview> setdata;
    public reviewAdapter(List<modelreview> setdata)
    {
        this.setdata = setdata;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.authr)TextView auther;
        @BindView(R.id.link)TextView link;
        @BindView(R.id.review)TextView review;

        public ViewHolder(View v)
        {
            super(v);
            ButterKnife.bind(this,v);
        }
        public TextView getAuther() {
            return auther;
        }
        public TextView getLink() {
            return link;
        }
        public TextView getReview() {
            return review;
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_review, parent, false);

        return  new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        if (setdata.get(position) != null) {
            Log.d("", "Element " + position + " set.");
            holder.getAuther().setText(setdata.get(position).getAuther());
            holder.getLink().setText(Html.fromHtml("<a href=\"" + setdata.get(position).getUrl() + "\">" + setdata.get(position).getUrl() + "</a> "));
            holder.getLink().setMovementMethod(LinkMovementMethod.getInstance());
            holder.getReview().setText(setdata.get(position).getContent());
        }
    }
    @Override
    public int getItemCount()
    {
        return setdata.size();
    }
}
