package com.cyberri.findroid;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
/**
 * Created by It's me! on 16-05-2016.
 */
public class contactlist  extends Activity {

    SqlHandler sqlHandler;
    ListView lvCustomList;
    Button removeall;
Button addcontact;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        lvCustomList = (ListView) findViewById(R.id.lv_custom_list);
        removeall = (Button) findViewById(R.id.removeall);
        addcontact = (Button) findViewById(R.id.addcontact);

        sqlHandler = new SqlHandler(this);
        showList();

        removeall.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                // String name = etName.getText().toString();
                //String phoneNo = etPhone.getText().toString();

                String query = " delete from PHONE_CONTACTS ";
                String query2 = "delete from sqlite_sequence where name='PHONE_CONTACTS'";


                sqlHandler.executeQuery(query);
                sqlHandler.executeQuery(query2);

                showList();
                //etName.setText("");
                // etPhone.setText("");

            }
        });

        final Button bt2 = (Button)findViewById(R.id.back);
        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(contactlist.this, HomeScreen.class);
              //  i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(i);
finish();
            }
        });

        addcontact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(contactlist.this, add_contact.class);
                //  i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(i);
                finish();
            }
        });

    }

    private void showList() {

        ArrayList<ContactListItems> contactList = new ArrayList<ContactListItems>();
        contactList.clear();
        String query = "SELECT * FROM PHONE_CONTACTS ";
        Cursor c1 = sqlHandler.selectQuery(query);
        if (c1 != null && c1.getCount() != 0) {
            if (c1.moveToFirst()) {
                do {
                    ContactListItems contactListItems = new ContactListItems();

                    contactListItems.setSlno(c1.getString(c1
                            .getColumnIndex("slno")));
                    contactListItems.setName(c1.getString(c1
                            .getColumnIndex("name")));
                    contactListItems.setPhone(c1.getString(c1
                            .getColumnIndex("phone")));
                    contactList.add(contactListItems);

                } while (c1.moveToNext());
            }
        }
        c1.close();

        ContactListAdapter contactListAdapter = new ContactListAdapter(
                contactlist.this, contactList);
        lvCustomList.setAdapter(contactListAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}