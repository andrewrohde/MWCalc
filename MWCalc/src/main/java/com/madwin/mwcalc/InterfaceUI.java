package com.madwin.mwcalc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;



public class InterfaceUI extends Activity {


    private static final String TAG = InterfaceUI.class.getName();
    private static String current_display_value = null;
    private static int operator_selection;
    private static double first_number = 0;
    private static Boolean wallpaper_checker;
    private static int button_height;
    private static int button_width;
    private static int layout_side;
    ViewGroup.LayoutParams params;
    private static int add_keypad_counter;
    private static int last_button; // 1 for number, 2 for operator, 3 for equals


    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_ui);
        add_keypad_counter = 1;
        layout_side = 1;
        mAddKeypadLayout();

        if (getIntent().getExtras() != null){
            Bundle extras = getIntent().getExtras();
            wallpaper_checker = extras.getBoolean("wallpaper_checker");
            Log.d(TAG, "wallpaper_checker from bundle = " + wallpaper_checker);
        } else {
            wallpaper_checker = false;
        }
        mUpdateDisplay();

        mButtonSetup();
    }

    @Override
    public void onResume() {

        super.onResume();
        current_display_value = "0";
        mUpdateDisplay();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.unitconverter:
                mShowUnitConverter();
                return true;
            case R.id.settings:
                mShowSettings();
                return true;
            case R.id.help:
                mShowHelp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private View.OnClickListener oneListener = new View.OnClickListener() {
        public void onClick(View v)  {

            String current_selection = "1";
            last_button = 1;

            current_display_value = ButtonHandle
                    .mAddNumberToValue(current_selection, current_display_value);
            mUpdateDisplay();

        }
    };

    private View.OnClickListener twoListener = new View.OnClickListener() {
        public void onClick(View v)  {

            String current_selection = "2";
            last_button = 1;


            current_display_value = ButtonHandle
                    .mAddNumberToValue(current_selection, current_display_value);
            mUpdateDisplay();
        }
    };

    private View.OnClickListener threeListener = new View.OnClickListener() {
        public void onClick(View v)  {

            String current_selection = "3";
            last_button = 1;

            current_display_value = ButtonHandle
                    .mAddNumberToValue(current_selection, current_display_value);
            mUpdateDisplay();
        }
    };

    private View.OnClickListener fourListener = new View.OnClickListener() {
        public void onClick(View v)  {

            String current_selection = "4";
            last_button = 1;
            current_display_value = ButtonHandle
                    .mAddNumberToValue(current_selection, current_display_value);
            mUpdateDisplay();
        }
    };

    private View.OnClickListener fiveListener = new View.OnClickListener() {
        public void onClick(View v)  {

            String current_selection = "5";
            last_button = 1;

            current_display_value = ButtonHandle
                    .mAddNumberToValue(current_selection, current_display_value);
            mUpdateDisplay();
        }
    };

    private View.OnClickListener sixListener = new View.OnClickListener() {
        public void onClick(View v)  {

            String current_selection = "6";
            last_button = 1;

            current_display_value = ButtonHandle
                    .mAddNumberToValue(current_selection, current_display_value);
            mUpdateDisplay();
        }
    };

    private View.OnClickListener sevenListener = new View.OnClickListener() {
        public void onClick(View v)  {

            String current_selection = "7";
            last_button = 1;

            current_display_value = ButtonHandle
                    .mAddNumberToValue(current_selection, current_display_value);
            mUpdateDisplay();
        }
    };

    private View.OnClickListener eightListener = new View.OnClickListener() {
        public void onClick(View v)  {

            String current_selection = "8";
            last_button = 1;

            current_display_value = ButtonHandle
                    .mAddNumberToValue(current_selection, current_display_value);
            mUpdateDisplay();
        }
    };

    private View.OnClickListener nineListener = new View.OnClickListener() {
        public void onClick(View v)  {

            String current_selection = "9";
            last_button = 1;

            current_display_value = ButtonHandle
                    .mAddNumberToValue(current_selection, current_display_value);
            mUpdateDisplay();
        }
    };

    private View.OnClickListener decimalListener = new View.OnClickListener() {
        public void onClick(View v)  {
            String current_selection = ".";

            Log.d(TAG, "current_display_value in decimal = " + current_display_value);

            try{
            if (!current_display_value.contains(current_selection) &&
                    !current_display_value.equals(null) && last_button != 2){
                current_display_value = ButtonHandle
                        .mAddNumberToValue(current_selection, current_display_value);
                Log.d(TAG, "current_display_value in decimal before update display = " + current_display_value);
                mUpdateDisplay();
                Log.d(TAG, "current_display_value in decimal after update display = " + current_display_value);
            }
            last_button = 1;
        }catch (Exception e) {
                e.printStackTrace();
            }
            Log.d(TAG, "current_display_value in decimal = " + current_display_value);
        }

    };

    private View.OnClickListener zeroListener = new View.OnClickListener() {
        public void onClick(View v)  {

            String current_selection = "0";
            last_button = 1;

            current_display_value = ButtonHandle
                    .mAddNumberToValue(current_selection, current_display_value);
            mUpdateDisplay();
        }
    };

    private View.OnClickListener additionListener = new View.OnClickListener() {
        public void onClick(View v)  {



            operator_selection = 1;
            mConductOperatorTasks();
            last_button = 2;


            current_display_value = null;
        }
    };

    private View.OnClickListener subtractionListener = new View.OnClickListener() {
        public void onClick(View v)  {

            operator_selection = 2;
            mConductOperatorTasks();
            last_button = 2;

            current_display_value = null;
        }
    };

    private View.OnClickListener multiplicationListener = new View.OnClickListener() {
        public void onClick(View v)  {

            operator_selection = 3;
            mConductOperatorTasks();
            last_button = 2;

            current_display_value = null;
        }
    };

    private View.OnClickListener divisionListener = new View.OnClickListener() {
        public void onClick(View v)  {

            operator_selection = 4;
            mConductOperatorTasks();
            last_button = 2;

            current_display_value = null;

        }
    };

    private View.OnClickListener equalsListener = new View.OnClickListener() {
        public void onClick(View v)  {

            String a = current_display_value;


            current_display_value = CalculatorMathOperations.mEqualsTask(operator_selection,
                    current_display_value, first_number);
            first_number = Double.parseDouble(a);
            mUpdateDisplay();
            //last_button = 3;
        }
    };

    private View.OnClickListener clearListener = new View.OnClickListener() {
        public void onClick(View v)  {

            first_number = 0;
            //last_button = 1;

            current_display_value = "0";
            mUpdateDisplay();
        }
    };

    private View.OnClickListener sqrtListener = new View.OnClickListener() {
        public void onClick(View v) {
            current_display_value = CalculatorMathOperations.mSquareRoot(current_display_value);
            mUpdateDisplay();
        }
    };

    private View.OnClickListener squaredListener = new View.OnClickListener() {
        public void onClick(View v) {
            current_display_value = CalculatorMathOperations.mNumberSquared(current_display_value);
            mUpdateDisplay();
        }
    };

    private View.OnClickListener deleteListener = new View.OnClickListener() {
        public void onClick(View v) {

            if (last_button != 2 && !current_display_value.equals(null)) {
            current_display_value = CalculatorMathOperations.mNumberDelete(current_display_value);
            mUpdateDisplay();
            }else{
                //last_button = 1;
            }
        }
    };

    private View.OnClickListener uiSwitchListener = new View.OnClickListener() {
        public void onClick(View v) {

        mUISwitch();
        }
    };

    public void mAddKeypadLayout() {

        if (add_keypad_counter == 1) {
        LinearLayout keypad_layout = (LinearLayout) findViewById(R.id.keypad_layout);
        //LinearLayout keypad_right = (LinearLayout) findViewById(R.id.keypad_layout_right);
        LayoutInflater inflateKeypad;
        inflateKeypad = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        LinearLayout keypad_right = (LinearLayout) inflateKeypad
                .inflate(R.layout.keypad_right, null);
        keypad_layout.addView(keypad_right);
            add_keypad_counter = 2;

        }

    }

    public void mUISwitch(){

        LinearLayout keypad_layout = (LinearLayout) findViewById(R.id.keypad_layout);

        LayoutInflater inflateKeypad;
        inflateKeypad = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout keypad_left = (LinearLayout) inflateKeypad
                .inflate(R.layout.keypad_left, null);
        LinearLayout keypad_right = (LinearLayout) inflateKeypad
                .inflate(R.layout.keypad_right, null);


        if (layout_side == 1) {

            keypad_layout.removeAllViews();
            keypad_layout.addView(keypad_left);
            mButtonSetup();

            layout_side = 2;

        } else if (layout_side == 2) {

            keypad_layout.removeAllViews(/*keypad_left*/);
            keypad_layout.addView(keypad_right);
            mButtonSetup();

            layout_side = 1;

        }
    }

    public void mUpdateDisplay(){

        LinearLayout calculator_layout = (LinearLayout)findViewById(R.id.calculator_layout);
        TextView display = (TextView)findViewById(R.id.currently_displayed_value);
        TextView previous_value = (TextView)findViewById(R.id.previous_value);



        LinearLayout button_layout = (LinearLayout)findViewById(R.id.keypad_layout);

        int button_layout_height = button_layout.getContext().getResources()
                .getDisplayMetrics().heightPixels;
        int button_layout_width = button_layout.getContext().getResources()
                .getDisplayMetrics().widthPixels;

        Log.d(TAG, "layout_height/width = " + button_layout_height + "  " + button_layout_width);


        button_height = ((int)(button_layout_height * 0.66))/6;
        button_width = button_layout_width/4;

        mSetButtonSize();

        display.setText(FormatCalcValue.mRemoveTrailingPointZero(FormatCalcValue.mCalcNullChecker(
                        current_display_value)));

        if (first_number == 0){
            previous_value.setText("");

        } else {
            previous_value.setText(
                    FormatCalcValue.mRemoveTrailingPointZero(Double.toString(first_number)));
        }
        if(wallpaper_checker == true){
            calculator_layout.setBackground(getResources()
                    .getDrawable(R.drawable.background_math_final));
        } else {
            calculator_layout.setBackgroundColor(getResources().getColor(R.color.Black));
        }
    }

    private void mSetButtonSize() {

        Button buttonOne = (Button)findViewById(R.id.one);
        Button buttonTwo = (Button)findViewById(R.id.two);
        Button buttonThree = (Button)findViewById(R.id.three);
        Button buttonFour = (Button)findViewById(R.id.four);
        Button buttonFive = (Button)findViewById(R.id.five);
        Button buttonSix = (Button)findViewById(R.id.six);
        Button buttonSeven = (Button)findViewById(R.id.seven);
        Button buttonEight = (Button)findViewById(R.id.eight);
        Button buttonNine = (Button)findViewById(R.id.nine);
        Button buttonZero = (Button)findViewById(R.id.zero);
        Button buttonAddition = (Button)findViewById(R.id.addition);
        Button buttonSubtraction = (Button)findViewById(R.id.subtraction);
        Button buttonEquals = (Button)findViewById(R.id.equals);
        Button buttonMultiplication = (Button)findViewById(R.id.multiplication);
        Button buttonDivision = (Button)findViewById(R.id.division);
        Button buttonDecimal = (Button)findViewById(R.id.decimal);
        Button buttonClear = (Button)findViewById(R.id.clear);
        Button buttonDelete = (Button)findViewById(R.id.delete);
        Button button_Sqrt = (Button)findViewById(R.id.sqrt);
        Button button_squared = (Button)findViewById(R.id.squared);
        Button button_ui_switch = (Button)findViewById(R.id.ui_switch);

        Log.d(TAG, "button_height/width = " + button_height + "  " + button_width);
        int button_dimension;
        if (button_height < button_width){
        button_dimension = button_height;
        }else{
        button_dimension = button_width;
        }

        params = buttonOne.getLayoutParams();
        params.height = button_dimension;
        params.width = button_dimension;

        Log.d(TAG, "button_dimension = " + button_dimension);
        Log.d(TAG, "layout_params = " + params);

        buttonOne.setLayoutParams(params);
        buttonTwo.setLayoutParams(params);
        buttonThree.setLayoutParams(params);
        buttonFour.setLayoutParams(params);
        buttonFive.setLayoutParams(params);
        buttonSix.setLayoutParams(params);
        buttonSeven.setLayoutParams(params);
        buttonEight.setLayoutParams(params);
        buttonNine.setLayoutParams(params);
        buttonZero.setLayoutParams(params);
        buttonAddition.setLayoutParams(params);
        buttonEquals.setLayoutParams(params);
        buttonDecimal.setLayoutParams(params);
        buttonDivision.setLayoutParams(params);
        buttonMultiplication.setLayoutParams(params);
        buttonSubtraction.setLayoutParams(params);
        buttonClear.setLayoutParams(params);
        button_Sqrt.setLayoutParams(params);
        button_squared.setLayoutParams(params);
        buttonDelete.setLayoutParams(params);
        button_ui_switch.setLayoutParams(params);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            super.onDestroy();
        }
        if (keyCode == KeyEvent.KEYCODE_HOME) {
            finish();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    public void mShowUnitConverter() {
        Intent UnitConverter = new Intent(getBaseContext(), UnitConverter.class);
        this.startActivity(UnitConverter);
        finish();
    }

    public void mShowSettings() {
        Log.d(TAG, "wallpaper_checker entering preferences = " + wallpaper_checker);
        Intent Preferences = new Intent(getBaseContext(), com.madwin.mwcalc.PreferencesUI.class);
        Preferences.putExtra("wallpaper_checker", wallpaper_checker);
        this.startActivity(Preferences);
        finish();
    }

    public void mShowHelp() {
        Intent Help = new Intent(getBaseContext(), com.madwin.mwcalc.HelpUI.class);
        this.startActivity(Help);
    }

    public void mButtonSetup() {


        //Button setup
        Button buttonOne = (Button)findViewById(R.id.one);
        Button buttonTwo = (Button)findViewById(R.id.two);
        Button buttonThree = (Button)findViewById(R.id.three);
        Button buttonFour = (Button)findViewById(R.id.four);
        Button buttonFive = (Button)findViewById(R.id.five);
        Button buttonSix = (Button)findViewById(R.id.six);
        Button buttonSeven = (Button)findViewById(R.id.seven);
        Button buttonEight = (Button)findViewById(R.id.eight);
        Button buttonNine = (Button)findViewById(R.id.nine);
        Button buttonZero = (Button)findViewById(R.id.zero);
        Button buttonAddition = (Button)findViewById(R.id.addition);
        Button buttonSubtraction = (Button)findViewById(R.id.subtraction);
        Button buttonEquals = (Button)findViewById(R.id.equals);
        Button buttonMultiplication = (Button)findViewById(R.id.multiplication);
        Button buttonDivision = (Button)findViewById(R.id.division);
        Button buttonDecimal = (Button)findViewById(R.id.decimal);
        Button buttonClear = (Button)findViewById(R.id.clear);
        Button buttonDelete = (Button)findViewById(R.id.delete);
        Button button_Sqrt = (Button)findViewById(R.id.sqrt);
        Button button_squared = (Button)findViewById(R.id.squared);
        Button button_ui_switch = (Button)findViewById(R.id.ui_switch);

        //Listener Setup
        buttonOne.setOnClickListener(oneListener);
        buttonTwo.setOnClickListener(twoListener);
        buttonThree.setOnClickListener(threeListener);
        buttonFour.setOnClickListener(fourListener);
        buttonFive.setOnClickListener(fiveListener);
        buttonSix.setOnClickListener(sixListener);
        buttonSeven.setOnClickListener(sevenListener);
        buttonEight.setOnClickListener(eightListener);
        buttonNine.setOnClickListener(nineListener);
        buttonZero.setOnClickListener(zeroListener);
        buttonAddition.setOnClickListener(additionListener);
        buttonEquals.setOnClickListener(equalsListener);
        buttonDecimal.setOnClickListener(decimalListener);
        buttonDivision.setOnClickListener(divisionListener);
        buttonMultiplication.setOnClickListener(multiplicationListener);
        buttonSubtraction.setOnClickListener(subtractionListener);
        buttonClear.setOnClickListener(clearListener);
        button_Sqrt.setOnClickListener(sqrtListener);
        button_squared.setOnClickListener(squaredListener);
        buttonDelete.setOnClickListener(deleteListener);
        button_ui_switch.setOnClickListener(uiSwitchListener);

        mSetButtonSize();
    }

    public void mConductOperatorTasks() {


       if(last_button == 1 && !current_display_value.equals(".") && !current_display_value.equals(null)){

        first_number = ButtonHandle.mOperatorTasks(current_display_value, first_number, last_button);
        current_display_value = null;

       }else{
           last_button = 1;
       }
    }


}