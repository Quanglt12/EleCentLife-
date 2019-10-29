package com.example.elecentlife;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText username;
    private EditText Password;
    private Button btnlogin;
    private Button btncreate;
    private TextView offline;
    private String newname,newpass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username    = (EditText)    findViewById(R.id.Useridtext);
        Password    = (EditText)    findViewById(R.id.passwordtext);
        btnlogin    = (Button)      findViewById(R.id.buttonlogin);
        btncreate   = (Button)      findViewById(R.id.buttoncreateA);
        offline     = (TextView)    findViewById(R.id.offline);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= username.getText()+"";
                String pass= Password.getText()+"";

                validate(name,pass);
            }
        });
        //set Create account dialog
        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create new dialog for craete button
                final Dialog dialog = new Dialog(Login.this);
                dialog.setTitle(" ");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialogact);
                //declare for new user name id,pass user enter

                final EditText edtname = (EditText) dialog.findViewById(R.id.edtNewAcount);
                final EditText edtpass = (EditText) dialog.findViewById(R.id.edtNewPass);
                Button cancel = (Button) dialog.findViewById(R.id.cancel);
                Button create = (Button) dialog.findViewById(R.id.create);

                create.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        newname = edtname.getText().toString().trim(); //trim mean get space
                        newpass = edtpass.getText().toString().trim();

                        dialog.cancel();
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });




        //set the offline mode go though without login
        offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void validate (String username, String userPassword)
    {
        if((username.equals("admin")) && (userPassword.equals("admin")))
        {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }
        else if((username.equals(newname)) && (userPassword.equals(newpass)))
        {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(),"Incorrect Password or User name",Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
