package com.example.tableclearv2;

import androidx.appcompat.app.AppCompatDialogFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Dialog;

public class AboutMeDialog extends AppCompatDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("About Table Clear")
                .setMessage("Table Clear is an efficient wait-list, restaurant booking and management system.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }

    public static class DataStructure_Customer {
        private String fullname;
        private String phone;
        private String address;

        public DataStructure_Customer() {

        }

        public DataStructure_Customer(String fullname, String phone, String address) {
            this.fullname = fullname;
            this.phone = phone;
            this.address = address;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
