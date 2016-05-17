package com.cyberri.findroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TrustedContacts extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_trusted_contacts);
        final TextView textView = new TextView(this);
        final LinearLayout Layout =(LinearLayout) findViewById(R.id.contactlayout);

        textView.setText("Hey, one more TextView");
        Layout.addView(textView);

        final Button bt = (Button)findViewById(R.id.button2);
        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(TrustedContacts.this, addcontact.class);
                startActivity(i);
            }
        });
        final Button bt2 = (Button)findViewById(R.id.button9);
        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(TrustedContacts.this, HomeScreen.class);
                startActivity(i);
            }
        });


    }
}