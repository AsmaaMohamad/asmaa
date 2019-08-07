package com.example.hp.mymoviesapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.mymoviesapp.R;
import com.example.hp.mymoviesapp.activities.ActivityDetails;
import com.example.hp.mymoviesapp.activities.MainActivity;
import com.example.hp.mymoviesapp.fragments.Fragmentdetails;
import com.example.hp.mymoviesapp.models.modelmovie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.ContentValues.TAG;

/**
 * Created by hp on 04/11/2016.
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
    private List<modelmovie> setdata;
    private static Context cantxt;
    public ItemsAdapter(Context contxt,List<modelmovie> setdata)
    {
        cantxt =contxt;
        this.setdata = setdata;
    }
            public  class ViewHolder extends RecyclerView.ViewHolder
            {
                @BindView(R.id.image)ImageView poster;
                @BindView(R.id.title)TextView title;
                @BindView(R.id.text)TextView text;
                public ViewHolder(View x)
                {
                    super(x);
                    x.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d(TAG, "elemnt " + getPosition() + " clck.");
                            if (MainActivity.towpan)
                            {
                                Bundle argm = new Bundle();
                                argm.putSerializable(Fragmentdetails.ard_item_id, setdata.get(getPosition()));

                                Fragmentdetails fragment = new Fragmentdetails();
                                fragment.setArguments(argm);
                                ((FragmentActivity) cantxt).getSupportFragmentManager().beginTransaction().replace(R.id.details_contain, fragment).commit();
                            }
                            else
                            {
                                Context cntxt2 = v.getContext();
                                Intent intn = new Intent(cntxt2, ActivityDetails.class);
                                intn.putExtra(Fragmentdetails.ard_item_id,  setdata.get(getPosition()));
                                cntxt2.startActivity(intn);
                            }
                        }
                    });
                    ButterKnife.bind(this,x);

                }

                public ImageView getPoster() {
                    return poster;
                }

                public TextView getTitle() {

                    return title;
                }
                public void setTitle(TextView titl) {
                    this.title = titl;
                }
                public TextView getText() {
                    return text;
                }
                public void setText(TextView txt) {
                    this.text = txt;
                }
            }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup prnt, int viewtyp)
    {
        View x = LayoutInflater.from(prnt.getContext()).inflate(R.layout.item, prnt, false);
        return  new ViewHolder(x);
    }
    @Override
    public void onBindViewHolder(final ViewHolder hold, int postion)
    {
        if (setdata.get(postion) != null)
        {
            Log.d("", "element " + postion + " set.");
            hold.getTitle().setText(setdata.get(postion).getOrgnaltitl());
            hold.getText().setText(setdata.get(postion).getOverview());
            if (setdata.get(postion).getPoster() != null)
            {
                Picasso.with(cantxt).load(setdata.get(postion).getPoster()).into(hold.getPoster());
            }
        }
    }
    @Override
    public int getItemCount() {
                return setdata.size();
            }
}
