package com.madwindevelopers.mwcalc;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

/*
 * Created by Andrew on 7/25/13.
 * Preferences Activity
 *
 */
public class PreferencesUI extends Activity {

    Boolean pass_wall_check;
    String TAG = "ACalc";
    SharedPreferences preferences;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferenceslayout);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        Boolean wallpaper_checker = extras.getBoolean("wallpaper_checker");

        if (getIntent().getExtras() == null){
            pass_wall_check = wallpaper_checker = false;
        } else {
            pass_wall_check = wallpaper_checker;
        }
        CheckBox wall_checkBox = (CheckBox) findViewById(R.id.wallCheckBox);
        if (wall_checkBox.isChecked()){
            pass_wall_check = true;
        }

        wall_checkBox.setOnClickListener(wallCheckBoxListener);
        wall_checkBox.setChecked(wallpaper_checker);

    }

    private View.OnClickListener wallCheckBoxListener = new View.OnClickListener() {
        public void onClick(View v) {
            CheckBox wall_checkBox = (CheckBox) findViewById(R.id.wallCheckBox);

            if (wall_checkBox.isChecked())
                pass_wall_check = true;
            else
                pass_wall_check = false;

            Log.d(TAG, "pass_wall_check from bundle = " + pass_wall_check);
        }

    };



    public boolean onOptionsItemSelected(MenuItem item){
        Intent back_to_calc = new Intent(this, InterfaceUI.class);
        back_to_calc.putExtra("wallpaper_checker", pass_wall_check);
        this.startActivity(back_to_calc);
        finish();
        return true;

    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent back_to_calc = new Intent(this, InterfaceUI.class);
            back_to_calc.putExtra("wallpaper_checker", pass_wall_check);
            this.startActivity(back_to_calc);

            finish();
        }
        return super.onKeyUp(keyCode, event);
    }

}
