package com.example.tableclearv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText inUsername, inPassword;
    Button btLogin;
    TextView btRegister;
    ImageView btAboutMe;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findAllViews();
        fAuth = FirebaseAuth.getInstance();

        //Button functions

        //Logging In
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                String username = inUsername.getText().toString().trim();
                String password = inPassword.getText().toString().trim();

                if(username.isEmpty()) {
                    inUsername.setError("Username is Required");
                    inUsername.requestFocus();
                }

                else if(password.isEmpty()) {
                    inPassword.setError("Password is Required");
                    inPassword.requestFocus();
                }

                else {
                    progressBar.setVisibility(View.VISIBLE);
                    fAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.INVISIBLE);
                                openCustomerActivity();
                            }
                            else
                                Toast.makeText(MainActivity.this, "Email or Password Incorrect!", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.INVISIBLE);
                        }
                    });
                }
            }
        });

        //Registering new Account
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                openRegisterPage();
            }
        });

        //About the App function
        btAboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

    }

    public void openDialog() {
        AboutMeDialog dialog = new AboutMeDialog();
        dialog.show(getSupportFragmentManager(),"Example Dialog");
    }

    //Find All Views
    private void findAllViews(){
        inUsername = findViewById(R.id.inUsername);
        inPassword = findViewById(R.id.inPassword);
        btLogin = findViewById(R.id.btLogin);
        btRegister = findViewById(R.id.btRegister);
        btAboutMe = findViewById(R.id.btAboutMe);
        progressBar = findViewById(R.id.progressBar);

    }

    //Opening Activities
    public void openRegisterPage() {
        Intent i = new Intent(this,RegisterActivity.class);
        startActivity(i);
    }

    public void openCustomerActivity() {
        Intent i = new Intent(this,CustomerActivity.class);
        startActivity(i);
    }
}
