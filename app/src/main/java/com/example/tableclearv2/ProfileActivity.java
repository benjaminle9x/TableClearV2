package com.example.tableclearv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {
    EditText inFullname, inPhone, inAddress;
    Button btProfile;
    FirebaseDatabase database;
    DatabaseReference myRef;
    DataStructure_Customer dscus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        findAllViews();
        getDatabase();

        btProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeData(inFullname.getText(),inPhone.getText(),inAddress.getText());
            }
        });
    }

    private void getDatabase() {
        database = FirebaseDatabase.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String path = "userdata/" + mAuth.getUid();
        myRef = database.getReference(path);
    }

    private DataStructure_Customer createData(Editable inFullname, Editable inPhone, Editable inAddress) {
        return new DataStructure_Customer(String.valueOf(inFullname),
                String.valueOf(inPhone),
                String.valueOf(inAddress));
    }

    private void writeData(Editable inFullname, Editable inPhone, Editable inAddress) {
        DataStructure_Customer dscus = createData(inFullname,inPhone,inAddress);
        myRef.push().setValue(dscus).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Profile Created", Toast.LENGTH_LONG).show();
                openMainActivity();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Creating Profile Failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void findAllViews() {
        inFullname = findViewById(R.id.inFullname);
        inPhone = findViewById(R.id.inPhone);
        inAddress = findViewById(R.id.inAddress);
        btProfile = findViewById(R.id.btProfile);
    }

    private void openMainActivity() {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
