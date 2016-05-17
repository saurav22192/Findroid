package com.cyberri.findroid;

/**
 * Created by It's me! on 17-05-2016.
 */
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by abhinav on 7/10/15.
 */
public class ContactsAdapter extends BaseAdapter {

    Context context;
    ArrayList<Contacts> contact;

    public ContactsAdapter(Context context, ArrayList<Contacts> contact) {
        this.context = context;
        this.contact = contact;
    }

    @Override
    public int getCount() {
        return contact.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;

        if(convertView==null)
        {
            LayoutInflater layoutInflater= LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.lay_contact,null);
        }

        else
        {
            view=convertView;
        }

        ImageView imgContact= (ImageView) view.findViewById(R.id.contactImage);
        TextView contactName= (TextView) view.findViewById(R.id.contactName);

        //get data


        Contacts contacts=contact.get(position);
        imgContact.setImageResource(contacts.getImageId());
        contactName.setText(contacts.getName());
        System.getProperty("line.separator");
        Log.e("name", contacts.getName() + " ");

        return view;

    }
}
