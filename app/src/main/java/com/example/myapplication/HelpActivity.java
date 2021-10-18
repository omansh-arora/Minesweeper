/*

Help screen

 */
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        setTitle("Help");
        TextView t2 = (TextView) findViewById(R.id.help_txtHomeLink);
        t2.setMovementMethod(LinkMovementMethod.getInstance());
    }
}