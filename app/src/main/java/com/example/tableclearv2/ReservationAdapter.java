package com.example.tableclearv2;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReservationAdapter extends RecyclerView.ViewHolder{
    public TextView rname, raddress, cname, cphone, mtime, mdate, mtable;

    public ReservationAdapter(@NonNull View itemView) {
        super(itemView);
        rname = itemView.findViewById(R.id.resName);
        raddress = itemView.findViewById(R.id.resAddress);
        cname = itemView.findViewById(R.id.cusName);
        cphone = itemView.findViewById(R.id.cusPhone);
        mtime = itemView.findViewById(R.id.time);
        mdate = itemView.findViewById(R.id.date);
        mtable = itemView.findViewById(R.id.table);


    }
}
