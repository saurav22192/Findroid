package com.cyberri.findroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class telephony extends Activity
{
     String a,b,c,d;
    private  TextView tvData;
    private  TextView textMCC;
   private TextView textMNC;
   private TextView textCID;
   private TextView textLAC;
    private TextView  textGeo;
   private ProgressBar mProgress;
    List<String> cv=new ArrayList<String>();
    List<String> y=new ArrayList<String>();
    List<String> bb=new ArrayList<String>();
    List<String>final_list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telephony);

        Button btnHit = (Button) findViewById(R.id.map);
        textMCC = (TextView) findViewById(R.id.mcc);
        textMNC = (TextView) findViewById(R.id.mnc);
        textCID = (TextView) findViewById(R.id.cid);
        textLAC = (TextView) findViewById(R.id.lac);
        textGeo = (TextView) findViewById(R.id.geo);
        tvData = (TextView) findViewById(R.id.remark);
        mProgress=(ProgressBar) findViewById(R.id.progress_bar);

        try {
            AsyncAction xx = new AsyncAction();
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

            bb = new JAs1().execute("http://opencellid.org/cell/get?key=ba67d026-f4bc-4382-a000-6fc179771ce7&mcc=" + a + "&mnc=" + b + "&cellid=" + d + "&lac=" + c).get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //session test begins
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gsons = new Gson();
        String jsons = sharedPrefs.getString("final_list", null);


        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        List<String> zz = gsons.fromJson(jsons, type);
        if(zz.size()>4) {
            tvData.setText("Session test succeded and loc stored at start is-->" + zz.get(4).toString() + "," + zz.get(5).toString());
        }
        else {
            tvData.setText("Session test succeeded--> location is not available");
        }

        btnHit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                try {
                    cv = new JAs().execute("http://opencellid.org/cell/get?key=ba67d026-f4bc-4382-a000-6fc179771ce7&mcc="+a+"&mnc=" + b + "&cellid=" + d + "&lac=" + c).get();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });
        final Button refresh = (Button)findViewById(R.id.Refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = getIntent();
                finish();
                startActivity(intent);
                        }
        });

        final Button bt2 = (Button)findViewById(R.id.mapback);
        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(telephony.this, HomeScreen.class);
             //   i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();

                startActivity(i);
            }
        });
    }

    public  class AsyncAction  extends AsyncTask<String, String, List<String>> {



        @Override
        protected List<String> doInBackground(String... params){
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
            l.add(0,mcc);
            l.add(1,mnc);
            l.add(2,lac);
            l.add(3,cid);

            return l;


        }


        protected void onPostExecute(List<String> w){
            textMCC.setText("\t"+w.get(0));
            textMNC.setText("\t"+ w.get(1));

            textCID.setText("\t"+w.get(3));
            textLAC.setText("\t"+w.get(2));

        }
    }


        public class JAs1 extends AsyncTask<String,String,List<String>>{
        List<String> list1 = new ArrayList<String>();

        @Override
        protected List<String> doInBackground(String... params){
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

                while(read1 !=null){
                    sb1.append(read1);
                    read1 = reader1.readLine();
                }
                JSONObject xy1 = XML.toJSONObject(sb1.toString());
                JSONObject s1 =xy1.getJSONObject("rsp").getJSONObject("cell");

                double db1 =s1.getDouble("lat");
                String st1 = String.valueOf(db1);
                double db11 =s1.getDouble("lon");
                String st11 = String.valueOf(db11);

                list1.add(0,st1);
                list1.add(1,st11);


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
                if(connection1 != null){
                    connection1.disconnect();
                }
                try {
                    if(reader1 != null){
                        reader1.close();
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return null;

        }

        protected void onPostExecute(List<String> result1) {
            super.onPostExecute(result1);
            if(result1 == null || result1.isEmpty())
            {
                Toast.makeText(telephony.this, "Location can't be traced", Toast.LENGTH_LONG).show();



            }
else {

                textGeo.setText("\tlat:"+result1.get(0).toString()+"\r\n\tlon:" + result1.get(1).toString());
            }
            mProgress.setVisibility(View.GONE);
        }
    }
    public class JAs extends AsyncTask<String,String,List<String>>{
         List<String> list = new ArrayList<String>();

@Override
        protected List<String> doInBackground(String... params){
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            StringBuilder sb;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                InputStreamReader is = new InputStreamReader(stream);
                sb = new StringBuilder();
                reader = new BufferedReader(is);
                String read = reader.readLine();

                while(read !=null){
                    sb.append(read);
                    read = reader.readLine();
                }
                JSONObject xy = XML.toJSONObject(sb.toString());
                JSONObject s =xy.getJSONObject("rsp").getJSONObject("cell");

                double db =s.getDouble("lat");
                String st = String.valueOf(db);
                double db1 =s.getDouble("lon");
                String st1 = String.valueOf(db1);

                list.add(0,st);
                list.add(1,st1);


                return list;



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
                if(connection != null){
                    connection.disconnect();
                }
                try {
                    if(reader != null){
                        reader.close();
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
            if(result == null || result.isEmpty())
            {
                Toast.makeText(telephony.this, "Location can't be traced", Toast.LENGTH_LONG).show();



            }
            else {
                String stringLoc = "geo:" + result.get(0) + "," + result.get(1);

                Toast.makeText(telephony.this, stringLoc, Toast.LENGTH_LONG).show();

                Gson gson = new Gson();
                String json = gson.toJson(result);

                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor1 = sharedPref.edit();
                editor1.putString("non_boot_loc_list", json);
                editor1.commit();
                Intent i = new Intent(telephony.this, MapsActivity.class);

              //  Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(stringLoc));
                startActivity(i);
            }
            }


    }




}

