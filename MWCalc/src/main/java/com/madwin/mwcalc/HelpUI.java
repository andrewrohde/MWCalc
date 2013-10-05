package com.madwin.mwcalc;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.madwin.mwcalc.R;

/**
 * Created by Andrew on 8/16/13.
 */
public class HelpUI extends Activity {
public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    setContentView(R.layout.help);

    ActionBar actionBar = getActionBar();
    actionBar.setDisplayHomeAsUpEnabled(true);

    TextView company_website = (TextView)findViewById(R.id.devs_website);

    company_website.setOnClickListener(websiteListener);


}

    private View.OnClickListener websiteListener = new View.OnClickListener() {
        public void onClick(View v)  {

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://madwindevelopers.weebly.com"));
            startActivity(browserIntent);

        }
    };


    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent back_to_calc = new Intent(this, com.madwin.mwcalc.InterfaceUI.class);
            this.startActivity(back_to_calc);

            finish();
        }
        return super.onKeyUp(keyCode, event);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent back_to_calc = new Intent(this, com.madwin.mwcalc.InterfaceUI.class);
        this.startActivity(back_to_calc);
        finish();
        return true;

    }
}