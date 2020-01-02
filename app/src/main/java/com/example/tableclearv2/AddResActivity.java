package com.example.tableclearv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class AddResActivity extends AppCompatActivity {
    EditText inResName, inAddress, inRating;
    Button btAdd;
    FirebaseDatabase database;
    DatabaseReference myRef;
    DataStructureRestaurant mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_res);
        findAllViews();
        getDatabase();

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeData(inResName.getText(),inAddress.getText(),inRating.getText());
            }
        });
    }

    private void getDatabase() {
        database = FirebaseDatabase.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String path = "restaurantdata/";
        myRef = database.getReference(path);
    }

    private DataStructureRestaurant createData(Editable resName, Editable resAddress, Editable resRating) {
        return new DataStructureRestaurant(String.valueOf(resName),
                                           String.valueOf(resAddress),
                                           String.valueOf(resRating));
    }

    private void writeData(Editable resName, Editable resAddress, Editable resRating) {
        DataStructureRestaurant mData = createData(resName,resAddress,resRating);
        myRef.push().setValue(mData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Restaurant Added", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failed to add Restaurant", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void findAllViews() {
        inResName = findViewById(R.id.inResname);
        inAddress = findViewById(R.id.inAddress);
        inRating = findViewById(R.id.inRating);
        btAdd = findViewById(R.id.btadd);
    }
}
