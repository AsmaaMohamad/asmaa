package com.example.hp.mymoviesapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.hp.mymoviesapp.R;
/**
 * Created by hp on 09/11/2016.
 */
public class ActivityStart extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);
        Thread time=new Thread() {
            public void run() {
                try
                {
                    sleep(2000);
                }
                catch (InterruptedException x)
                {
                    x.printStackTrace();
                }
                finally {
                    Intent m=new Intent("mainActivity");
                    startActivity(m);
                }
            }

        };
        time.start();}
}
