package com.cyberri.findroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by It's me! on 16-05-2016.
 */
public class add_contact extends Activity {
    SqlHandler sqlHandler;
    EditText etName, etPhone;
    Button btnsubmit;
Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcontact);
        sqlHandler = new SqlHandler(this);
        etName = (EditText) findViewById(R.id.et_name);
       etPhone = (EditText) findViewById(R.id.et_phone);
        cancel = (Button) findViewById(R.id.btn_cancel1);

        btnsubmit = (Button) findViewById(R.id.addcon1);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                String name = etName.getText().toString();
                String phoneNo = etPhone.getText().toString();
                if (name.isEmpty() || phoneNo.isEmpty()) {
                    Toast toast = Toast.makeText(add_contact.this, "fields are empty", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                String query = "INSERT INTO PHONE_CONTACTS(name,phone) values ('"
                        + name + "','" + phoneNo + "')";
                sqlHandler.executeQuery(query);

                Intent i = new Intent(add_contact.this, contactlist.class);


                startActivity(i);
                finish();
            }}
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(add_contact.this, contactlist.class);
                finish();
                startActivity(i);
            }
        });

    }
}







