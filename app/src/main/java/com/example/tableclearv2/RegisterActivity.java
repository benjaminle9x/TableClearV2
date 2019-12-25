package com.example.tableclearv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    EditText inUsername, inPassword, inRetype;
    Button btRegister;
    ProgressBar progressBar;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findAllViews();

        fAuth = FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser() != null) {
            openProfileActivity();
            finish();
        }

        //Buttons function
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = inUsername.getText().toString().trim();
                String password = inPassword.getText().toString().trim();
                String retype = inRetype.getText().toString().trim();


                if(TextUtils.isEmpty(username)) {
                    inUsername.setError("Username is Required");
                    inUsername.requestFocus();
                    return;
                }

                else if(TextUtils.isEmpty(password)) {
                    inPassword.setError("Password is Required");
                    inPassword.requestFocus();
                    return;
                }

                else if(TextUtils.isEmpty(retype)) {
                    inRetype.setError("Please Confirm your Password");
                    inRetype.requestFocus();
                    return;
                }

                else if(password.length() < 6) {
                    inPassword.setError("Password Must be over 6 characters");
                    inPassword.requestFocus();
                    return;
                }

                else if(!password.equals(retype)) {
                    inRetype.setError("Your password is not matched! Please re-type your password");
                    inRetype.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //Register in Firebase
                fAuth.createUserWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this,"Account Created Successfully", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                            openProfileActivity();
                        } else {
                            Toast.makeText(RegisterActivity.this,"Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    //Find All Views
    private void findAllViews() {
        inUsername = findViewById(R.id.inUsername);
        inPassword = findViewById(R.id.inPassword);
        inRetype = findViewById(R.id.inRetype);
        btRegister = findViewById(R.id.btRegister);
        progressBar = findViewById(R.id.progressBar);
    }

    //Open Activities
    private void openProfileActivity() {
        Intent i = new Intent(this,ProfileActivity.class);
        startActivity(i);
    }


}
