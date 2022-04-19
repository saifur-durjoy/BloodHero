package com.example.bloodhero;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
/**
 * Donor will be Registered Here
 * @author Abrar Karim
 * @version 0.1
*/
public class DonorRegistrationActivity extends AppCompatActivity {

    private View decorView;
    private CircleImageView profile_image;
    private TextInputEditText registerFullName, registerIdNumber,
            registerPhoneNumber, registerEmail, registerPassword;
    private Spinner bloodGroupsSpinner;
    private Button  registerButton;

    private Uri resultUri;

    private ProgressDialog loader;
    private FirebaseAuth mAuth;
    private DatabaseReference userDatabaseRef;

    public DonorRegistrationActivity() {
    }
    /**
     * Registering buttons
     * @author Abrar Karim
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_registration);
        View backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(view -> {
            Intent intent = new Intent (DonorRegistrationActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        profile_image = findViewById(R.id.profile_image);
        registerFullName = findViewById(R.id.registerFullName);
        registerIdNumber = findViewById(R.id.registerIdNumber);
        registerPhoneNumber = findViewById(R.id.registerPhoneNumber);
        registerEmail = findViewById(R.id.registerEmail);
        registerPassword = findViewById(R.id.registerPassword);
        bloodGroupsSpinner = findViewById(R.id.bloodGroupsSpinner);
        registerButton = findViewById(R.id.registerButton);
        loader = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();

        profile_image.setOnClickListener(new View.OnClickListener() {
            /**
             * Open (choose image)
             * @author Abrar Karim
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/");
                startActivityForResult(intent, 1);

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            /**
             * For putting values
             * Error Messages
             * After Registration screen message
             * @author Abrar Karim
             * @version 0.1
             */

            @Override
            public void onClick(View view) {
                final String email = registerEmail.getText().toString().trim();
                final String password = registerPassword.getText().toString().trim();
                final String fullName = registerFullName.getText().toString().trim();
                final String idNumber = registerIdNumber.getText().toString().trim();
                final String phoneNumber = registerPhoneNumber.getText().toString().trim();
                final String bloodGroup = bloodGroupsSpinner.getSelectedItem().toString();

                if (TextUtils.isEmpty(email)){
                    registerEmail.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    registerPassword.setError("Password is Required");
                    return;
                }
                if (TextUtils.isEmpty(fullName)){
                    registerFullName.setError("Full Name is Required");
                    return;
                }
                if (TextUtils.isEmpty(idNumber)){
                    registerIdNumber.setError("ID Number is Required");
                    return;
                }
                if (TextUtils.isEmpty(phoneNumber)){
                    registerPhoneNumber.setError("Phone Number is Required");
                }

                if (bloodGroup.equals("Select your blood group")){
                    Toast.makeText(DonorRegistrationActivity.this, "Select Blood Group", Toast.LENGTH_SHORT).show();
                }
                else {

                    loader.setMessage("Registering");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

          mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
              /**
               * Database Connection
               * Database Error
               * @author Abrar Karim
               */
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                 if(!task.isSuccessful()){
                     String error = task.getException().toString();
                     Toast.makeText(DonorRegistrationActivity.this, "Error" + error, Toast.LENGTH_SHORT).show();
                 }
                 else {
                     String currentUserID= mAuth.getCurrentUser().getUid();
                     userDatabaseRef = FirebaseDatabase.getInstance().getReference().child("users").child(currentUserID);
                     HashMap userInfo = new HashMap();
                     userInfo.put("id", currentUserID);
                     userInfo.put("name", fullName);
                     userInfo.put("email", email);
                     userInfo.put("idNumber", idNumber);
                     userInfo.put("phoneNumber", phoneNumber);
                     userInfo.put("bloodGroup", bloodGroup);
                     userInfo.put("type", "donor");
                     userInfo.put("search","donor" + bloodGroup);

                     userDatabaseRef.updateChildren(userInfo).addOnCompleteListener(new OnCompleteListener() {
                         /**
                          * Successful Registration
                          * @author Abrar Karim
                          */
                         @Override
                         public void onComplete(@NonNull Task task) {
                             if (task.isSuccessful()){
                                 Toast.makeText(DonorRegistrationActivity.this, "Data set Successful", Toast.LENGTH_SHORT).show();
                             }
                             else
                             {
                                 Toast.makeText(DonorRegistrationActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                             }

                             finish();
                            // loader.dismiss();
                         }
                     });

                     if (resultUri !=null){
                         final StorageReference filePath = FirebaseStorage.getInstance().getReference().child("Profile Image").child(currentUserID);
                         Bitmap bitmap = null;

                         try {
                             bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), resultUri);

                         }catch (IOException e){
                             e.printStackTrace();

                         }
                         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                         bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
                         byte [] data = byteArrayOutputStream.toByteArray();
                         UploadTask uploadTask = filePath.putBytes(data);

                         uploadTask.addOnFailureListener(new OnFailureListener() {
                             /**
                              * Unsuccessful Image Upload message
                              * @author Abrar Karim
                              */
                             @Override
                             public void onFailure(@NonNull Exception e) {
                                 Toast.makeText(DonorRegistrationActivity.this, "Upload Failed", Toast.LENGTH_SHORT).show();
                             }
                         });

                         uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                             @Override
                             public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                 if (taskSnapshot.getMetadata() !=null && taskSnapshot.getMetadata().getReference() !=null){
                                     Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                                     result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                         @Override
                                         public void onSuccess(Uri uri) {
                                             String imageUrl = toString();
                                             Map newImageMap = new HashMap();
                                             newImageMap.put("profilepictureurl", imageUrl);
                                             userDatabaseRef.updateChildren(newImageMap).addOnCompleteListener(new OnCompleteListener() {
                                                 /**
                                                  * Image Upload Message
                                                  * @author Abrar Karim
                                                  */
                                                 @Override
                                                 public void onComplete(@NonNull Task task) {
                                                     if (task.isSuccessful()){
                                                         Toast.makeText(DonorRegistrationActivity.this, "Image Url Added to database Successfully", Toast.LENGTH_SHORT).show();
                                                     }
                                                     else
                                                     {
                                                         Toast.makeText(DonorRegistrationActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                                     }
                                                 }
                                             });

                                             finish();

                                         }
                                     });
                                 }
                             }
                         });

                         Intent intent = new Intent(DonorRegistrationActivity.this, MainActivity1.class);
                         startActivity(intent);
                         finish();
                         loader.dismiss();


                     }

                 }
              }
          });

                }
            }
        });

        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(visibility -> {
            if (visibility == 0)
                decorView.setSystemUiVisibility(hideSystemBars());
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( requestCode == 1 && resultCode == RESULT_OK && data !=null ){
            resultUri = data.getData();
            profile_image.setImageURI(resultUri);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            decorView.setSystemUiVisibility(hideSystemBars());
        }

    }

    /**
     * Hiding Navigation Panel
     * @author Abrar Karim
     */

    private int hideSystemBars(){
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;



    }
}