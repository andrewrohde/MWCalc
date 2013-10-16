package com.madwin.mwcalc;

/*
 * Created by Andrew on 7/19/13.
 * This class file handles all mathematical operations.
 */

import android.util.Log;

import java.io.IOException;

public class CalculatorMathOperations {




    public static String mEqualsTask(int operator_selection,
                                       String current_display_value, double first_number){
        String TAG = "MathOperations";
        Log.d(TAG, "operator_selection/current_display_value/first_number in equals = " +
                    operator_selection + "/" + current_display_value + "/" + first_number);

        double displayed_value_after_operation = 0;

        if (!current_display_value.equals("") && !current_display_value.equals("0")
                && !current_display_value.equals(".") && first_number != 0 ){

            if (operator_selection == 1){
                displayed_value_after_operation = first_number +
                        Double.parseDouble(current_display_value);
            }
            if (operator_selection == 2){
                displayed_value_after_operation = first_number -
                        Double.parseDouble(current_display_value);
            }
            if (operator_selection == 3){
                displayed_value_after_operation = first_number *
                        Double.parseDouble(current_display_value);
            }
            if (operator_selection == 4){
                displayed_value_after_operation = first_number /
                        Double.parseDouble(current_display_value);
            }

            if (operator_selection >= 1 && operator_selection <= 4){
                //first_number = displayed_value_after_operation;
                current_display_value = String.valueOf(displayed_value_after_operation);
            }
        }


        return current_display_value;
    }

    public static String mSquareRoot(String current_display_value){

        if (current_display_value.equals("") || current_display_value.equals("0")
                || current_display_value.equals(".")){
            return "0";
        } else {
            return String.valueOf(Math.sqrt(Double.parseDouble(current_display_value)));
        }
    }

    public static String mNumberSquared(String current_display_value){

        if (current_display_value.equals("") || current_display_value.equals("0")
                || current_display_value.equals(".")) {
            return "0";
        } else{
            return String.valueOf(Double.parseDouble(current_display_value) *
                    Double.parseDouble(current_display_value));
        }
    }

    public static String mNumberDelete(String current_display_value){

        if (current_display_value.length() > 1){
        return(

               current_display_value.substring(0, current_display_value.length() - 1)
                );
        }

        if (current_display_value.equals("")) {
            return "0";
        }

        if (current_display_value.length() == 1) {
            return "0";
        }

        return current_display_value;
    }
}