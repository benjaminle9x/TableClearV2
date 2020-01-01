package com.example.tableclearv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ManagerActivity extends AppCompatActivity {
    ImageView btAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        findAllViews();

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddResActivity();
            }
        });
    }

    private void findAllViews() {
        btAdd = findViewById(R.id.btAdd);
    }

    private void openAddResActivity() {
        Intent i = new Intent(this,AddResActivity.class);
        startActivity(i);
    }
}
