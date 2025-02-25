package com.mountreachsolution.animalbuddy.Rescuser.ADAPTER;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mountreachsolution.animalbuddy.R;
import com.mountreachsolution.animalbuddy.Rescuser.POJO.POJOGetHistory;
import com.mountreachsolution.animalbuddy.urls;

import java.util.List;

public class AdpterHistory extends RecyclerView.Adapter<AdpterHistory.ViewHolder> {
    List<POJOGetHistory>pojoGetHistories;
    Activity activity;

    public AdpterHistory(List<POJOGetHistory> pojoGetHistories, Activity activity) {
        this.pojoGetHistories = pojoGetHistories;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AdpterHistory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.history,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdpterHistory.ViewHolder holder, int position) {
        POJOGetHistory request=pojoGetHistories.get(position);

        holder.tvUsername.setText(request.getUsername());
        holder.tvName.setText(request.getName());
        holder.tvLocation.setText(request.getLocation());
        holder.tvDetails.setText(request.getDetail());
        holder.tvStatus.setText(request.getStatus());
        Glide.with(activity)
                .load(urls.address + "images/"+request.getImage())
                .skipMemoryCache(true)
                .error(R.drawable.baseline_person_24)// Resize the image to 800x800 pixels
                .into(holder.ivImage);

    }

    @Override
    public int getItemCount() {
        return pojoGetHistories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvUsername, tvName, tvMobile, tvLocation, tvDetails, tvStatus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvName = itemView.findViewById(R.id.tvname);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvDetails = itemView.findViewById(R.id.tvDetails);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }
    }
}
