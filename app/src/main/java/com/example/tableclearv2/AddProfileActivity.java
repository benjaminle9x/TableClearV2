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

public class AddProfileActivity extends AppCompatActivity {
    EditText inFullname, inPhone, inAddress;
    Button btCreate;
    FirebaseDatabase database;
    DatabaseReference myRef;
    DataStructureCustomer mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);
        findAllViews();
        getDatabase();

        btCreate.setOnClickListener(new View.OnClickListener() {
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

    private DataStructureCustomer createData(Editable fullname, Editable phone, Editable address) {
        return new DataStructureCustomer(String.valueOf(fullname),
                                         String.valueOf(phone),
                                         String.valueOf(address));
    }

    private void writeData(Editable fullname, Editable phone, Editable address) {
        DataStructureCustomer mData = createData(fullname,phone,address);
        myRef.push().setValue(mData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Profile Created", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failed to create Profile", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void findAllViews() {
        inFullname = findViewById(R.id.inFullname);
        inPhone = findViewById(R.id.inPhone);
        inAddress = findViewById(R.id.inAddress);
        btCreate = findViewById(R.id.btCreate);
    }
}
