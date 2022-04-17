package com.example.bloodhero.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder>{

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public CircleImageView notification_profile_image;
        public TextView notification_name, notification_text, notification_date;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            notification_profile_image = itemView.findViewById(R.id.notification_profile_image);
            notification_name = itemView.findViewById(R.id.notification_name);
            notification_text = itemView.findViewById(R.id.notification_text);
            notification_date = itemView.findViewById(R.id.notification_date);
        }
    }

}
