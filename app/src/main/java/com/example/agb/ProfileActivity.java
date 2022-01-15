package com.example.agb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

import static com.example.agb.ClothingTransaction.Clothingaff;
import static com.example.agb.EntertainmentTransaction.Entertainmentaff;
import static com.example.agb.FoodTransaction.Foodaff;
import static com.example.agb.GroceriesTransaction.Groceriesaff;
import static com.example.agb.HealthTransaction.Healthaff;
import static com.example.agb.HomeTransaction.Homeaff;
import static com.example.agb.OtherTransaction.Otheraff;
import static com.example.agb.StudiesTransaction.Studiesaff;
import static com.example.agb.TechnologyTransaction.Technologyaff;
import static com.example.agb.TransportTransaction.Transportaff;
import static java.lang.Integer.parseInt;


public class ProfileActivity extends AppCompatActivity {

    //declaration de variables
    private ImageView ProfileImage;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    DrawerLayout drawerLayout;
    private FirebaseUser user, userProfile;
    private DatabaseReference reference, RefEmail;
    private String userID, userProfileID;
    private Button toExp;
    private Button modify, send;
    final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;
    TextView mEditTextTo;
    String SmsToSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        drawerLayout = findViewById(R.id.drawer_layout);

// Change your profile image
        ProfileImage = findViewById(R.id.profile_image);
        ProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent();
                gallery.setType("iamge/+");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "select Picture"), PICK_IMAGE);
            }
        });
//Buttons
        modify = findViewById(R.id.toDash);
        send = findViewById(R.id.infoButton);

// Modify Profile: Go To Dashboard
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphamod = new AlphaAnimation(0f, 1f);
                alphamod.setDuration(100);
                modify.startAnimation(alphamod);
                //end animation button
                Intent intToDash = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intToDash);
            }
        });

// Send SMS
        send.setEnabled(false);
        if(checkPermission(Manifest.permission.SEND_SMS)){
            send.setEnabled(true);
        }else{
            ActivityCompat.requestPermissions(ProfileActivity.this, new
                    String[]{Manifest.permission.SEND_SMS},SEND_SMS_PERMISSION_REQUEST_CODE);
        }


        // Firebase get Id

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance("https://app-gestion-de-budget-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Dashboard");
        userID = user.getUid();

        // find View By Id
        final TextView firstNameTextView = findViewById(R.id.first_name);
        //final TextView lastNameTextView = findViewById(R.id.last_name);
        final TextView phoneNumberTextView = findViewById(R.id.phone_number);
        final TextView monthlyIncomeTextView = findViewById(R.id.monthly_income);
        final TextView savingsTextView = findViewById(R.id.savings);

        // reference child
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Dashboard userProfile = dataSnapshot.getValue(Dashboard.class);
                if(userProfile != null) {
                    String firstName = userProfile.firstName;
                    String lastName = userProfile.lastName;
                    String phoneNumber = userProfile.phoneNumber;
                    String monthlyIncome = userProfile.monthlyIncome;
                    String savings = userProfile.savings;

                    firstNameTextView.setText(firstName);
                    //lastNameTextView.setText(lastName);
                    phoneNumberTextView.setText(phoneNumber);
                    monthlyIncomeTextView.setText(monthlyIncome);
                    savingsTextView.setText(savings);

                    Integer Somme_depenses = parseInt(Clothingaff) + parseInt(Foodaff) + parseInt(Studiesaff) + parseInt(Homeaff) + parseInt(Transportaff) + parseInt(Technologyaff) + parseInt(Healthaff) + parseInt(Groceriesaff) + parseInt(Entertainmentaff) + parseInt(Otheraff);

// Account Information
                    SmsToSend = "Hi " + firstName + " " + lastName + " Your number is " + phoneNumber + ". Your total expenses are "
                            + Somme_depenses + " detailed: \n" + Clothingaff + " for clothing expenses and \n" + Foodaff + " for food expenses and \n" + Homeaff + " for home related expenses and \n"
                            + Studiesaff + " for studies expenses and \n" + Technologyaff + " for technology related expenses and \n" + Transportaff + " for transport expenses and \n" + Healthaff + " for health expenses and \n"
                            + Groceriesaff + " for the groceries and \n" + Entertainmentaff + " for the entertainment and finally \n" + Otheraff + " for any other expenses you had. \n"
                            + "The amount of money you want to save per month is " + savings + " \n"
                            + " Thank you for your trust in our application. Happy to serve you!";
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this, "Something Wrong Happened!", Toast.LENGTH_SHORT).show();
            }
        });


        //Send Message
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphasend = new AlphaAnimation(0f, 1f);
                alphasend.setDuration(100);
                send.startAnimation(alphasend);
                //end animation button
                onSend(view);
            }
        });

        mEditTextTo = findViewById(R.id.editTextTextEmailAddress);

// Send me an email: Account Information
        findViewById(R.id.EmailButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //animation button
                AlphaAnimation alphaemail = new AlphaAnimation(0f, 1f);
                alphaemail.setDuration(100);
                findViewById(R.id.EmailButton).startAnimation(alphaemail);
                //end animation button

                String receipeintList = mEditTextTo.getText().toString()+",";
                String[] recipients = receipeintList.split(",");
                String subject = "Account Informations";
                String message = SmsToSend;

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT,subject);
                intent.putExtra(Intent.EXTRA_TEXT,message);

                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent,"Choose an email client"));
            }
        });
    }

