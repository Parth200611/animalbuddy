package com.mountreachsolution.animalbuddy.Rescuser.ADAPTER;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mountreachsolution.animalbuddy.R;
import com.mountreachsolution.animalbuddy.Rescuser.POJO.POJONgo;

import org.w3c.dom.ls.LSInput;

import java.util.List;

public class AdpterNgo extends RecyclerView.Adapter<AdpterNgo.ViewhOlder> {
    List<POJONgo>pojoNgos;
    Activity activity;

    public AdpterNgo(List<POJONgo> pojoNgos, Activity activity) {
        this.pojoNgos = pojoNgos;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AdpterNgo.ViewhOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.ngo,parent,false);
        return new ViewhOlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdpterNgo.ViewhOlder holder, int position) {
        POJONgo userHistory = pojoNgos.get(position);
        holder.tvName.setText(userHistory.getName());
        holder.tvMobile.setText(userHistory.getMobil());
        holder.tvAddress.setText(userHistory.getAddress());

    }

    @Override
    public int getItemCount() {
        return pojoNgos.size();
    }

    public class ViewhOlder extends RecyclerView.ViewHolder {
        TextView tvName, tvMobile, tvAddress;
        public ViewhOlder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvMobile = itemView.findViewById(R.id.tvMobile);
            tvAddress = itemView.findViewById(R.id.tvAddress);
        }
    }
}
