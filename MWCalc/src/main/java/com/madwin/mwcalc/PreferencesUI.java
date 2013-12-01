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

    String TAG = "MWCalc";
    Boolean wallpaper_checker;
    Boolean layout_side;
    SharedPreferences settings;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferenceslayout);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        settings = getSharedPreferences("PREFS", 0);
        wallpaper_checker = settings.getBoolean("Wall", false);
        layout_side = settings.getBoolean("Side", false);

        CheckBox wall_checkBox = (CheckBox) findViewById(R.id.wallCheckBox);
        CheckBox hand_checkBox = (CheckBox) findViewById(R.id.handCheckBox);
        wall_checkBox.setChecked(wallpaper_checker);
        hand_checkBox.setChecked(layout_side);

        wall_checkBox.setOnClickListener(wallCheckBoxListener);
        hand_checkBox.setOnClickListener(handCheckBoxListener);

    }

    private View.OnClickListener wallCheckBoxListener = new View.OnClickListener() {
        public void onClick(View v) {
            wallpaper_checker = !wallpaper_checker;
        }

    };

    private View.OnClickListener handCheckBoxListener = new View.OnClickListener() {
        public void onClick(View v) {
            layout_side = !layout_side;
        }

    };

    public boolean onOptionsItemSelected(MenuItem item){
        Intent back_to_calc = new Intent(this, InterfaceUI.class);
        mSaveSettings();
        this.startActivity(back_to_calc);
        finish();
        return true;

    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent back_to_calc = new Intent(this, InterfaceUI.class);
            mSaveSettings();
            this.startActivity(back_to_calc);
            finish();
        }
        return super.onKeyUp(keyCode, event);
    }

    private void mSaveSettings() {

        settings = getSharedPreferences("PREFS", 0);
        SharedPreferences.Editor pref_editor = settings.edit();
        pref_editor.putBoolean("Wall", wallpaper_checker).commit();
        pref_editor.putBoolean("Side", layout_side).commit();

    }
}