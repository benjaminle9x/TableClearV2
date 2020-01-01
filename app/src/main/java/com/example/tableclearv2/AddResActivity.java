package com.example.tableclearv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddResActivity extends AppCompatActivity {
    EditText inResName, inAddress, inRating;
    Button btAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_res);
        findAllViews();

    }

    private void findAllViews() {
        inResName = findViewById(R.id.inResname);
        inAddress = findViewById(R.id.inAddress);
        inRating = findViewById(R.id.inRating);
        btAdd = findViewById(R.id.btadd);
    }
}
