package com.example.tableclearv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class ReservationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        String mResName = getIntent().getStringExtra("resname");
        String mResAddress = getIntent().getStringExtra("resaddress");
        String mResRating = getIntent().getStringExtra("resrating");
        Log.i("OUR VALUE",mResName);
        Log.i("OUR VALUE 2",mResAddress);
        Log.i("OUR VALUE 3",mResRating);
        Toast.makeText(this,""+mResName,Toast.LENGTH_SHORT).show();
    }
}
