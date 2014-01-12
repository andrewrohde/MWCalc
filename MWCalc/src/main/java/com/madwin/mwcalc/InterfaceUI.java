package com.madwin.mwcalc;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


public class InterfaceUI extends Activity {


    private final String TAG = InterfaceUI.class.getName();
    private String current_display_value = "";
    private int operator_selection;
    private double first_number = 0;
    private Boolean wallpaper_checker = false;
    private int button_height;
    private int button_width;
    private Boolean layout_side;
    ViewGroup.LayoutParams params;
    private int last_button; // 1 for number, 2 for operator, 3 for equals
    SharedPreferences preferences;
    private int intButtonColor;
    private int intTextColor;

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

/******************************SavePreferences******************************/
    preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
    wallpaper_checker = preferences.getBoolean("Wall", false);
    layout_side = preferences.getBoolean("Side", false);
    intButtonColor = preferences.getInt("ButtonColor", -13388315);
    intTextColor = preferences.getInt("TextColor", -1338315);

/*****************************SavePreferences****************************/

/**************** Nav Bar Setup******************************************/
        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mPlanetTitles));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

/**************** Nav Bar Setup******************************************/

/**************** Open nav bar with app icon******************************************/
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

/**************** Open nav bar with app icon******************************************/

/****************Display Setup********************************************************/
        mAddKeypadLayout();

        mUpdateDisplay();

        mButtonSetup();

        mSetButtonSize();

        mSetColors();

        mSetBackground();
    }
/****************Display Setup********************************************************/
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
            current_display_value = FormatCalcValue.mRemoveTrailingPointZero(current_display_value);

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
            }
        }
    };

    private View.OnClickListener uiSwitchListener = new View.OnClickListener() {
        public void onClick(View v) {

        mUISwitch();
        mSetColors();
        }
    };

    public void mAddKeypadLayout() {

        if (!layout_side) {
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


        if (!layout_side) {

            keypad_layout.removeAllViews();
            keypad_layout.addView(keypad_left);
            mButtonSetup();
            layout_side = true;

        } else if (layout_side) {

            keypad_layout.removeAllViews(/*keypad_left*/);
            keypad_layout.addView(keypad_right);
            mButtonSetup();
            layout_side = false;

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

            current_display_value = FormatCalcValue.mShortenForTextView(FormatCalcValue.mCalcNullChecker(
                    current_display_value));
            display.setText(
                    current_display_value);


        if (first_number == 0){
            previous_value.setText("");

        } else {
            previous_value.setText(
                    FormatCalcValue.mShortenForTextView(
                            FormatCalcValue.mRemoveTrailingPointZero(
                                    Double.toString(first_number))));
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

    private void mSetColors() {

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

       // Log.d(TAG, "Current button color as int = " + buttonOne.getCurrentTextColor());
        buttonOne.setTextColor(intButtonColor);
        buttonTwo.setTextColor(intButtonColor);
        buttonThree.setTextColor(intButtonColor);
        buttonFour.setTextColor(intButtonColor);
        buttonFive.setTextColor(intButtonColor);
        buttonSix.setTextColor(intButtonColor);
        buttonSeven.setTextColor(intButtonColor);
        buttonEight.setTextColor(intButtonColor);
        buttonNine.setTextColor(intButtonColor);
        buttonZero.setTextColor(intButtonColor);
        buttonAddition.setTextColor(intButtonColor);
        buttonEquals.setTextColor(intButtonColor);
        buttonDecimal.setTextColor(intButtonColor);
        buttonDivision.setTextColor(intButtonColor);
        buttonMultiplication.setTextColor(intButtonColor);
        buttonSubtraction.setTextColor(intButtonColor);
        buttonClear.setTextColor(intButtonColor);
        button_Sqrt.setTextColor(intButtonColor);
        button_squared.setTextColor(intButtonColor);
        buttonDelete.setTextColor(intButtonColor);
        button_ui_switch.setTextColor(intButtonColor);

        TextView display = (TextView)findViewById(R.id.currently_displayed_value);
        TextView previous_value = (TextView)findViewById(R.id.previous_value);

        display.setTextColor(intTextColor);
        previous_value.setTextColor(intTextColor);


    }

    private void mSetBackground(){

        LinearLayout calculator_layout = (LinearLayout)findViewById(R.id.calculator_layout);

        if (preferences.getBoolean("BackgroundStyle", false)) {
            calculator_layout.setBackgroundColor(preferences.getInt("BackgroundColor",
                    getResources().getColor(R.color.Black)));
        } else {

            if(wallpaper_checker){
                calculator_layout.setBackground(getResources()
                        .getDrawable(R.drawable.background_math_final));
            } else {
                calculator_layout.setBackgroundColor(getResources().getColor(R.color.Black));
            }
        }

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
        Preferences.putExtra("wallpaper", wallpaper_checker);
        Preferences.putExtra("layout", layout_side);
        this.startActivity(Preferences);
        finish();
    }

    public void mShowHelp() {

        Intent Help = new Intent(getBaseContext(), com.madwin.mwcalc.HelpUI.class);
        this.startActivity(Help);
    }

    public void mButtonSetup() {

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
    preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
    SharedPreferences.Editor pref_editor = preferences.edit();
    pref_editor.putBoolean("Wall", wallpaper_checker).commit();
    pref_editor.putBoolean("Side", layout_side).commit();

   // Button buttonOne = (Button)findViewById(R.id.one);
   // pref_editor.putInt("ButtonColor", buttonOne.getCurrentTextColor()).commit();

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

    @Override
    protected void onStop() {
        super.onStop();

        mSaveSettings();
    }


  /*  @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            View decorView = this.getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                          //  | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }
    */
}
