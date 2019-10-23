package com.example.elecentlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText username;
    private EditText Password;
    private Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.Useridtext);
        Password = (EditText) findViewById(R.id.passwordtext);
        btnlogin = (Button) findViewById(R.id.buttonlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= username.getText()+"";
                String pass= Password.getText()+"";

                validate(name,pass);
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
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(),"Incorrect Password or User name",Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
