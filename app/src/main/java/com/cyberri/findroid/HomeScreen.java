package com.cyberri.findroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class HomeScreen extends Activity {
private Switch sw;
    DatabaseHelper dh=new DatabaseHelper(this);
    SharedPreferences pref ;
private TextView wc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);
        sw= (Switch) findViewById(R.id.switch2);
        pref= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final Button locate=(Button)findViewById(R.id.locate);
        locate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(HomeScreen.this, telephony.class);
         //       i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
           //     i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
finish();

                startActivity(i);
            }
        });
        String statuscheck;
        String user2=pref.getString("useremail", null);;

        statuscheck = dh.check("status");
       //  wc = (TextView)findViewById(R.id.welcome);
        //wc.setText("Welcome"+statuscheck);

        if (statuscheck.equals("active")) {
            sw.setChecked(true);


        }
else        {
            // Toast toaste=Toast.makeText(MainActivity.this,"a"+idcheck, Toast.LENGTH_SHORT);
            //toaste.show();
          sw.setChecked(false);
        }
        //set the switch to ON
        //attach a listener to check for changes in state

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                String user1=pref.getString("useremail", null);;

                if (isChecked) {
                    dh.updateStatus("active",user1);

                    TelephonyManager telemamanger= (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                    String getSimSerialNumber = telemamanger.getSimSerialNumber();

                    dh.updatesimno(getSimSerialNumber);


                    Toast toaster = Toast.makeText(HomeScreen.this, "Anti-Theft is active and sim number is updated", Toast.LENGTH_SHORT);
                    toaster.show();

                } else {

                    //member m = new member();
                    //m.setStatus("Inactive");
                    dh.updateStatus("inactive",user1);
                    Toast toaster = Toast.makeText(HomeScreen.this, "Anti-Theft is inactive", Toast.LENGTH_SHORT);
                    toaster.show();

                }

            }
        });




        final Button bt = (Button)findViewById(R.id.button2);
        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(HomeScreen.this, contactlist.class);
         //       i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
           //     i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
finish();

                startActivity(i);
            }
        });
        final Button bt1 = (Button)findViewById(R.id.button3);
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(HomeScreen.this, help.class);
         //       i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
           //     i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
finish();
                startActivity(i);
            }
        });
final Button profile=(Button)findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(HomeScreen.this, ViewProfile.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
  //              i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
finish();

                startActivity(i);
            }
        });

        Button btn2 = (Button) findViewById(R.id.button8);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = pref1.edit();

                editor.clear();
                editor.commit();
                Intent i = new Intent(HomeScreen.this, MainActivity.class);
                // Closing all the Activities
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                // Add new Flag to start new Activity
      //          i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                // Staring Login Activity
               startActivity(i);

                //finish();

            }
        });
       // Intent in = getIntent();
        //String user=in.getStringExtra("Username");
        String user=pref.getString("useremail", null);;

        String namecheck;

        namecheck = dh.check("name");

   wc = (TextView)findViewById(R.id.welcome);
      wc.setText("Welcome   "+namecheck);

    }








}
