package com.madwin.mwcalc;


/*
 * Created by Andrew on 9/1/13.
 * This handles tasks from all buttons
 */


public class UnitConversion extends com.madwin.mwcalc.UnitConverter {

    public static double mKmToMi (double starting_value) {
        double final_number = starting_value * 0.621371;
        return final_number;
    }
    public static double mMiToKm (double starting_value) {
        double final_number = starting_value * 1.60934;
        return final_number;
    }
    public static double mFtToM (double starting_value) {
        double final_number = starting_value * 0.3048;
        return final_number;
    }
    public static double mMToFt (double starting_value) {
        double final_number = starting_value * 3.28084;
        return final_number;
    }
    public static double mLbToKg (double starting_value) {
        double final_number = starting_value * 0.453592;
        return final_number;
    }
    public static double mKgToLb (double starting_value) {
        double final_number = starting_value * 2.20462;
        return final_number;
    }
    public static double mMphToKph (double starting_value) {
        double final_number = starting_value * 1.60934;
        return final_number;
    }
    public static double mKphToMph (double starting_value) {
        double final_number = starting_value * 0.621371;
        return final_number;
    }


}