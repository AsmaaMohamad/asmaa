package com.example.hp.mymoviesapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.hp.mymoviesapp.R;
import com.example.hp.mymoviesapp.fragments.Fragmentdetails;


/**
 * Created by hp on 09/11/2016.
 */
public class ActivityDetails extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedinstancestate) {
        super.onCreate(savedinstancestate);
        setContentView(R.layout.details_activity);
        if (savedinstancestate == null)
        {
            Bundle argume = new Bundle();
            argume.putSerializable(Fragmentdetails.ard_item_id, getIntent().getSerializableExtra(Fragmentdetails.ard_item_id));
            Fragmentdetails fragmnt = new Fragmentdetails();
            fragmnt.setArguments(argume);
            getSupportFragmentManager().beginTransaction().add(R.id.details_contain, fragmnt).commit();
        }
    }
}
