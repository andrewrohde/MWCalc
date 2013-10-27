package com.madwin.mwcalc;

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
        if (selection.equals("tsp to Tbsp")) {
            double starting_value = com.madwin.mwcalc.UnitConverter.mStartingValue();
            com.madwin.mwcalc.UnitConverter.mUpdateDisplay(Double.toString(
                    com.madwin.mwcalc.UnitConversion.mTspToTbsp(starting_value)));
        }
        if (selection.equals("Tbsp to tsp")) {
            double starting_value = com.madwin.mwcalc.UnitConverter.mStartingValue();
            com.madwin.mwcalc.UnitConverter.mUpdateDisplay(Double.toString(
                    com.madwin.mwcalc.UnitConversion.mTbspToTsp(starting_value)));
        }
        if (selection.equals("Cups to Tbsp")) {
            double starting_value = com.madwin.mwcalc.UnitConverter.mStartingValue();
            com.madwin.mwcalc.UnitConverter.mUpdateDisplay(Double.toString(
                    com.madwin.mwcalc.UnitConversion.mCupsToTbsp(starting_value)));
        }
        if (selection.equals("Tbsp to Cups")) {
            double starting_value = com.madwin.mwcalc.UnitConverter.mStartingValue();
            com.madwin.mwcalc.UnitConverter.mUpdateDisplay(Double.toString(
                    com.madwin.mwcalc.UnitConversion.mTbspToCups(starting_value)));
        }
        if (selection.equals("in to ft")) {
            double starting_value = com.madwin.mwcalc.UnitConverter.mStartingValue();
            com.madwin.mwcalc.UnitConverter.mUpdateDisplay(Double.toString(
                    com.madwin.mwcalc.UnitConversion.mInToFt(starting_value)));
        }
        if (selection.equals("ft to in")) {
            double starting_value = com.madwin.mwcalc.UnitConverter.mStartingValue();
            com.madwin.mwcalc.UnitConverter.mUpdateDisplay(Double.toString(
                    com.madwin.mwcalc.UnitConversion.mFtToIn(starting_value)));
        }
        if (selection.equals("L to gal")) {
            double starting_value = com.madwin.mwcalc.UnitConverter.mStartingValue();
            com.madwin.mwcalc.UnitConverter.mUpdateDisplay(Double.toString(
                    com.madwin.mwcalc.UnitConversion.mLToGal(starting_value)));
        }
        if (selection.equals("gal to L")) {
            double starting_value = com.madwin.mwcalc.UnitConverter.mStartingValue();
            com.madwin.mwcalc.UnitConverter.mUpdateDisplay(Double.toString(
                    com.madwin.mwcalc.UnitConversion.mGalToL(starting_value)));
        }

    }




}
