package com.madwindevelopers.mwcalc;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Andrew on 9/2/13.
 */
public class ConversionSelection {



    public static void mDecision(String selection) {


        if (selection.equals("km to mi")) {
            double starting_value = UnitConverter.mStartingValue();
            UnitConverter.mUpdateDisplay(Double.toString(UnitConversion.mKmToMi(starting_value)));
        }
        if (selection.equals("mi to km")) {
            double starting_value = UnitConverter.mStartingValue();
            UnitConverter.mUpdateDisplay(Double.toString(UnitConversion.mMiToKm(starting_value)));
        }
        if (selection.equals("ft to m")) {
            double starting_value = UnitConverter.mStartingValue();
            UnitConverter.mUpdateDisplay(Double.toString(UnitConversion.mFtToM(starting_value)));
        }
        if (selection.equals("m to ft")) {
            double starting_value = UnitConverter.mStartingValue();
            UnitConverter.mUpdateDisplay(Double.toString(UnitConversion.mMToFt(starting_value)));
        }
        if (selection.equals("lb to kg")) {
            double starting_value = UnitConverter.mStartingValue();
            UnitConverter.mUpdateDisplay(Double.toString(UnitConversion.mLbToKg(starting_value)));
        }
        if (selection.equals("kg to lb")) {
            double starting_value = UnitConverter.mStartingValue();
            UnitConverter.mUpdateDisplay(Double.toString(UnitConversion.mKgToLb(starting_value)));
        }
        if (selection.equals("mph to kph")) {
            double starting_value = UnitConverter.mStartingValue();
            UnitConverter.mUpdateDisplay(Double.toString(UnitConversion.mMphToKph(starting_value)));
        }
        if (selection.equals("kph to mph")) {
            double starting_value = UnitConverter.mStartingValue();
            UnitConverter.mUpdateDisplay(Double.toString(UnitConversion.mKphToMph(starting_value)));
        }

    }




}
