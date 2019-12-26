package com.example.tableclearv2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SettingsFragment extends Fragment {
    public SettingsFragment() {

    }
    ListView listview;
    String mTitle[] = {"Edit Account",
                       "Booking History",
                       "Add Profile Information",
                       "Edit Profile Information",
                       "Change Language",
                       "Signout"};
    String mDescription[] = {"Editing your Password",
                             "Checking your Booking History",
                             "Adding your Profile",
                             "Editing your Profile",
                             "Changing Language of the Application",
                             "Signing out of your Account"};
    int images[] = {R.drawable.ic_account,
                    R.drawable.ic_history,
                    R.drawable.ic_add,
                    R.drawable.ic_edit,
                    R.drawable.ic_language,
                    R.drawable.ic_logout};



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_settings,container,false);

        listview = v.findViewById(R.id.listView);
        MyAdapter adapter = new MyAdapter(getActivity(),mTitle,mDescription,images);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {
                    Toast.makeText(getActivity(),"Item 1", Toast.LENGTH_SHORT).show();
                }

                if(position == 1) {
                    Toast.makeText(getActivity(),"Item 2", Toast.LENGTH_SHORT).show();
                }

                if(position == 2) {
                     openAddProfileActivity();
                }

                if(position == 3) {
                    openEditProfileActivity();
                }

                if(position == 4) {
                    Toast.makeText(getActivity(),"Item 5", Toast.LENGTH_SHORT).show();
                }

                if(position == 5) {
                    onBackPressed();
                }
            }
        });
        return v;
    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Are you sure you want to logout?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();
                        openMainActivity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String rTitle[];
        String rDescription[];
        int rImages[];

        MyAdapter (Context c, String title[], String description[], int images[]) {
            super(c,R.layout.row,R.id.title,title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
            this.rImages = images;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row,parent,false);
            ImageView images = row.findViewById(R.id.image);
            TextView mTitle = row.findViewById(R.id.title);
            TextView mDescription = row.findViewById(R.id.description);

            images.setImageResource(rImages[position]);
            mTitle.setText(rTitle[position]);
            mDescription.setText(rDescription[position]);

            return row;
        }
    }

    private void openMainActivity() {
        Intent i = new Intent(getActivity(),MainActivity.class);
        startActivity(i);
    }

    private void openAddProfileActivity() {
        Intent i = new Intent(getActivity(),AddProfileActivity.class);
        startActivity(i);
    }

    private void openEditProfileActivity() {
        Intent i = new Intent(getActivity(),EditProfileActivity.class);
        startActivity(i);
    }
}
