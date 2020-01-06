package com.example.tableclearv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    public HomeFragment() {

    }
    RecyclerView recyclerView;
    ArrayList<DataStructureRestaurant> arrayList;
    FirebaseRecyclerOptions<DataStructureRestaurant> options;
    FirebaseRecyclerAdapter<DataStructureRestaurant,ResDataAdapter> adapter;
    DatabaseReference databaseReference;


    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home,container,false);

        recyclerView = v.findViewById(R.id.recView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        arrayList = new ArrayList<DataStructureRestaurant>();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("restaurantdata");
        databaseReference.keepSynced(true);
        options = new FirebaseRecyclerOptions.Builder<DataStructureRestaurant>()
                .setQuery(databaseReference,DataStructureRestaurant.class).build();

        adapter = new FirebaseRecyclerAdapter<DataStructureRestaurant, ResDataAdapter>(options) {

            @Override
            protected void onBindViewHolder(@NonNull ResDataAdapter resDataAdapter, int i, @NonNull final DataStructureRestaurant dataStructureRestaurant) {
                resDataAdapter.mResName.setText(dataStructureRestaurant.getResName());
                resDataAdapter.mResAddress.setText(dataStructureRestaurant.getResAddress());
                resDataAdapter.mResRating.setText(dataStructureRestaurant.getResRating());
                resDataAdapter.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(),ReservationActivity.class);
                        i.putExtra("resname",dataStructureRestaurant.getResName());
                        i.putExtra("resaddress",dataStructureRestaurant.getResAddress());
                        i.putExtra("resrating",dataStructureRestaurant.getResRating());
                        startActivity(i);
                    }
                });
            }

            @NonNull
            @Override
            public ResDataAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new ResDataAdapter(LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_list,parent,false));
            }
        };

        recyclerView.setAdapter(adapter);

        return v;
    }
}
