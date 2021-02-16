package com.example.videocall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.tv.TvContract;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Activity extends AppCompatActivity {

    EditText emailbox, passwordbox;
    Button loginbtn, signupbtn;
    FirebaseAuth auth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");
        auth = FirebaseAuth.getInstance();

        emailbox = findViewById(R.id.emailBox);
        passwordbox = findViewById(R.id.passwordBox);

        loginbtn = findViewById(R.id.loginbtn);
        signupbtn = findViewById(R.id.createbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();
                String email, password;
                email = emailbox.getText().toString();
                password = passwordbox.getText().toString();

                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        dialog.dismiss();
                        if(task.isSuccessful())
                        {
                            startActivity(new Intent(Login_Activity.this, Dashboard_Activity.class));
                        }
                        else
                        {
                            Toast.makeText(Login_Activity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Activity.this, SignUp_Activity.class);
                startActivity(intent);
            }
        });

    }
}