package com.madwin.mwcalc;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import java.text.DecimalFormat;
import java.util.Locale;


public class InterfaceUI extends Activity {


    private final String TAG = InterfaceUI.class.getName();
    private String current_display_value = "";
    private int operator_selection;
    private double first_number = 0;
    private Boolean wallpaper_checker = false;
    private int button_height;
    private int button_width;
    private int layout_side;
    ViewGroup.LayoutParams params;
    private int last_button; // 1 for number, 2 for operator, 3 for equals
    String wallpaper_preference = "background_enabled=";
    String layout_side_preference = "layout_side=";
    String preferences_filename = "preferences.txt";
    String layout_preference_filename = "layout.txt";

/******************************Nav bar setup********************************/
    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

/*****************************Nav Bar Setup*********************************/

    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_ui);

//**************** Nav Bar Setup******************************************
        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mPlanetTitles));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

//**************** Nav Bar Setup******************************************

//**************** Open nav bar with app icon******************************************
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

//**************** Open nav bar with app icon******************************************



        if(SavePreferences.mCheckSettingsFile(preferences_filename)){
        wallpaper_checker = !Character.toString((SavePreferences.getPreference(
                wallpaper_preference, preferences_filename).charAt(
                SavePreferences.getPreference(wallpaper_preference, preferences_filename).length() - 1))).equals("0");
            Log.d(TAG, "wallpaper_preference = " + SavePreferences.getPreference(
                    wallpaper_preference, preferences_filename));
        }
        if (SavePreferences.mCheckSettingsFile(layout_preference_filename)) {
        layout_side = Character.getNumericValue(SavePreferences.getPreference(layout_side_preference, layout_preference_filename).charAt(
                SavePreferences.getPreference(layout_side_preference, layout_preference_filename).length() - 1));
            Log.d(TAG, "layout_side_preference = " + SavePreferences.getPreference(
                    layout_side_preference, layout_preference_filename));
        }else{
            layout_side = 0;
        }

        if (SavePreferences.mCheckSettingsFile(layout_preference_filename)){
            String layout_save_string = SavePreferences.getPreference(layout_side_preference, layout_preference_filename);
            Log.d(TAG, "layout_save_string = " + layout_save_string);
            layout_side = Character.getNumericValue((layout_save_string.charAt(
                    layout_save_string.length() - 1)));

        }
        Log.d(TAG, "wallpaper_checker = " + wallpaper_checker);
        Log.d(TAG, "layout_side = " + layout_side);

        mAddKeypadLayout();

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

        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

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
                    !current_display_value.equals("") && last_button != 2){
                current_display_value = ButtonHandle
                        .mAddNumberToValue(current_selection, current_display_value);
                mUpdateDisplay();
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
            current_display_value = "";
        }
    };

    private View.OnClickListener subtractionListener = new View.OnClickListener() {
        public void onClick(View v)  {

            operator_selection = 2;
            mConductOperatorTasks();
            last_button = 2;
            current_display_value = "";
        }
    };

    private View.OnClickListener multiplicationListener = new View.OnClickListener() {
        public void onClick(View v)  {

            operator_selection = 3;
            mConductOperatorTasks();
            last_button = 2;
            current_display_value = "";
        }
    };

    private View.OnClickListener divisionListener = new View.OnClickListener() {
        public void onClick(View v)  {

            operator_selection = 4;
            mConductOperatorTasks();
            last_button = 2;
            current_display_value = "";
        }
    };

    private View.OnClickListener equalsListener = new View.OnClickListener() {
        public void onClick(View v)  {

            if (!current_display_value.equals("") && !current_display_value.equals(".") ) {
            String a = current_display_value;
            current_display_value = CalculatorMathOperations.mEqualsTask(operator_selection,
                    current_display_value, first_number);

                first_number = Double.parseDouble(a);
            }

            mUpdateDisplay();
        }
    };

    private View.OnClickListener clearListener = new View.OnClickListener() {
        public void onClick(View v)  {

            first_number = 0;
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

            if (last_button != 2 && !current_display_value.equals("")) {
            current_display_value = CalculatorMathOperations.mNumberDelete(current_display_value);
            mUpdateDisplay();
            }else{
            }
        }
    };

    private View.OnClickListener uiSwitchListener = new View.OnClickListener() {
        public void onClick(View v) {

        mUISwitch();
        }
    };

    public void mAddKeypadLayout() {

        if (layout_side == 0) {
        LinearLayout keypad_layout = (LinearLayout) findViewById(R.id.keypad_layout);
        LayoutInflater inflateKeypad;
        inflateKeypad = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        LinearLayout keypad_right = (LinearLayout) inflateKeypad
                .inflate(R.layout.keypad_right, null);
        keypad_layout.addView(keypad_right);

        }else {
            LinearLayout keypad_layout = (LinearLayout) findViewById(R.id.keypad_layout);
            LayoutInflater inflateKeypad;
            inflateKeypad = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout keypad_left = (LinearLayout) inflateKeypad
                    .inflate(R.layout.keypad_left, null);
            keypad_layout.addView(keypad_left);
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


        if (layout_side == 0) {

            keypad_layout.removeAllViews();
            keypad_layout.addView(keypad_left);
            mButtonSetup();
            layout_side = 1;

        } else if (layout_side == 1) {

            keypad_layout.removeAllViews(/*keypad_left*/);
            keypad_layout.addView(keypad_right);
            mButtonSetup();
            layout_side = 0;

        }
        mSaveSettings();
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


            display.setText(FormatCalcValue.mShortenForTextView(FormatCalcValue.mRemoveTrailingPointZero(FormatCalcValue.mCalcNullChecker(
                    current_display_value))));

        if (first_number == 0){
            previous_value.setText("");

        } else {
            previous_value.setText(
                    FormatCalcValue.mShortenForTextView(
                            FormatCalcValue.mRemoveTrailingPointZero(
                                    Double.toString(first_number))));
        }
        if(wallpaper_checker == true){
            calculator_layout.setBackground(getResources()
                    .getDrawable(R.drawable.background_math_final));
        } else {
            calculator_layout.setBackgroundColor(getResources().getColor(R.color.Black));
        }
        mSaveSettings();
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

        int button_dimension;
        if (button_height < button_width){
        button_dimension = button_height;
        }else{
        button_dimension = button_width;
        }

        params = buttonOne.getLayoutParams();
        params.height = button_dimension;
        params.width = button_dimension;

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
        mSaveSettings();
        Log.d(TAG, "wallpaper_checker entering preferences = " + wallpaper_checker);
        Intent Preferences = new Intent(getBaseContext(), com.madwin.mwcalc.PreferencesUI.class);
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

       if(last_button == 1 && !current_display_value.equals(".") && !current_display_value.equals("")){

        first_number = ButtonHandle.mOperatorTasks(current_display_value, first_number, last_button);
        current_display_value = "";

       }else{
           last_button = 1;
       }
    }

public void mSaveSettings() {

    String wallpaper_save_value;
    String layout_side_value;
    if (wallpaper_checker){
        wallpaper_save_value = "1";
    }else{
        wallpaper_save_value = "0";
    }

    layout_side_value = Integer.toString(layout_side);
    SavePreferences.mSaveToFile(wallpaper_preference, wallpaper_save_value, preferences_filename);
    SavePreferences.mSaveToFile(layout_side_preference, layout_side_value, layout_preference_filename);

}

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {

        Log.d("selectItem position variable = ", String.valueOf(position));
        switch (position) {
            case 0:
                mShowUnitConverter();
                return ;
            case 1:
                mShowSettings();
                return ;
            case 2:
                mShowHelp();
                return ;
        }


        mDrawerList.setItemChecked(position, true);
        setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

}
