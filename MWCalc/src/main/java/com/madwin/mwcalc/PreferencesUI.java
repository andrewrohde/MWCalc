package com.madwin.mwcalc;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import net.margaritov.preference.colorpicker.ColorPickerPreference;

/*
 * Created by Andrew on 7/25/13.
 * Preferences Activity
 *
 */
public class PreferencesUI extends PreferenceActivity {

    //String TAG = "MWCalc";
    Boolean wallpaper_checker;
    Boolean layout_side;
    SharedPreferences settings;
    private ColorPickerPreference mColorPicker;
    int intButtonColor;
    int intTextColor;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
      //  ActionBar actionBar = getActionBar();
      //  actionBar.setDisplayHomeAsUpEnabled(true);

     //   settings = getSharedPreferences("PREFS", 0);
     //   wallpaper_checker = settings.getBoolean("Wall", false);
     //   layout_side = settings.getBoolean("Side", false);
     //   intButtonColor = preferences.getInt("ButtonColor", getResources().getInteger(R.color.LightBlue));
     //   intTextColor = preferences.getInt("TextColor", getResources().getInteger(R.color.LightBlue));





     //   CheckBoxPreference wall_checkBox = (CheckBoxPreference)findPreference(wallCheckBox)
     //   CheckBoxPreference hand_checkBox = (CheckBoxPreference) findViewById(R.id.handCheckBox);
     //   wall_checkBox.setChecked(wallpaper_checker);
    //    hand_checkBox.setChecked(layout_side);

     //   wall_checkBox.setOnClickListener(wallCheckBoxListener);
     //   hand_checkBox.setOnClickListener(handCheckBoxListener);

    }

 /*   private View.OnClickListener wallCheckBoxListener = new View.OnClickListener() {
        public void onClick(View v) {
            wallpaper_checker = !wallpaper_checker;
        }

    };

    private View.OnClickListener handCheckBoxListener = new View.OnClickListener() {
        public void onClick(View v) {
            layout_side = !layout_side;
        }

    };
*/



    public boolean onOptionsItemSelected(MenuItem item){
        Intent back_to_calc = new Intent(this, InterfaceUI.class);
        //mSaveSettings();
        this.startActivity(back_to_calc);
        finish();
        return true;

    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent back_to_calc = new Intent(this, InterfaceUI.class);
            //mSaveSettings();
            this.startActivity(back_to_calc);
            finish();
        }
        return super.onKeyUp(keyCode, event);
    }

   /* private void mSaveSettings() {

        settings = getSharedPreferences("PREFS", 0);
        SharedPreferences.Editor pref_editor = settings.edit();
        pref_editor.putBoolean("Wall", wallpaper_checker).commit();
        pref_editor.putBoolean("Side", layout_side).commit();

    }
    */
}