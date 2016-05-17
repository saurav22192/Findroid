package com.cyberri.findroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ViewProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_profile);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
       final String user=pref.getString("useremail", null);;

        final Button bt = (Button) findViewById(R.id.editprofile);
        final Button back = (Button) findViewById(R.id.backprofile);

        final TextView t1 = (TextView) findViewById(R.id.pname);
        final TextView emaill = (TextView) findViewById(R.id.pemail);
emaill.setText(user);
        final TextView t3 = (TextView) findViewById(R.id.paddress);
        final TextView t2 = (TextView) findViewById(R.id.pmobile);
        final EditText e1 = (EditText) findViewById(R.id.e1);
        final EditText e2 = (EditText) findViewById(R.id.e3);
        final EditText e3 = (EditText) findViewById(R.id.e4);
       final DatabaseHelper dh=new DatabaseHelper(this);
final Button save=(Button)findViewById(R.id.psave);
        member m=dh.getmember(user);
        t1.setText(m.getName());
        t2.setText(m.getMobile());
        t3.setText(m.getAddress());

        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t1.setVisibility(View.GONE);
                t2.setVisibility(View.GONE);
                t3.setVisibility(View.GONE);

                e1.setVisibility(View.VISIBLE);
                e1.setText(t1.getText().toString());
                e3.setVisibility(View.VISIBLE);
                e3.setText(t3.getText().toString());

                e2.setVisibility(View.VISIBLE);
                e2.setText(t2.getText().toString());
                bt.setVisibility(View.GONE);
                save.setVisibility(View.VISIBLE);
            }

        });


        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                member m = new member();
                m.setName(e1.getText().toString());
                m.setAddress(e3.getText().toString());
                m.setMobile(e2.getText().toString());
                m.setEmail(user);
                dh.updateMember(m);
                Toast toaster = Toast.makeText(ViewProfile.this, "Profile has been updated successfully", Toast.LENGTH_SHORT);
                toaster.show();
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            
            }});
                back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(ViewProfile.this, HomeScreen.class);
                //i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
finish();

                startActivity(i);
            }
        });

    }}