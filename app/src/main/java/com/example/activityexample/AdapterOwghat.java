package com.example.activityexample;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;


public class AdapterOwghat extends RecyclerView.Adapter<AdapterOwghat.HolderAdapter> {

    ArrayList<String> Temp=new ArrayList<>();

    String clickitemes="";
    AdapterOwghat(ArrayList<String>Temp)
    {
        this.Temp=Temp;
    }

    @NonNull
    @Override
    public HolderAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemowghat,viewGroup,false);
        final HolderAdapter holder = new HolderAdapter(v,mListener);


         return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderAdapter holderAdapter, int i) {

        holderAdapter.itemOwghat.setText(Temp.get(i));
    }

    @Override
    public int getItemCount() {
        return Temp.size();
    }
    public interface OnItemClickListener {
        void onItemClick(int position);

        void onDeleteClick(int position);
    }
    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public class HolderAdapter extends RecyclerView.ViewHolder {
        TextView itemOwghat;
        TextView txtSobh,txtZohr,txtMaghreb;

        public HolderAdapter(@NonNull View itemView,final OnItemClickListener listener) {
            super(itemView);
            itemOwghat=itemView.findViewById(R.id.txtItemOwghat);
            txtSobh=itemView.findViewById(R.id.txtSobh);
            txtZohr=itemView.findViewById(R.id.txtZohr);
            txtMaghreb=itemView.findViewById(R.id.txtMaghreb);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                          listener.onItemClick(position);

                        }
                    }
                }
            });

        }
    }
}
