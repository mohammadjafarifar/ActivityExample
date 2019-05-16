package com.example.activityexample;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterRecycle extends RecyclerView.Adapter<AdapterRecycle.AdapterRecycleHolder> {


    private ArrayList<PersonalViewModel> myList;

    AdapterRecycle(ArrayList<PersonalViewModel> list) {
        myList = list;
    }

    @NonNull
    @Override
    public AdapterRecycleHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleitem, parent, false);

        AdapterRecycleHolder holder = new AdapterRecycleHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecycleHolder adapterRecycleHolder, int position) {
        if (position % 2 == 1) {
            adapterRecycleHolder.root.setBackgroundResource(R.color.colorAccent);
        }else
            adapterRecycleHolder.root.setBackgroundResource(R.color.colorPrimary);

        String name = myList.get(position).GetName();
        String Family=myList.get(position).GetFamily();
        int Tel=myList.get(position).GetTell();
        String Address =myList.get(position).GetAddress();
        adapterRecycleHolder.txtName.setText(name);
        adapterRecycleHolder.txtFamily.setText(Family);
        adapterRecycleHolder.txtTel.setText(Integer.toString(Tel));
        adapterRecycleHolder.txtAddress.setText(Address);
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class AdapterRecycleHolder extends RecyclerView.ViewHolder{

        TextView txtName;
        TextView txtFamily;
        TextView txtTel;
        TextView txtAddress;
        LinearLayoutCompat root;
        public AdapterRecycleHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtFamily=itemView.findViewById(R.id.txtFamily);
            txtTel=itemView.findViewById(R.id.txtTel);
            txtAddress=itemView.findViewById(R.id.txtAddress);
            root = itemView.findViewById(R.id.root);
        }
    }
}
