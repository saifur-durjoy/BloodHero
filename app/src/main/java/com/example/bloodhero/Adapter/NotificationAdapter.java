package com.example.bloodhero.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bloodhero.R;
import com.example.bloodhero.model.Notification;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * This class represent the notification
 * CSE327 project documentations
 * @author Arfana Rahman_1831172042
 * @since 2022
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder>{

    private Context context;
    private List<Notification> notificationList;


    public NotificationAdapter(Context context, List<Notification> notificationList) {
        this.context = context;
        this.notificationList = notificationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notification_item,parent,false);
        return new NotificationAdapter.ViewHolder(view);

    }

    /**
     * Shows the notification with the date.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Notification notification = notificationList.get(position);

        holder.notification_text.setText(notification.getText());
        holder.notification_date.setText(notification.getDate());

        getUserInfo(holder.notification_profile_image, holder.notification_name, notification.getSenderId());

    }

    /**
     * Get the notification from the users
     * @return Shows the notification list size
     */

    @Override
    public int getItemCount() {
        return notificationList.size();
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

    private void getUserInfo(final CircleImageView circleImageView, final TextView nameTextView, final String senderId) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users").child(senderId);
        reference.addValueEventListener(new ValueEventListener() {
            /**
             * Firebase class built in method to query for data and updates
             * @param snapshot
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                nameTextView.setText(user.getName());
                Glide.with(context).load(user.getProfilepictureurl()).into(circleImageView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
