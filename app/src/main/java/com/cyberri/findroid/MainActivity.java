package com.cyberri.findroid;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    DatabaseHelper helper = new DatabaseHelper(this);
    List<String> y =new ArrayList<String>();
    List<String> bb =new ArrayList<String>();
    List<String> final_list = new ArrayList<String>();

    String a, b, c, d;

    private TextView tv;
    private Button bt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         tv = (TextView) findViewById(R.id.textView3);
         bt = (Button) findViewById(R.id.button6);

        // telemamanger= (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
       // String getSimSerialNumber = telemamanger.getSimSerialNumber();
        //String getSimNumber = telemamanger.getLine1Number();
       // String getSimNumber = telemamanger.getLine1Number();
     //   TelephonyManager.getDeviceId()

       /* SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gsons = new Gson();
        String jsons = sharedPrefs.getString("final_list", null);


        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        List<String> zz = gsons.fromJson(jsons, type);
        tvData.setText(zz.get(5).toString());
*/


        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View vi) {


                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                SharedPreferences.Editor editor = pref.edit();

                EditText a = (EditText) findViewById(R.id.email);
                String str = a.getText().toString();
                EditText b = (EditText) findViewById(R.id.pass);
                String pass = b.getText().toString();
                String password = helper.searchPass(str);
                if (str.isEmpty() || pass.isEmpty()) {
                    Toast toast = Toast.makeText(MainActivity.this, "fields are empty", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {

                    if (pass.equals(password)) {
                        Intent in = new Intent(MainActivity.this, HomeScreen.class);

                        // Intent in = new Intent(MainActivity.this, trusted_contact_2.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    // in.putExtra("Username",str);
                    editor.putString("useremail", str); // Storing string
                    editor.commit();
                    startActivity(in);
                } else {
                    Toast toaster1 = Toast.makeText(MainActivity.this, "username and password do not match", Toast.LENGTH_SHORT);
                    toaster1.show();
                }
            }}
        });
        tv.setOnClickListener(new View.OnClickListener() {

            long idcheck = 0;

            public void onClick(View v) {
                idcheck = helper.idcheck();
                if (idcheck != 0) {
                    Toast toaste = Toast.makeText(MainActivity.this, "one time registration already completed.no more registration allowed", Toast.LENGTH_SHORT);
                   toaste.show();

                } else {
                    // Toast toaste=Toast.makeText(MainActivity.this,"a"+idcheck, Toast.LENGTH_SHORT);
                    //toaste.show();
                    Intent i = new Intent(MainActivity.this, Registration.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(i);
                }
            }
        });

          }

}
