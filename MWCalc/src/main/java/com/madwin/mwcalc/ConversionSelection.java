package com.madwin.mwcalc;

/**
 * Created by Andrew on 9/2/13.
 */
public class ConversionSelection {



    public static void mDecision(String selection) {


        if (selection.equals("km to mi")) {
            double starting_value = com.madwin.mwcalc.UnitConverter.mStartingValue();
            com.madwin.mwcalc.UnitConverter.mUpdateDisplay(Double.toString(
                    com.madwin.mwcalc.UnitConversion.mKmToMi(starting_value)));
        }
        if (selection.equals("mi to km")) {
            double starting_value = com.madwin.mwcalc.UnitConverter.mStartingValue();
            com.madwin.mwcalc.UnitConverter.mUpdateDisplay(Double.toString(
                    com.madwin.mwcalc.UnitConversion.mMiToKm(starting_value)));
        }
        if (selection.equals("ft to m")) {
            double starting_value = com.madwin.mwcalc.UnitConverter.mStartingValue();
            com.madwin.mwcalc.UnitConverter.mUpdateDisplay(Double.toString(
                    com.madwin.mwcalc.UnitConversion.mFtToM(starting_value)));
        }
        if (selection.equals("m to ft")) {
            double starting_value = com.madwin.mwcalc.UnitConverter.mStartingValue();
            com.madwin.mwcalc.UnitConverter.mUpdateDisplay(Double.toString(
                    com.madwin.mwcalc.UnitConversion.mMToFt(starting_value)));
        }
        if (selection.equals("lb to kg")) {
            double starting_value = com.madwin.mwcalc.UnitConverter.mStartingValue();
            com.madwin.mwcalc.UnitConverter.mUpdateDisplay(Double.toString(
                    com.madwin.mwcalc.UnitConversion.mLbToKg(starting_value)));
        }
        if (selection.equals("kg to lb")) {
            double starting_value = com.madwin.mwcalc.UnitConverter.mStartingValue();
            com.madwin.mwcalc.UnitConverter.mUpdateDisplay(Double.toString(
                    com.madwin.mwcalc.UnitConversion.mKgToLb(starting_value)));
        }
        if (selection.equals("mph to kph")) {
            double starting_value = com.madwin.mwcalc.UnitConverter.mStartingValue();
            com.madwin.mwcalc.UnitConverter.mUpdateDisplay(Double.toString(
                    com.madwin.mwcalc.UnitConversion.mMphToKph(starting_value)));
        }
        if (selection.equals("kph to mph")) {
            double starting_value = com.madwin.mwcalc.UnitConverter.mStartingValue();
            com.madwin.mwcalc.UnitConverter.mUpdateDisplay(Double.toString(
                    com.madwin.mwcalc.UnitConversion.mKphToMph(starting_value)));
        }

    }




}
