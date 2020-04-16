package com.example.tableclearv2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReservationActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {
    EditText ResName, ResAddress, CusName, CusPhone, tvTime, tvDate, tvTable;
    Button btTime, btDate, btBook;
    NumberPicker numberPicker;
    FirebaseDatabase database, database2, database3;
    DatabaseReference myRef, myRef2;
    DatabaseReference ref;
    DataStructureReservation reservation;
    DataStructureBooking booking;
    DataStructureGPS mGPS;
    TextView lat, lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        findAllViews();
        getDatabase();
        getDatabase2();
        getDatabase3();

        final String mResName = getIntent().getStringExtra("resname");
        String mResAddress = getIntent().getStringExtra("resaddress");

        ResName.setText(mResName);
        ResAddress.setText(mResAddress);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(100);
        numberPicker.setOnValueChangedListener(this);

        //Buttons function
        btTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTime();
                tvTime.setVisibility(View.VISIBLE);
            }
        });

        btDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleDate();
                tvDate.setVisibility(View.VISIBLE);
            }
        });

        btBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float mlat = Float.parseFloat(lat.getText().toString());
                float mlon = Float.parseFloat(lon.getText().toString());
                if(mlat > 44.01 || mlat < 43.01 || mlon > 80.01 || mlon < 79.01) {
                    writeData(ResName.getText(),ResAddress.getText(),CusName.getText(),CusPhone.getText(),tvTime.getText(),tvDate.getText(),tvTable.getText());
                    writeData2(ResName.getText(),ResAddress.getText(),tvTime.getText(),tvDate.getText(),tvTable.getText());
                }

                else if(mlat > 43.01 && mlat < 44.01 && mlon > 79.01 && mlon < 80.01) {
                    Toast.makeText(getApplicationContext(), "Table is already occupied. Please choose another table!", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void getDatabase() {
        database = FirebaseDatabase.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String path = "managerdata/reservation/";
        myRef = database.getReference(path);
    }

    private void getDatabase2() {
        database2 = FirebaseDatabase.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String path = "userdata/" + mAuth.getUid() + "/bookingdata/";
        myRef2 = database2.getReference(path);
    }

    private void getDatabase3(){
        ref = FirebaseDatabase.getInstance().getReference().child("gpssensor");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String lat1 = dataSnapshot.child("lat").getValue().toString();
                String lon1 = dataSnapshot.child("lon").getValue().toString();
                lat.setText(lat1);
                lon.setText(lon1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private DataStructureReservation createData(Editable mResName,
                                                Editable mResAddress,
                                                Editable mCusName,
                                                Editable mCusPhone,
                                                Editable mTime,
                                                Editable mDate,
                                                Editable mTable) {

        return new DataStructureReservation(String.valueOf(mResName),
                                            String.valueOf(mResAddress),
                                            String.valueOf(mCusName),
                                            String.valueOf(mCusPhone),
                                            String.valueOf(mTime),
                                            String.valueOf(mDate),
                                            String.valueOf(mTable));
    }

    private DataStructureBooking createData2(Editable mResName,
                                             Editable mResAddress,
                                             Editable mTime,
                                             Editable mDate,
                                             Editable mTable) {

        return new DataStructureBooking(String.valueOf(mResName),
                                        String.valueOf(mResAddress),
                                        String.valueOf(mTime),
                                        String.valueOf(mDate),
                                        String.valueOf(mTable));
    }

    private void writeData(Editable mResName,
                           Editable mResAddress,
                           Editable mCusName,
                           Editable mCusPhone,
                           Editable mTime,
                           Editable mDate,
                           Editable mTable) {

        DataStructureReservation reservation = createData(mResName,mResAddress,mCusName,mCusPhone,mTime,mDate,mTable);
        myRef.push().setValue(reservation).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Booking Successfully!", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Booking Failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void writeData2(Editable mResName,
                            Editable mResAddress,
                            Editable mTime,
                            Editable mDate,
                            Editable mTable) {

        DataStructureBooking booking = createData2(mResName,mResAddress,mTime,mDate,mTable);
        myRef2.push().setValue(booking).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                //Toast.makeText(getApplicationContext(), "Booking Saved!", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //Toast.makeText(getApplicationContext(), "Saving Failed!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void handleTime() {
        Calendar calendar = Calendar.getInstance();

        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                String timestring;
                if(minute < 10) {
                    timestring = hour + ":0" + minute;
                }

                else {
                    timestring = hour + ":" + minute;
                }
                tvTime.setText(timestring);
            }
        }, HOUR, MINUTE, true);

        timePickerDialog.show();
    }

    private void handleDate() {
        Calendar calendar = Calendar.getInstance();

        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH) + 1;
        int DATE = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int date) {
                String datestring;
                if(month < 10 && date < 10) {
                    datestring = year + "/0" + month + "/0" + date;
                }

                else if(month < 10) {
                    datestring = year + "/0" + month + "/" + date;
                }

                else if(date < 10) {
                    datestring = year + "/" + month + "/0" + date;
                }

                else {
                    datestring = year + "/" + month + "/" + date;
                }

                tvDate.setText(datestring);
            }
        }, YEAR, MONTH, DATE);
        datePickerDialog.show();
    }

    private void findAllViews() {
        ResName = findViewById(R.id.mResName);
        ResAddress = findViewById(R.id.mResAddress);
        CusName = findViewById(R.id.mCusName);
        CusPhone = findViewById(R.id.mCusPhone);
        btTime = findViewById(R.id.timepick);
        btDate = findViewById(R.id.datepick);
        btBook = findViewById(R.id.btBook);
        tvTime = findViewById(R.id.mTime);
        tvDate = findViewById(R.id.mDate);
        tvTable = findViewById(R.id.mTable);
        numberPicker = findViewById(R.id.numberPicker);
        lat = findViewById(R.id.lat);
        lon = findViewById(R.id.lon);
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        tvTable.setText(String.valueOf(newVal));
    }

    /*
    private void retrieveData(){
        myRef3.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DataStructureGPS ds = dataSnapshot.getValue(DataStructureGPS.class);
                lat.setText(ds.getmLat());
                lon.setText(ds.getmLon());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DataStructureGPS ds = dataSnapshot.getValue(DataStructureGPS.class);
                lat.setText(ds.getmLat());
                lon.setText(ds.getmLon());
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

        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<DataStructureGPS> arraylist = new ArrayList<DataStructureGPS>();

                if(dataSnapshot != null && dataSnapshot.getValue() != null){
                    for(DataSnapshot a : dataSnapshot.getChildren()) {
                        DataStructureGPS dataStructureGPS = new DataStructureGPS();
                        dataStructureGPS.setmLat(a.getValue(DataStructureGPS.class).getmLat());
                        dataStructureGPS.setmLon(a.getValue(DataStructureGPS.class).getmLon());

                        arraylist.add(dataStructureGPS);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Data unavailable", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Loading data failed", Toast.LENGTH_LONG).show();
            }
        });
    }

     */
}
