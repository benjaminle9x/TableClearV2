package com.example.tableclearv2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EditProfileActivity extends AppCompatActivity {
    EditText ouFullname, ouPhone, ouAddress;
    Button btView;
    FirebaseDatabase database;
    DatabaseReference myRef;
    DataStructureCustomer mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        getDatabase();
        findAllViews();

        btView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieveData();
            }
        });
    }

    private void getDatabase() {
        database = FirebaseDatabase.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String path = "userdata/" + mAuth.getUid();
        myRef = database.getReference(path);
    }

    private void retrieveData() {
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DataStructureCustomer ds = dataSnapshot.getValue(DataStructureCustomer.class);
                ouFullname.setText("Fullname: " + ds.getFullname());
                ouPhone.setText("Mobile Phone: " + ds.getPhone());
                ouAddress.setText("Address: " + ds.getAddress());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DataStructureCustomer ds = dataSnapshot.getValue(DataStructureCustomer.class);
                ouFullname.setText("Fullname: " + ds.getFullname());
                ouPhone.setText("Mobile Phone: " + ds.getPhone());
                ouAddress.setText("Address: " + ds.getAddress());
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<DataStructureCustomer> arrayList = new ArrayList<DataStructureCustomer>();
                if(dataSnapshot != null && dataSnapshot.getValue() != null) {
                    for (DataSnapshot a : dataSnapshot.getChildren()) {
                        DataStructureCustomer dataStructureCustomer = new DataStructureCustomer();
                        dataStructureCustomer.setFullname(a.getValue(DataStructureCustomer.class).getFullname());
                        dataStructureCustomer.setPhone(a.getValue(DataStructureCustomer.class).getPhone());
                        dataStructureCustomer.setAddress(a.getValue(DataStructureCustomer.class).getAddress());

                        arrayList.add(dataStructureCustomer);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Data unavailable", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Data Loading Failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void findAllViews() {
        ouFullname = findViewById(R.id.ouFullname);
        ouPhone = findViewById(R.id.ouPhone);
        ouAddress = findViewById(R.id.ouAddress);
        btView = findViewById(R.id.btView);
    }
}
