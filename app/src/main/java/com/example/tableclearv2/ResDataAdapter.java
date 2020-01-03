package com.example.tableclearv2;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ResDataAdapter extends RecyclerView.ViewHolder {
    public TextView mResName, mResAddress, mResRating;

    public ResDataAdapter(@NonNull View itemView) {
        super(itemView);
        mResName = itemView.findViewById(R.id.res_name);
        mResAddress = itemView.findViewById(R.id.res_address);
        mResRating = itemView.findViewById(R.id.res_rating);
    }
}
