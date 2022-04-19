package com.example.bloodhero.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bloodhero.Email.JavaMailApi;
import com.example.bloodhero.Model.User;
import com.example.bloodhero.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
/**
 * This class represents users list to send mail and filtering our query
 * @author Saifur Rahman Durjoy (Saifur-Durjoy)
 * @since 2022
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>
{
    /**
     * Context instance declared here
     */
    private Context context;
    /**
     * One List Adapter instance named userAdapter, for fetching user info
     */
    private List<User> userList;

    /**
     * Constructor method of user Adapter
     * @param context
     * @param userList
     */
    public UserAdapter(Context context, List<User> userList)
    {
        this.context = context;
        this.userList = userList;
    }

    /**
     * Auto generated Java Class Built in method.
     * This method is used for establishing layout of users from front end
     * @param parent
     * @param viewType
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.user_displayed_layout, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Auto generated Java Class Built in method.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        final  User user = userList.get(position);
        holder.type.setText(user.getType());

        if(user.getType().equals("donor"))
        {
            /**
             * displaying user info that matches to the database
             */
            holder.emailNow.setVisibility(View.VISIBLE);
            holder.userName.setText(user.getName());
            holder.userEmail.setText(user.getEmail());
            holder.bloodGroup.setText(user.getBloodGroup());
            holder.phoneNumber.setText(user.getPhoneNumber());
            Glide.with(context).load(user.getProfilePictureUrl()).into(holder.userProfileImage);

            final String nameOfTheReciever = user.getName();
            final String idOfTheReciever = user.getId();

            /**
             * to send the dialog box of the email along with the email body
             */

            holder.emailNow.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                   new AlertDialog.Builder(context).setTitle("Send Email;")
                           .setMessage("Send email  to" +user.getName()+ "?").setCancelable(false)
                           .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                           {
                               @Override
                               public void onClick(DialogInterface dialogInterface, int i)
                               {
                                   DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                                           .child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

                                   reference.addValueEventListener(new ValueEventListener()
                                   {
                                       /**
                                        * method used to send the info on the mail body
                                        * @param snapshot
                                        */
                                       @Override
                                       public void onDataChange(@NonNull DataSnapshot snapshot)
                                       {
                                           String nameOfSender = snapshot.child("name").getValue().toString();
                                           String email = snapshot.child("email").getValue().toString();
                                           String phone = snapshot.child("phonenumber").getValue().toString();
                                           String blood = snapshot.child("bloodgroup").getValue().toString();

                                           String mEmail = user.getEmail();
                                           String mSubject = "Blood Donation";
                                           String mMessage = "Hello" + nameOfTheReciever+ ","+ "nameOfSender"
                                                   +"would like blood donation from you. Her is his/her details:\n"
                                                   +"Name:"+nameOfSender +"\n"
                                                   +"Phone:" +phone +"\n"
                                                   +"Blood Grpup:" +blood +"\n"
                                                   +"Kindly Reach out to the recipient. Thank You";

                                           JavaMailApi javaMailApi = new JavaMailApi(context, mEmail, mSubject,mMessage);
                                           javaMailApi.execute();

                                           DatabaseReference ref = FirebaseDatabase.getInstance().getReference("emails")
                                                   .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                                           ref.child(idOfTheReciever).setValue(true).addOnCompleteListener(new OnCompleteListener<Void>()
                                           {
                                               @Override
                                               public void onComplete(@NonNull Task<Void> task)
                                               {
                                                   if(task.isSuccessful())
                                                   {
                                                       DatabaseReference recieverref = FirebaseDatabase.getInstance().getReference("emails")
                                                               .child(idOfTheReciever);
                                                       recieverref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(true);
                                                   }
                                               }
                                           });
                                       }
                                       @Override
                                       public void onCancelled(@NonNull DatabaseError error)
                                       {

                                       }
                                   });
                               }
                           })
                           .setNegativeButton("No", null).show();
                }
            });
        }
    }

    /**
     * method the check for populated user
     * @return userlist.size()
     */
    @Override
    public int getItemCount()
    {
        return userList.size();
    }


     /**
     * This class describes an item view for created adapter
     * @author Saifur Rahman Durjoy (Saifur-Durjoy)
     * @since 2022
     */

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        /**
         * instances belonging to the class
         */
        public CircleImageView userProfileImage;
        public TextView type, userName, userEmail, phoneNumber, bloodGroup;
        public Button emailNow;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            /**
             * connecting the front end with backend
             */

            userProfileImage = itemView.findViewById(R.id.userProfileImage);
            type = itemView.findViewById(R.id.type);
            userName = itemView.findViewById(R.id.userName);
            userEmail = itemView.findViewById(R.id.userEmail);
            phoneNumber = itemView.findViewById(R.id.phoneNumber);
            bloodGroup = itemView.findViewById(R.id.bloodGroup);
            emailNow = itemView.findViewById(R.id.emailNow);
        }
    }
}
