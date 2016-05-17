package com.cyberri.findroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends Activity {
    DatabaseHelper helper = new DatabaseHelper(this);
    EditText email;
    String emailstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        email = (EditText) findViewById(R.id.email);
        final TextView tv1 = (TextView) findViewById(R.id.regLog);
        tv1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Registration.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public void onSubmitClick(View v) {
        EditText name = (EditText) findViewById(R.id.name);
        EditText mob = (EditText) findViewById(R.id.mobile);
        EditText addr = (EditText) findViewById(R.id.address);
        EditText pass1 = (EditText) findViewById(R.id.pass1);
        EditText pass2 = (EditText) findViewById(R.id.pass2);
        String namestr = name.getText().toString();
        emailstr = email.getText().toString();
        String flag = "true";
        String mobstr = mob.getText().toString();
        String addrstr = addr.getText().toString();
        String pass1str = pass1.getText().toString();
        String pass2str = pass2.getText().toString();
        int checkuser;
        checkuser = helper.checkuser(emailstr);
        // final EditText emailValidate = (EditText)findViewById(R.id.textMessage);

        final TextView textView = (TextView) findViewById(R.id.text);

        if (namestr.isEmpty() || emailstr.isEmpty() || mobstr.isEmpty() || pass1str.isEmpty() || pass2str.isEmpty()) {
            Toast toast = Toast.makeText(Registration.this, "fields are empty", Toast.LENGTH_SHORT);
            toast.show();

            flag = "false";
        } else {
            if (!isValidEmail(emailstr)) {
                Toast toaster = Toast.makeText(Registration.this, "email is invalid", Toast.LENGTH_SHORT);
                toaster.show();
                flag = "false";
            }


            if (!pass1str.equals(pass2str)) {
                Toast toaster = Toast.makeText(Registration.this, "passwords do not match", Toast.LENGTH_SHORT);
                toaster.show();
                flag = "false";
            }
            //int checkuser=helper.checkuser(emailstr);
            if (checkuser != 0) {
                Toast toaster1 = Toast.makeText(Registration.this, "user already exists", Toast.LENGTH_SHORT);
                toaster1.show();
                flag = "false";
            }

        }
        if (flag == "true") {
            //insert detail in db
            member m = new member();
            m.setName(namestr);
            m.setEmail(emailstr);
            m.setAddress(addrstr);
            m.setMobile(mobstr);
            m.setPassword(pass1str);
            helper.insertMember(m, this.getApplicationContext());
            Toast toaster = Toast.makeText(Registration.this, "Registration completed successfully", Toast.LENGTH_SHORT);
            toaster.show();
            Intent in = new Intent(Registration.this, MainActivity.class);
            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      startActivity(in);

        }
    }
}



