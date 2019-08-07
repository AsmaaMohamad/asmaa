package com.example.hp.mymoviesapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.hp.mymoviesapp.R;
import com.example.hp.mymoviesapp.fragments.Itemsfragment;

import butterknife.BindView;
import butterknife.ButterKnife;
public class MainActivity extends AppCompatActivity
{
    public static boolean towpan;
    @BindView(R.id.toolbar)Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if (findViewById(R.id.details_contain) != null)
        {
            towpan = true;}
        Itemsfragment listitemsfragment=new Itemsfragment();
        getSupportFragmentManager().beginTransaction().add(R.id.items_contain, listitemsfragment).commit();
    }
}
