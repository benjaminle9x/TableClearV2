package com.example.tableclearv2;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReservationAdapter extends RecyclerView.ViewHolder {
    public TextView CusName, CusPhone, ResName, ResAddress, Time, Date, Table;

    public ReservationAdapter(@NonNull View itemView) {
        super(itemView);
        CusName = itemView.findViewById(R.id.cusName);
        CusPhone = itemView.findViewById(R.id.cusPhone);
        ResName = itemView.findViewById(R.id.resName);
        ResAddress = itemView.findViewById(R.id.resAddress);
        Time = itemView.findViewById(R.id.time);
        Date = itemView.findViewById(R.id.date);
        Table = itemView.findViewById(R.id.table);
    }
}
