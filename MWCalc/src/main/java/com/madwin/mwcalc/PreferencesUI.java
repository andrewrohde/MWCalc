package com.madwin.mwcalc;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import com.madwin.mwcalc.R;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/*
 * Created by Andrew on 7/25/13.
 * Preferences Activity
 *
 */
public class PreferencesUI extends Activity {

  /*  String TAG = "MWCalc";
    SharedPreferences preferences;
    File file;
    //String preferences_location = "/data/data/com.madwin.MWCalc/preferences/";
    String preferences_filename = "preferences.txt";
    String layout_preference_filename = "layout.txt";
    String background_save_string = "";
    String background_preference = "background_enabled=";
    String wallpaper_value = "";
    String layout_preference = "layout_side=";
    String layout_save_string = "";
*/
    Boolean wallpaper_checker;
    Boolean layout_side;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferenceslayout);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        wallpaper_checker = bundle.getBoolean("Wall", false);
        layout_side = bundle.getBoolean("Side", false);




        /*if (SavePreferences.mCheckSettingsFile(preferences_filename)){
            background_save_string = SavePreferences.getPreference(background_preference, preferences_filename);
            Log.d(TAG, "background_save_string = " + background_save_string);

            wallpaper_checker = !Character.toString((background_save_string.charAt(
                    background_save_string.length() - 1))).equals("0");


        }
        if (SavePreferences.mCheckSettingsFile(layout_preference_filename)){
            layout_save_string = SavePreferences.getPreference(layout_preference, layout_preference_filename);
            Log.d(TAG, "layout_save_string = " + layout_save_string);
            hand_checker = Character.toString((layout_save_string.charAt(
                    layout_save_string.length() - 1))).equals("0");

        }

        Log.d(TAG, "hand_checker = " + hand_checker);
        Log.d(TAG, "wallpaper_checker = " + wallpaper_checker);
*/
        CheckBox wall_checkBox = (CheckBox) findViewById(R.id.wallCheckBox);
        CheckBox hand_checkBox = (CheckBox) findViewById(R.id.handCheckBox);
        wall_checkBox.setChecked(wallpaper_checker);
        hand_checkBox.setChecked(layout_side);

        wall_checkBox.setOnClickListener(wallCheckBoxListener);
        hand_checkBox.setOnClickListener(handCheckBoxListener);

    }

    private View.OnClickListener wallCheckBoxListener = new View.OnClickListener() {
        public void onClick(View v) {
            CheckBox wall_checkBox = (CheckBox) findViewById(R.id.wallCheckBox);
            wallpaper_checker = !wallpaper_checker;
      //      if (wall_checkBox.isChecked()) {
      //          wallpaper_value = "1";
      //      } else{
       //         wallpaper_value = "0";
       //     }
       //     Log.d(TAG, "wallpaper_checker when button pressed = " + wallpaper_checker);
           // SavePreferences.mSaveToFile(background_preference, wallpaper_value, preferences_filename);
        }

    };

    private View.OnClickListener handCheckBoxListener = new View.OnClickListener() {
        public void onClick(View v) {
            CheckBox hand_checkBox = (CheckBox) findViewById(R.id.handCheckBox);
            layout_side = !layout_side;
/*            if (hand_checkBox.isChecked()) {
                layout_side = false;
            } else{
                layout_side = true;
            }
            Log.d(TAG, "hand_checker after button pressed = " + hand_checker);
            //SavePreferences.mSaveToFile(layout_preference, layout_side, layout_preference_filename);
*/
        }

    };

    public boolean onOptionsItemSelected(MenuItem item){
        Intent back_to_calc = new Intent(this, InterfaceUI.class);
        back_to_calc.putExtra("Wall", wallpaper_checker);
        back_to_calc.putExtra("Side", layout_side);
        this.startActivity(back_to_calc);
        finish();
        return true;

    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent back_to_calc = new Intent(this, InterfaceUI.class);
            back_to_calc.putExtra("Wall", wallpaper_checker);
            back_to_calc.putExtra("Side", layout_side);
            this.startActivity(back_to_calc);
            finish();
        }
        return super.onKeyUp(keyCode, event);
    }
}