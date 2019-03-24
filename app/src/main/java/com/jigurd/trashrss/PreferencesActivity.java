package com.jigurd.trashrss;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import static com.jigurd.trashrss.MainActivity.USERPREFS;

public class PreferencesActivity extends AppCompatActivity {

    public String URL;
    public String freq;
    public String page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        //retrieve user preference information
        //final Intent i = getIntent();
        //final String USERPREFS = i.getStringExtra("USERPREFS");

        //grab sharedPreferences
        SharedPreferences uPrefs = getSharedPreferences(USERPREFS, MODE_PRIVATE);
        URL = uPrefs.getString("URL", "No URL was found!");
        freq = uPrefs.getString("freq", "60 minutes");
        page = uPrefs.getString("page", "20");


        //create URL field
        final EditText mURL = findViewById(R.id.field_URL);
        //set it to the text from the preference
        mURL.setText(URL);

        //take input from the URL field
        mURL.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                URL = mURL.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //create item spinner
        String[] mPageOptions = {"20", "50", "100", "200"};
        final ArrayAdapter<String> pageAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_dropdown_item, mPageOptions);
        final Spinner mPageSpinner = findViewById(R.id.pagedropdown);
        mPageSpinner.setAdapter(pageAdapter);

        //update it to the preference
        mPageSpinner.setSelection(getIndex(mPageSpinner, page));

        //create frequency spinner
        String[] mFreqOptions = {"10 minutes", "60 minutes", "24 hours"};
        final ArrayAdapter<String> freqAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_dropdown_item, mFreqOptions);
        final Spinner mFreqSpinner = findViewById(R.id.freqdropdown);
        mFreqSpinner.setAdapter(freqAdapter);

        //update it to the preference
        mFreqSpinner.setSelection(getIndex(mFreqSpinner, freq));

        //create apply button
        final Button mApplyButton = findViewById(R.id.applyButton);
        mApplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            //when clicked
            public void onClick(View v) {
                //apply preferences
                SharedPreferences.Editor uPrefs = getSharedPreferences(USERPREFS, MODE_PRIVATE).edit();
                uPrefs.putString("URL", URL);
                uPrefs.putString("freq", mFreqSpinner.getSelectedItem().toString());
                uPrefs.putString("page", mPageSpinner.getSelectedItem().toString());
                uPrefs.apply();
                //notify user of this
                Toast.makeText(getApplicationContext(), "Preferences Updated.", Toast.LENGTH_SHORT).show();
                //return
                finish();
            }
        });
    }

    //gets the index of a given item in a spinner
    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return 0;
    }
}
