package com.example.tableclearv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ManagerActivity extends AppCompatActivity {
    ImageView btAdd;
    RecyclerView recView;
    ArrayList<DataStructureReservation> arrayList;
    FirebaseRecyclerOptions<DataStructureReservation> options;
    FirebaseRecyclerAdapter<DataStructureReservation,ReservationAdapter> adapter;
    DatabaseReference myRef;

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        findAllViews();

        recView.setHasFixedSize(true);
        recView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<DataStructureReservation>();
        myRef = FirebaseDatabase.getInstance().getReference().child("managerdata/reservation/");
        myRef.keepSynced(true);

        options = new FirebaseRecyclerOptions.Builder<DataStructureReservation>().setQuery(myRef,DataStructureReservation.class).build();
        adapter = new FirebaseRecyclerAdapter<DataStructureReservation, ReservationAdapter>(options) {

            @Override
            protected void onBindViewHolder(@NonNull ReservationAdapter reservationAdapter, int i, @NonNull DataStructureReservation dataStructureReservation) {
                reservationAdapter.CusName.setText(dataStructureReservation.getmCusName());
                reservationAdapter.CusPhone.setText(dataStructureReservation.getmCusPhone());
                reservationAdapter.ResName.setText(dataStructureReservation.getmResName());
                reservationAdapter.ResAddress.setText(dataStructureReservation.getmResAddress());
                reservationAdapter.Time.setText(dataStructureReservation.getmTime());
                reservationAdapter.Date.setText(dataStructureReservation.getmDate());
                reservationAdapter.Table.setText(dataStructureReservation.getmTable());
                reservationAdapter.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*
                        Intent i = new Intent(this,MainActivity.class);
                        i.putExtra("cusname",dataStructureReservation.getmCusName());
                         */
                        Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @NonNull
            @Override
            public ReservationAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new ReservationAdapter(LayoutInflater.from(ManagerActivity.this).inflate(R.layout.reservation_list,parent,false));
            }
        };


        recView.setAdapter(adapter);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddResActivity();
            }
        });
    }

    private void findAllViews() {
        btAdd = findViewById(R.id.btAdd);
        recView = findViewById(R.id.recView);
    }

    private void openAddResActivity() {
        Intent i = new Intent(this,AddResActivity.class);
        startActivity(i);
    }
}
