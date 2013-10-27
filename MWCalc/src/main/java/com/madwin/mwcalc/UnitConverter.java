package com.madwin.mwcalc;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.madwin.mwcalc.R;

import java.util.ArrayList;

/**
 * Created by Andrew on 8/24/13.
 */
public class UnitConverter extends Activity implements OnChildClickListener {

    private static final String TAG = UnitConverter.class.getName();

    public static EditText starting_number_input;
    public static String starting_string = "0";
    public static double starting_value = 0;
    public static String final_value = "0";
    public static TextView text_view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.unitconverterlayout);
        starting_number_input = (EditText)findViewById(R.id.startingvalueinput);
        starting_number_input.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        starting_number_input.setText(starting_string);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ExpandableListView elv = (ExpandableListView)findViewById(R.id.converterlist);
        elv.setClickable(true);

        setGroupData();
        setChildGroupData();

        NewAdapter mNewAdapter = new NewAdapter(groupItem, childItem);
        mNewAdapter
                .setInflater(
                        (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE),
                        this);

        starting_number_input = (EditText)findViewById(R.id.startingvalueinput);
        text_view = (TextView)findViewById(R.id.convertfinalvalue);

        elv.setAdapter(mNewAdapter);
        elv.setOnChildClickListener(this);

    }

    public void setGroupData() {
        groupItem.add(getString(R.string.length));
        groupItem.add(getString(R.string.weight));
        groupItem.add(getString(R.string.speed));
        groupItem.add(getString(R.string.cooking));
        groupItem.add(getString(R.string.volume));
    }

    ArrayList<String> groupItem = new ArrayList<String>();
    ArrayList<Object> childItem = new ArrayList<Object>();

    public void setChildGroupData() {
        /**
         * Add Data For Length
         */
        ArrayList<String> child = new ArrayList<String>();
        child.add(getString(R.string.kmtomi));
        child.add(getString(R.string.mitokm));
        child.add(getString(R.string.fttom));
        child.add(getString(R.string.mtoft));
        child.add(getString(R.string.intoft));
        child.add(getString(R.string.fttoin));
        childItem.add(child);

        /**
         * Add Data For Weight
         */
        child = new ArrayList<String>();
        child.add(getString(R.string.lbtokg));
        child.add(getString(R.string.kgtolb));
        childItem.add(child);
        /**
         * Add Data For Speed
         */
        child = new ArrayList<String>();
        child.add(getString(R.string.mphtokph));
        child.add(getString(R.string.kphtomph));
        childItem.add(child);

        /**
         * Add Data For Cooking
         */
        child = new ArrayList<String>();
        child.add(getString(R.string.tsptotbsp));
        child.add(getString(R.string.tbsptotsp));
        child.add(getString(R.string.tbsptocups));
        child.add(getString(R.string.cupstotbsp));
        childItem.add(child);

        /**
         * Add Data For Volume
         */
        child = new ArrayList<String>();
        child.add(getString(R.string.galtol));
        child.add(getString(R.string.ltogal));
        childItem.add(child);

    }

   // @Override
    public boolean onChildClick(ExpandableListView parent, View v,
                                int groupPosition, int childPosition, long id) {

        Toast.makeText(UnitConverter.this, "Clicked On Child",
                Toast.LENGTH_SHORT).show();

        return true;
    }

    public static void mUpdateDisplay(String final_value) {
        //InputMethodManager imm = (InputMethodManager)getSystemService(
        //        Context.INPUT_METHOD_SERVICE);
       // imm.hideSoftInputFromWindow(starting_number_input.getWindowToken(), 0);

        text_view.setText(final_value);
   }

    public static double mStartingValue() {

        starting_string = starting_number_input.getText().toString();
        if (starting_string.equals("")) {
            starting_string = "0";
        }

        Log.d(TAG, "starting_string.equals(null) = " + starting_string.equals(null));
        Log.d(TAG, "starting_string.equals = " + starting_string);


        if (!starting_string.equals("")){
            starting_value = Double.parseDouble(starting_string);
        }
        return starting_value;
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent back_to_calc = new Intent(this, InterfaceUI.class);
            this.startActivity(back_to_calc);

            finish();
        }
        return super.onKeyUp(keyCode, event);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent back_to_calc = new Intent(this, InterfaceUI.class);
        this.startActivity(back_to_calc);
        finish();
        return true;

    }

}
