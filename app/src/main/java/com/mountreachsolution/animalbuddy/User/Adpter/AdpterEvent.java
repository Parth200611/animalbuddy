package com.mountreachsolution.animalbuddy.User.Adpter;

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
import com.mountreachsolution.animalbuddy.User.pojo.POJOEvent;
import com.mountreachsolution.animalbuddy.urls;

import java.util.List;

public class AdpterEvent extends RecyclerView.Adapter<AdpterEvent.ViewHolder> {
    List<POJOEvent>pojoEventList;
    Activity activity;

    public AdpterEvent(List<POJOEvent> pojoEventList, Activity activity) {
        this.pojoEventList = pojoEventList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AdpterEvent.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(activity).inflate(R.layout.event,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdpterEvent.ViewHolder holder, int position) {
        POJOEvent event = pojoEventList.get(position);
        holder.tvName.setText(event.getName());
        holder.tvLocation.setText(event.getLocation());
        holder.tvDate.setText(event.getDate());
        holder.tvStarttime.setText(event.getStime());
        holder.tvEndTime.setText(event.getEtime());
        holder.tvEntry.setText(event.getEntry());
        holder.tvDescription.setText(event.getDis());
        Glide.with(activity)
                .load(urls.address + "images/"+event.getImage())
                .skipMemoryCache(true)
                .error(R.drawable.baseline_person_24)// Resize the image to 800x800 pixels
                .into(holder.ivImage);

    }

    @Override
    public int getItemCount() {
        return pojoEventList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvLocation, tvDate, tvStarttime, tvEndTime, tvEntry, tvDescription;
        ImageView ivImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvName = itemView.findViewById(R.id.tvName);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvStarttime = itemView.findViewById(R.id.tvStarttime);
            tvEndTime = itemView.findViewById(R.id.tvEndTime);
            tvEntry = itemView.findViewById(R.id.tvEntry);
            tvDescription = itemView.findViewById(R.id.tvDiscription);
        }
    }
}
