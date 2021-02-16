package com.example.videocall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUp_Activity extends AppCompatActivity {

    FirebaseAuth auth;
    EditText emailbox, passwordbox, namebox;
    Button loginbtn, signupbtn, already_account;
    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);

        database = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        emailbox = findViewById(R.id.emailBox);
        namebox = findViewById(R.id.name_box);
        passwordbox = findViewById(R.id.passwordBox);
        loginbtn = findViewById(R.id.loginbtn);
        signupbtn = findViewById(R.id.createbtn);

        already_account = findViewById(R.id.already_have_Account);
        already_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp_Activity.this, Login_Activity.class));
            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, pass, name;
                email = emailbox.getText().toString();
                pass = passwordbox.getText().toString();
                name = namebox.getText().toString();

                User user = new User();
                user.setEmail(email);
                user.setPass(pass);
                user.setName(name);


                auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                   
                        if(task.isSuccessful())
                        {
                            database.collection("Users")
                                    .document().set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    startActivity(new Intent(SignUp_Activity.this, Login_Activity.class));
                                }
                            });
                            Toast.makeText(SignUp_Activity.this, "Successfully Created", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(SignUp_Activity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
}