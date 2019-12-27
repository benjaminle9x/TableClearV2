package com.example.tableclearv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EditAccountActivity extends AppCompatActivity {
    EditText oldPassword, newPassword, reNewPassword;
    Button btSave;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        findAllViews();
        mAuth = FirebaseAuth.getInstance();

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });
    }

    private void changePassword() {
        String oldpassword = oldPassword.getText().toString().trim();
        final String newpassword = newPassword.getText().toString().trim();
        String renewpassword = reNewPassword.getText().toString().trim();

        if(TextUtils.isEmpty(oldpassword)) {
            oldPassword.setError("Current Password is Required");
            oldPassword.requestFocus();
            return;
        }

        else if(TextUtils.isEmpty(newpassword)) {
            oldPassword.setError("New Password is Required");
            oldPassword.requestFocus();
            return;
        }

        else if(TextUtils.isEmpty(renewpassword)) {
            reNewPassword.setError("Please Confirm your Password");
            reNewPassword.requestFocus();
            return;
        }

        else if(newpassword.length() < 6) {
            newPassword.setError("Password Must be over 6 characters");
            newPassword.requestFocus();
            return;
        }

        else if(!newpassword.equals(renewpassword)) {
            reNewPassword.setError("Your password is not matched! Please re-type your password");
            reNewPassword.requestFocus();
            return;
        }

        else {
            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(),newpassword);
            user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    user.updatePassword(newpassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(EditAccountActivity.this,"Password Saved", Toast.LENGTH_SHORT).show();
                            }

                            else {
                                Toast.makeText(EditAccountActivity.this,"Saving Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
        }
    }

    private void findAllViews() {
        oldPassword = findViewById(R.id.oldPassword);
        newPassword = findViewById(R.id.newPassword);
        reNewPassword = findViewById(R.id.reNewPassword);
        btSave = findViewById(R.id.btSave);
    }
}
