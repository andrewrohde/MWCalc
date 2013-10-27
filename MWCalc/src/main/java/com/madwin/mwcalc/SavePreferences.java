package com.madwin.mwcalc;

import android.util.Log;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Andrew on 9/21/13.
 */
public class SavePreferences {

    public static void mSaveToFile(String preference_string, String preference_value, String preference_file_name){

        String preference_save_string = preference_string + preference_value;

        FileOutputStream fos;
        File file;
        try{
            file = new File ("/data/data/com.madwin.mwcalc/files/" + preference_file_name);
            fos = new FileOutputStream(file);

            if (!file.exists()) {
                    file.createNewFile();
            }

            fos.write(preference_save_string.getBytes());
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static Boolean mCheckSettingsFile(String preference_file_name){
        File file = new File("/data/data/com.madwin.mwcalc/files/" + preference_file_name);

        return file.exists();
    }
    static String getPreference(String get_preference, String preference_file_name) {
        String TAG = "MWCalc";
        File file = new File("/data/data/com.madwin.mwcalc/files/" + preference_file_name);

        String ret = "";

        try {
            InputStream inputStream = new FileInputStream(file);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = get_preference;
                StringBuilder stringBuilder = new StringBuilder();


                while ( (receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("save preferences activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("save preferences activity", "Can not read file: " + e.toString());
        }
        return ret;
    }

}
