package com.example.bloodhero.Email;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.bloodhero.R;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * This class represents the functions for sending email to a user from the app
 * @author Saifur Rahman Durjoy (Saifur-Durjoy)
 * @since 2022
 */
public class JavaMailApi extends AsyncTask<Void, Void, Void> {

    /**
     * Context instance declared here
     */
    private Context context;
    /**
     * Session instance declared named session
     */
    private Session session;
    /**
     * three string instances for the mail body
     */
    private String email, subject, message;

    /**
     * Constructor method of mail api
     * @param context
     * @param email
     * @param subject
     * @param message
     *
     */
    public JavaMailApi(Context context, String email, String subject, String message) {
        this.context = context;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    /**
     * instaced declared here for showing the progessbar
     */
    ProgressDialog progressDialog;

    /**
     * pre excecute method handles progress bar before an email gets sent successfully
     */
    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait as the email is being sent...");
        progressDialog.setTitle("Sending email to donor");
        progressDialog.show();
        super.onPreExecute();
    }

    /**
     * method for establishig the connection with online gmail port to deliver the message
     */
    @Override
    protected Void doInBackground(Void... voids) {
        Properties properties = new Properties();

        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port","465");
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl,SSLSocketFactory");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.port","465");

        session = Session.getDefaultInstance(properties, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return  new PasswordAuthentication(Util.EMAIL, Util.PASSWORD);

            }
        });
        /**
         * exception handler when emailing is unsuccesfull
         */

        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(Util.EMAIL));
            mimeMessage.addRecipients(Message.RecipientType.TO,String.valueOf(new InternetAddress(email)));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);
            Transport.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * post excecute method handles the activity screen after a mail gets delivered successfully
     * it opens a new alert dialog box
     */
   @Override
    protected void onPostExecute(Void aVoid) {
        progressDialog.dismiss();
        startAlertDialog();
        super.onPostExecute(aVoid);
    }

    /**
     * method for starting the dialog after on click activity
     */

    private void startAlertDialog() {
        AlertDialog.Builder myDialog = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View myView = inflater.inflate(R.layout.output_layout, null);
        myDialog.setView(myView);

        final AlertDialog dialog = myDialog.create();
        dialog.setCancelable(false);

        /**
         * close button instance closes the dialog box once clicked
         */
        Button closeButton = myView.findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
