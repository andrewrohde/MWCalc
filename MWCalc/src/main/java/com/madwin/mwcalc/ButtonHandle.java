package com.madwin.mwcalc;


/*
 * Created by Andrew on 7/19/13.
 * This handles tasks from all buttons
 */


public class ButtonHandle {

    public static String mAddNumberToValue(String current_selection, String current_display_value){

       // String current_display_value_string = Double.toString(current_selection);

        if (current_display_value.equals("0") && current_selection.equals(".")){
            current_display_value = "0.";
        }else if (current_display_value == "" || current_display_value.equals("0")){
            current_display_value = current_selection;
        }else{
            //current_display_value = Double.parseDouble(current_display_value_string);current_display_value + current_selection;
            current_display_value = current_display_value + current_selection;
        }

        return current_display_value;

    }

    public static double mOperatorTasks(String current_display_value, double first_number, int last_button){


        if (current_display_value != null && !current_display_value.equals(".")){

            first_number = Double.parseDouble(current_display_value);
            return first_number;

        }else {return 0;}


    }



}