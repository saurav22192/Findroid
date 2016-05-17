package com.cyberri.findroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by It's me! on 01-12-2015.
 */
public class homesplash extends Activity {
        ProgressBar progressBar;DatabaseHelper helper =new DatabaseHelper(this);
  //  SqlDbHelper SqHelper =new SqlDbHelper(this);

    private TextView splash;
    List<String> y =new ArrayList<String>();
    List<String> bb =new ArrayList<String>();
    List<String> final_list = new ArrayList<String>();

    String a, b, c, d,e,f;


    public class BackgroundAsyncTask extends AsyncTask<Void, Integer, Void> {

            int myProgress;

            @Override
            protected void onPostExecute(Void result) {
                // TODO Auto-generated method stub
                //        Toast.makeText(AndroidAsyncTaskProgressBar.this,
                //            "onPostExecute", Toast.LENGTH_LONG).show();
                //  buttonStartProgress.setClickable(true);
            }

            @Override
            protected void onPreExecute() {
                // TODO Auto-generated method stub
                //Toast.makeText(AndroidAsyncTaskProgressBar.this,
                //      "onPreExecute", Toast.LENGTH_LONG).show();
                myProgress = 0;
            }

            @Override
            protected Void doInBackground(Void... params) {
                // TODO Auto-generated method stub
                while(myProgress<100){
                    myProgress++;
                    publishProgress(myProgress);
                   SystemClock.sleep(30);
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                // TODO Auto-generated method stub
                progressBar.setProgress(values[0]);
            }

        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.homesplash);


     // homesplash.this.deleteDatabase("member.db");


