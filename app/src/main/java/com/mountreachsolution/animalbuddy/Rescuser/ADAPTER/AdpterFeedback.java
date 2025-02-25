package com.mountreachsolution.animalbuddy.Rescuser.ADAPTER;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mountreachsolution.animalbuddy.R;
import com.mountreachsolution.animalbuddy.Rescuser.POJO.POJOFeedback;

import java.util.List;

public class AdpterFeedback extends RecyclerView.Adapter<AdpterFeedback.ViewHolder> {
    List<POJOFeedback>pojoFeedbacks;
    Activity activity;

    public AdpterFeedback(List<POJOFeedback> pojoFeedbacks, Activity activity) {
        this.pojoFeedbacks = pojoFeedbacks;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AdpterFeedback.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.feedback,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdpterFeedback.ViewHolder holder, int position) {
        POJOFeedback feedback = pojoFeedbacks.get(position);
        holder.tvUsername.setText(feedback.getUsername());
        holder.tvFeedback.setText(feedback.getFeedback());
        holder.tvDate.setText("Date: " + feedback.getDate());
        holder.tvTime.setText("Time: " + feedback.getTime());

    }

    @Override
    public int getItemCount() {
        return pojoFeedbacks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsername, tvFeedback, tvDate, tvTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvFeedback = itemView.findViewById(R.id.tvFeedback);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }
}
