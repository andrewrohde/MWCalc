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
    public static double mTspToTbsp (double starting_value) {
        double final_number = starting_value * 0.33333;
        return final_number;
    }
    public static double mTbspToTsp (double starting_value) {
        double final_number = starting_value * 3;
        return final_number;
    }
    public static double mCupsToTbsp (double starting_value) {
        double final_number = starting_value * 16;
        return final_number;
    }
     public static double mTbspToCups (double starting_value) {
        double final_number = starting_value * 0.0625;
        return final_number;
    }
    public static double mInToFt (double starting_value) {
        double final_number = starting_value * 0.0833333;
        return final_number;
    }
    public static double mFtToIn (double starting_value) {
        double final_number = starting_value * 12;
        return final_number;
    }
    public static double mGalToL (double starting_value) {
        double final_number = starting_value * 3.78541;
        return final_number;
    }
    public static double mLToGal (double starting_value) {
        double final_number = starting_value * 0.264172;
        return final_number;
    }


}