package com.jigurd.trashrss;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;
import com.sun.syndication.io.SyndFeedInput;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
public static final String USERPREFS = "UserPref";
//public static final List entryList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //initialize sharedPreferences with default
        SharedPreferences.Editor uPrefs = getSharedPreferences(USERPREFS, MODE_PRIVATE).edit();
        uPrefs.putString("URL", "https://www.sciencedaily.com/rss/all.xml");
        uPrefs.putString("freq", "60 minutes");
        uPrefs.putString("page", "20");
        uPrefs.apply();

        //Create settings FAB
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (MainActivity.this, PreferencesActivity.class);
                startActivity(i);
            }
        });

        //initialize text fields
        TextView urlView = findViewById(R.id.mUrlDisplay);
        TextView pageView = findViewById(R.id.mPageNumber);
        TextView freqView = findViewById(R.id.mUpdate);

        urlView.setText("");
        pageView.setText("60 minutes");
        freqView.setText("20");

    }

    @Override
    protected void onResume() {
        super.onResume();



        //update test shit
        TextView urlView = findViewById(R.id.mUrlDisplay);
        TextView pageView = findViewById(R.id.mPageNumber);
        TextView freqView = findViewById(R.id.mUpdate);

        SharedPreferences uPrefs = getSharedPreferences(USERPREFS, MODE_PRIVATE);
        urlView.setText(uPrefs.getString("URL", "Not Found!"));
        pageView.setText(uPrefs.getString("page", "Not Found!"));
        freqView.setText(uPrefs.getString("freq", "Not Found!"));

        //the broken stuff
        //new RSSReader(getBaseContext()).execute((Void) null);
    }
}
