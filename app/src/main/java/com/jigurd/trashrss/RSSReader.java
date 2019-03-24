package com.jigurd.trashrss;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.net.URL;

import static android.content.Context.MODE_PRIVATE;
import static com.jigurd.trashrss.MainActivity.USERPREFS;

public class RSSReader extends AsyncTask<Void, Void, Boolean> {
    private String mURL;
    private Context mContext;

    RSSReader(Context iContext){
        mContext = iContext;
    }
    @Override
    protected Boolean doInBackground(Void... voids) {
        SharedPreferences uPrefs = mContext.getSharedPreferences(USERPREFS, MODE_PRIVATE);
        mURL = uPrefs.getString("URL", "");


        getFeed(mURL);
        return null;
    }

    private void getFeed(String iURL){
        URL feedUrl;
        try {
            feedUrl = new URL(iURL);

            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedUrl));

            Toast.makeText(mContext, feed.toString(), Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Parse error: "+e.getMessage());
        }
        System.out.println("test");
    }
}