// Envoi du SMS Profile Summury : début

    public void onSend(View v){
        TextView number = findViewById(R.id.phone_number);
        String phoneNumber = number.getText().toString();
        phoneNumber = "+216" + phoneNumber;

        String smsMessage = SmsToSend;

        if(phoneNumber == null || phoneNumber.length() == 0 || smsMessage == null || smsMessage.length() == 0){
            return;
        }

        if(checkPermission(Manifest.permission.SEND_SMS)){
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, smsMessage, null, null);
            Toast.makeText(ProfileActivity.this, "Message Sent!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(ProfileActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(ProfileActivity.this, permission);
        return(check == PackageManager.PERMISSION_GRANTED);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                ProfileImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    // Envoi du SMS Profile Summury :: Fin

    // OnClick Methods
    // Click Menu
    public void ClickMenu(View view) {
        //animation button
        AlphaAnimation alphaOpenMenu = new AlphaAnimation(0f, 1f);
        alphaOpenMenu.setDuration(100);
        findViewById(R.id.MenuClickable).startAnimation(alphaOpenMenu);
        //end animation button
        MyExpenses.openDrawer(drawerLayout);
    }

    // Click Logo
    public void ClickLogo(View view) {
        //animation button
        AlphaAnimation alphaCloseLogo = new AlphaAnimation(0f, 1f);
        alphaCloseLogo.setDuration(100);
        findViewById(R.id.imageView17).startAnimation(alphaCloseLogo);
        //end animation button
        MyExpenses.closeDrawer(drawerLayout);
    }

    // Click Home
    public void ClickHome(View view) {
        //animation button
        AlphaAnimation alphaOpenStat = new AlphaAnimation(0f, 1f);
        alphaOpenStat.setDuration(100);
        findViewById(R.id.StatisticsClickable).startAnimation(alphaOpenStat);
        //end animation button
        MyExpenses.redirectActivity(this, statistics.class);
    }

    // Click Profile
    public void ClickProfile(View view) {
        //animation button
        AlphaAnimation alphaOpenProfile = new AlphaAnimation(0f, 1f);
        alphaOpenProfile.setDuration(100);
        findViewById(R.id.ProfileClickable).startAnimation(alphaOpenProfile);
        //end animation button
        MyExpenses.redirectActivity(this, ProfileActivity.class);
    }

    // Click Incomes
    public void ClickIncomes(View view){
        //animation button
        AlphaAnimation alphaOpenInco = new AlphaAnimation(0f, 1f);
        alphaOpenInco.setDuration(100);
        findViewById(R.id.IncomesClickable).startAnimation(alphaOpenInco);
        //end animation button
        MyExpenses.redirectActivity(this,IncomesActivity.class);
    }

    // Click Dashboard
    public void ClickDashboard(View view) {
        //animation button
        AlphaAnimation alphaOpenDash = new AlphaAnimation(0f, 1f);
        alphaOpenDash.setDuration(100);
        findViewById(R.id.DashboardClickable).startAnimation(alphaOpenDash);
        //end animation button
        MyExpenses.redirectActivity(this, HomeActivity.class);
    }

    // Click Expenses
    public void ClickExpenses(View view) {
        //animation button
        AlphaAnimation alphaOpenExpen = new AlphaAnimation(0f, 1f);
        alphaOpenExpen.setDuration(100);
        findViewById(R.id.ExpensesClickable).startAnimation(alphaOpenExpen);
        //end animation button
        MyExpenses.redirectActivity(this, MyExpenses.class);
    }

    // Click About Us
    public void ClickAboutUs(View view) {
        //animation button
        AlphaAnimation alphaOpenAbout = new AlphaAnimation(0f, 1f);
        alphaOpenAbout.setDuration(100);
        findViewById(R.id.AboutUsClickable).startAnimation(alphaOpenAbout);
        //end animation button
        MyExpenses.redirectActivity(this, AboutUs.class);
    }

    // Click Know More
    public void ClickKnowMore(View view){
        //animation button
        AlphaAnimation alphaOpenKnow = new AlphaAnimation(0f, 1f);
        alphaOpenKnow.setDuration(100);
        findViewById(R.id.KnowMoreClickable).startAnimation(alphaOpenKnow);
        //end animation button
        MyExpenses.redirectActivity(this,KnowMoreActivity.class);
    }

    // Click Log Out
    public void ClickLogout(View view) {
        //animation button
        AlphaAnimation alphaOpenLogOut = new AlphaAnimation(0f, 1f);
        alphaOpenLogOut.setDuration(100);
        findViewById(R.id.LogOutClickable).startAnimation(alphaOpenLogOut);
        //end animation button
        MyExpenses.Logout(this);
    }

    // Method onPause
    @Override
    protected void onPause() {
        super.onPause();
        MyExpenses.closeDrawer(drawerLayout);

    }

}
