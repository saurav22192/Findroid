package com.cyberri.findroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class help extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_help);
        final Button bt1 = (Button)findViewById(R.id.button10);
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(help.this, HomeScreen.class);


                startActivity(i);
                finish();
            }
        });

    }
}