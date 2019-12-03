package com.example.elecentlife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText useremail;
    private EditText Password;
    private Button btnlogin;
    private Button btncreate;
    private TextView offline;
    private String newemail,newpass;
    private FirebaseAuth mAuth; //crate firebase database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        useremail    = (EditText)    findViewById(R.id.Useridtext);
        Password    = (EditText)    findViewById(R.id.passwordtext);
        btnlogin    = (Button)      findViewById(R.id.buttonlogin);
        btncreate   = (Button)      findViewById(R.id.buttoncreateA);
        offline     = (TextView)    findViewById(R.id.offline);
        mAuth = FirebaseAuth.getInstance(); //get instance for file resource


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= useremail.getText()+"";
                String pass= Password.getText()+"";

                validate(email,pass);
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

                //set click able for create button in dialog
                create.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        newemail = edtname.getText().toString().trim(); //trim mean get space
                        newpass = edtpass.getText().toString().trim();

                        if(newemail.isEmpty())
                        {
                            Toast toast = Toast.makeText(getApplicationContext(),"please enter user name",Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        else if(newpass.isEmpty())
                        {
                            Toast toast = Toast.makeText(getApplicationContext(),"please enter password",Toast.LENGTH_SHORT);
                            toast.show();
                        }

                        else if(!newpass.isEmpty() && !newemail.isEmpty()){
                            mAuth.createUserWithEmailAndPassword(newemail,newpass).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success
                                        Toast toast = Toast.makeText(getApplicationContext(),"sign up successful",Toast.LENGTH_SHORT);
                                        toast.show();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast toast = Toast.makeText(getApplicationContext(),"sign up fail",Toast.LENGTH_SHORT);
                                        toast.show();
                                    }

                                }
                            });

                            dialog.cancel();
                        }


                    }
                });

                //set cancel button in dialog
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                //showing the dialog when click on create button in the main login window
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

    private void validate (String useremail, String userPassword)
    {
        mAuth.signInWithEmailAndPassword(useremail, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success
                            Toast toast = Toast.makeText(getApplicationContext(),"Login successful",Toast.LENGTH_SHORT);
                            toast.show();
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast toast = Toast.makeText(getApplicationContext(),"Login fail",Toast.LENGTH_SHORT);
                            toast.show();
                        }

                        // ...
                    }
                });
    }



}

