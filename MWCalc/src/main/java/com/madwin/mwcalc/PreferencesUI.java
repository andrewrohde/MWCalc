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
    SharedPreferences preferences;
    File file;
    //String preferences_location = "/data/data/com.madwin.MWCalc/preferences/";
    String preferences_filename = "preferences.txt";
    String background_save_string = "";
    String background_preference = "background_enabled=";
    Boolean wallpaper_checker = false;





    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferenceslayout);

        //mCreatePreferencesFile();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (mCheckSettingsFile()){
        background_save_string = getPreference(
                background_preference, preferences_filename);
        }

        //Log.d(TAG, "background_save_string.charAt(background_save_string.length() - 1) = "
        //        + background_save_string.charAt(background_save_string.length() - 1));
        if (mCheckSettingsFile()) {
        wallpaper_checker = !Character.toString((background_save_string.charAt(
                background_save_string.length() - 1))).equals("0");
        }
        Log.d(TAG, "background_save_string = " + background_save_string);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        /*Bundle extras = getIntent().getExtras();
        wallpaper_checker = extras.getBoolean("wallpaper_checker");

        //pass and receive left right ui variable

        if (getIntent().getExtras() == null){
            pass_wall_check = wallpaper_checker = false;
        } else {
            pass_wall_check = wallpaper_checker;
        }*/
        CheckBox wall_checkBox = (CheckBox) findViewById(R.id.wallCheckBox);
        wall_checkBox.setChecked(wallpaper_checker);

      /*  if (wall_checkBox.isChecked()){
            pass_wall_check = true;
        }*/

        wall_checkBox.setOnClickListener(wallCheckBoxListener);



        // Add setting for left - right hand calculator layout



    }

    void mReadPreferences() {


    }

    private View.OnClickListener wallCheckBoxListener = new View.OnClickListener() {
        public void onClick(View v) {
            CheckBox wall_checkBox = (CheckBox) findViewById(R.id.wallCheckBox);

            if (wall_checkBox.isChecked()) {
                wallpaper_checker = true;
            } else{
                wallpaper_checker = false;
            }
            Log.d(TAG, "wallpaper_checker when button pressed = " + wallpaper_checker);
        }

    };



    public boolean onOptionsItemSelected(MenuItem item){
        Intent back_to_calc = new Intent(this, InterfaceUI.class);
    //    back_to_calc.putExtra("wallpaper_checker", wallpaper_checker);

        mSaveToFile();
        this.startActivity(back_to_calc);
        finish();
        return true;

    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent back_to_calc = new Intent(this, InterfaceUI.class);
         //   back_to_calc.putExtra("wallpaper_checker", pass_wall_check);

            mSaveToFile();
            this.startActivity(back_to_calc);
            finish();
        }
        return super.onKeyUp(keyCode, event);
    }

    public void mSaveToFile() {

       // mCreatePreferencesFile();

        int background_save_value;

        if (wallpaper_checker == true) {
            background_save_value = 1;
        } else {
            background_save_value = 0;
        }


        background_save_string = background_preference + background_save_value;


        try{

            FileOutputStream fos = openFileOutput(preferences_filename, Context.MODE_PRIVATE);
            fos.write(background_save_string.getBytes());
            fos.close();
/*
            OutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream(file, true));
            fileOutputStream.write(background_save_string.getBytes());
            //fileOutputStream.write(System.getProperty("line.separator").getBytes());
            fileOutputStream.close();
*/
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    String getPreference(String get_preference, String preferences_filename) {
        String TAG = "MWCalc";

        String ret = "";

        try {
            InputStream inputStream = openFileInput(preferences_filename);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = get_preference;
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        Log.d(TAG, "read_preference = " + ret);

        return ret;
    }

    Boolean mCheckSettingsFile(){
        File file = new File("/data/data/com.madwin.mwcalc/files/preferences.txt");

        return file.exists();
    }




   /* public void mCreatePreferencesFile() {

        file = new File(preferences_location, preferences_filename);

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("ERRR", "Could not create file",e);
        }

    }
*/

}