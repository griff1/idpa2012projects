package com.dky.bouncy;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class BouncyActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        LinearLayout layout1 = (LinearLayout) findViewById(R.id.layout1);
        MyView myView = new MyView(this);
        layout1.addView(myView);
    }
}