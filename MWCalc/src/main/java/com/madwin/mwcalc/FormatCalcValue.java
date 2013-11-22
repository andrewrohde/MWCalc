package com.madwin.mwcalc;

import android.util.Log;


/**
 * Created by Andrew on 8/24/13.
 */
public class FormatCalcValue {
    private static final String TAG = FormatCalcValue.class.getName();

    public static String mRemoveTrailingPointZero(String current_display_value) {

        Log.d(TAG, "current_display_value into trailing = " + current_display_value);

        if (current_display_value.length() > 2 && !current_display_value.equals(null)) {
            int decimal_position = current_display_value.length() - 1;
            int zero_position = current_display_value.length();
            String trailing_point_zero = current_display_value.substring(decimal_position -1, zero_position);
            if (trailing_point_zero.equals(".0")) {
                current_display_value = current_display_value.substring(0, decimal_position - 1);
            }
        }
        return current_display_value;

    }

    public static String mCalcNullChecker (String current_display_value) {

        if (current_display_value == null) {
            current_display_value = "0";
        }
        return current_display_value;
    }

    public static String mShortenForTextView (String current_display_value) {

        if (current_display_value.length() > 13 ){
            return current_display_value.substring(0,9) +
                    current_display_value.substring(current_display_value.indexOf("E"),
                            current_display_value.length());
        }
        return current_display_value;
    }

}