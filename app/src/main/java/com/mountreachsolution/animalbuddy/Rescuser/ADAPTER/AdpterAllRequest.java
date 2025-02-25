package com.mountreachsolution.animalbuddy.Rescuser.ADAPTER;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mountreachsolution.animalbuddy.R;
import com.mountreachsolution.animalbuddy.Rescuser.POJO.POJOAllRequest;
import com.mountreachsolution.animalbuddy.Rescuser.RequestDetails;
import com.mountreachsolution.animalbuddy.urls;

import java.util.List;

public class AdpterAllRequest extends RecyclerView.Adapter<AdpterAllRequest.viewholder> {
    List<POJOAllRequest> pojoAllRequests;
    Activity activity;

    public AdpterAllRequest(List<POJOAllRequest> pojoAllRequests, Activity activity) {
        this.pojoAllRequests = pojoAllRequests;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AdpterAllRequest.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(activity).inflate(R.layout.request,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdpterAllRequest.viewholder holder, int position) {
        POJOAllRequest request=pojoAllRequests.get(position);
        holder.tvUsername.setText(request.getUsername());
        holder.tvLocation.setText(request.getLocation());
        holder.tvDetails.setText(request.getDetail());
        Glide.with(activity)
                .load(urls.address + "images/"+request.getImage())
                .skipMemoryCache(true)
                .error(R.drawable.baseline_person_24)// Resize the image to 800x800 pixels
                .into(holder.ivImage);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, RequestDetails.class);
                i.putExtra("id",request.getId());
                i.putExtra("username",request.getUsername());
                i.putExtra("location",request.getLocation());
                i.putExtra("details",request.getDetail());
                i.putExtra("image",request.getImage());
                activity.startActivity(i);

            }
        });


    }

    @Override
    public int getItemCount() {
        return pojoAllRequests.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvUsername, tvLocation, tvDetails;
        CardView card;


        public viewholder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvDetails = itemView.findViewById(R.id.tvDetails);
            card = itemView.findViewById(R.id.card);
        }
    }
}