        try {
            AsyncAction1 xx = new AsyncAction1();
            y = xx.execute().get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        a = y.get(0);
        b = y.get(1);
        c = y.get(2);
        d = y.get(3);
        try {

            bb = new JAs11().execute("http://opencellid.org/cell/get?key=ba67d026-f4bc-4382-a000-6fc179771ce7&mcc="+a+"&mnc=" + b + "&cellid=" + d + "&lac=" + c).get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        final_list.add(0, a);

        final_list.add(1, b);

        final_list.add(2, c);

        final_list.add(3, d);
        if(bb != null) {
            final_list.add(4, bb.get(0));

            final_list.add(5, bb.get(1));
        }
        Gson gson = new Gson();
        String json = gson.toJson(final_list);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor1 = sharedPref.edit();
        editor1.putString("final_list", json);
        editor1.commit();
        Toast toasted = Toast.makeText(homesplash.this, "Boot-Up Location Saved", Toast.LENGTH_SHORT);
        toasted.show();


        final ImageView iv = (ImageView) findViewById(R.id.imageView);
        final Animation an = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate);
        final Animation an2 = AnimationUtils.loadAnimation(getBaseContext(), R.anim.abc_fade_out);

        splash=(TextView)findViewById(R.id.splash);

        progressBar = (ProgressBar)findViewById(R.id.startbar);
        progressBar.setProgress(0);


        TelephonyManager telemamanger= (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String getSimSerialNumber = telemamanger.getSimSerialNumber();

        long idcheck = helper.idcheck();
        String simnofrommdb;
      String status=helper.check("status");
        SqlHandler sqlHandler;
        SqlDbHelper dbHelper;
        String DATABASE_NAME = "MY_DATABASE";
          int DATABASE_VERSION = 1;
        Context context;

        dbHelper = new SqlDbHelper(this.getApplicationContext(), DATABASE_NAME, null,
                DATABASE_VERSION);

        SQLiteDatabase sqlDatabase;
        String query = "SELECT `phone` FROM PHONE_CONTACTS";

        List<String> list = new ArrayList<String>();
        sqlDatabase = dbHelper.getWritableDatabase();
       Cursor c1 = sqlDatabase.rawQuery(query, null);

        if (c1 != null && c1.getCount() != 0) {
            if (c1.moveToFirst()) {
                do {
                    list.add (c1.getString(0));

                } while (c1.moveToNext());
            }
        }
        c1.close();

      //  splash.setText(list.get(1));



        if(status.equals("active")) {
            if (idcheck != 0) {
                simnofrommdb = helper.check("simno");
//        splash.setText(simnofrommdb+",,,,"+getSimSerialNumber);

                   if (!simnofrommdb.equals(getSimSerialNumber)) {
            //        splash.setText("simno of db n inserted sim differs");
                     String  name = helper.check("name");

String adder=null;
                       String strSMSBody;
                       e=bb.get(0);
                       f=bb.get(1);
if(bb!=null) {
    strSMSBody = name + "'s phone has been stolen.The location details are : \nmcc: " + a + "\nmnc: " + b + "\nlac: " + c + "\ncid: "+ d + "\nGeo: " + e + "," + f+"\nPlease seek for immediate police help";

}
                    else
{
    strSMSBody = name + "'s phone has been stolen.The location details are : \nmcc: " + a + "\nmnc: " + b + "\nlac: " + c + "\ncid: " + d+"\n Please seek for immediate police help" ;

}

                    /*String toNumbers = "";
                       for ( String s : list)
                       {
                           toNumbers = toNumbers + s + ";";
                       }
                       toNumbers = toNumbers.substring(0, toNumbers.length() - 1);
                       String message= "this is a custom message";

                       Uri sendSmsTo = Uri.parse("smsto:" + toNumbers);
                       Intent intent = new Intent(
                               android.content.Intent.ACTION_SENDTO, sendSmsTo);
                       intent.putExtra("sms_body", message);
                       startActivity(intent);
                   */

                       SmsManager sms = SmsManager.getDefault();
                       String[] strarray =list.toArray(new String[list.size()]);

// the message

// the phone numbers we want to send to
                       String numbers[] = strarray;

                       for(String number : numbers) {
                           sms.sendTextMessage(number, null, strSMSBody, null, null);
                       }


                   }
                else
            {
             splash.setText("Anti-Theft Is On");

                }
            }
        }
        else
        {
            splash.setText("Anti Theft Is Off");
        }

        new BackgroundAsyncTask().execute();
        iv.startAnimation(an);
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv.startAnimation(an2);
                finish();
                Intent i = new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }



    public class AsyncAction1 extends AsyncTask<String, String, List<String>> {


        @Override
        protected List<String> doInBackground(String... params) {
            List<String> l = new ArrayList<String>();


            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            GsmCellLocation cellLocation = (GsmCellLocation) telephonyManager.getCellLocation();

            String networkOperator = telephonyManager.getNetworkOperator();
            String mcc = networkOperator.substring(0, 3);
            String mnc = networkOperator.substring(3);


            int cidd = cellLocation.getCid();
            int lacc = cellLocation.getLac();
            String cid = String.valueOf(cidd);
            String lac = String.valueOf(lacc);
            //lacmncmcccid l = new lacmncmcccid(mcc, mnc, cid, lac);
            l.add(0, mcc);
            l.add(1, mnc);
            l.add(2, lac);
            l.add(3, cid);

            return l;


        }
        protected void onPostExecute(List<String> result) {
            super.onPostExecute(result);

        }
    }


    public class JAs11 extends AsyncTask<String, String, List<String>> {
        List<String> list1 = new ArrayList<String>();

        @Override
        protected List<String> doInBackground(String... params) {
            HttpURLConnection connection1 = null;
            BufferedReader reader1 = null;
            StringBuilder sb1;
            try {
                URL url1 = new URL(params[0]);
                connection1 = (HttpURLConnection) url1.openConnection();
                connection1.connect();

                InputStream stream1 = connection1.getInputStream();

                InputStreamReader is1 = new InputStreamReader(stream1);
                sb1 = new StringBuilder();
                reader1 = new BufferedReader(is1);
                String read1 = reader1.readLine();

                while (read1 != null) {
                    sb1.append(read1);
                    read1 = reader1.readLine();
                }
                JSONObject xy1 = XML.toJSONObject(sb1.toString());
                JSONObject s1 = xy1.getJSONObject("rsp").getJSONObject("cell");

                double db1 = s1.getDouble("lat");
                String st1 = String.valueOf(db1);
                double db11 = s1.getDouble("lon");
                String st11 = String.valueOf(db11);

                list1.add(0, st1);
                list1.add(1, st11);


                return list1;


            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                System.out.println(e.toString());
            } finally {
                if (connection1 != null) {
                    connection1.disconnect();
                }
                try {
                    if (reader1 != null) {
                        reader1.close();
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return null;

        }

        protected void onPostExecute(List<String> result) {
            super.onPostExecute(result);


        }


    }

}
