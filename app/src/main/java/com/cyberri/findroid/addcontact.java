package com.cyberri.findroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class addcontact extends Activity {
    DatabaseHelper2 helper=new DatabaseHelper2(this);

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcontact);

        final Button bt = (Button) findViewById(R.id.addcon);
//        final Button bt = (Button) findViewById(R.id.addcon);

        bt.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {

            EditText name = (EditText) findViewById(R.id.cname);
            EditText email = (EditText) findViewById(R.id.cemail);
            EditText mob = (EditText) findViewById(R.id.cmobile);
            EditText addr = (EditText) findViewById(R.id.caddress);
            RadioGroup rg1 = (RadioGroup) findViewById(R.id.radio);
            String namestr = name.getText().toString();
            String emailstr = email.getText().toString();
            String mobstr = mob.getText().toString();
            String addrstr = addr.getText().toString();
            String selection = "";
/*            if (rg1.getCheckedRadioButtonId() != -1) {
                int id = rg1.getCheckedRadioButtonId();
                View radioButton = rg1.findViewById(id);
                int radioId = rg1.indexOfChild(radioButton);
                RadioButton btn = (RadioButton) rg1.getChildAt(radioId);
                selection = (String) btn.getText();
            }
*/
            if (namestr.isEmpty() || emailstr.isEmpty() || mobstr.isEmpty()) {
                Toast toast = Toast.makeText(addcontact.this, "fields are empty", Toast.LENGTH_SHORT);
                toast.show();


            } else {

                //insert detail in db
                contact m = new contact();
                m.setName(namestr);
                m.setEmail(emailstr);
                m.setAddress(addrstr);
                m.setMobile(mobstr);
                m.setGender(selection);
                helper.insertContact(m);
                Toast toaster = Toast.makeText(addcontact.this, "Contact has been added successfully", Toast.LENGTH_SHORT);
                toaster.show();
            }
        }
    });
        final Button bt1 = (Button)findViewById(R.id.button7);
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(addcontact.this, TrustedContacts.class);
                startActivity(i);
            }
        });



    }}
