package online.bcasino.pbonus.review.adminapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Appoinments_details extends AppCompatActivity {
    EditText timeInterval,date,status;
    TextView uname;
    ImageView uImg;
    Button call;
    DatabaseReference mref,mref2;
    Apntments_Model apntments_model;
    String aID;
    String pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoinments_details);
        uname=findViewById(R.id.uname);

//        Just for test purpose
        uname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call.setEnabled(true);
                call.setBackground(getResources().getDrawable(R.drawable.rountcorner));
                call.setBackgroundColor(getResources().getColor(R.color.parrot));
                call.setTextColor(getResources().getColor(R.color.white));
            }
        });
        timeInterval=findViewById(R.id.time_interval);
        date=findViewById(R.id.date);
        uImg=findViewById(R.id.uimg);
        call=findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkforPermission();
            }
        });
        status=findViewById(R.id.status);
        get_intent();
        getApp_data();
        uneditable();
    }
    public void get_intent(){
        Intent intent=getIntent();
        aID=intent.getStringExtra("aID");
    }
    public void getApp_data(){
        mref= FirebaseDatabase.getInstance().getReference("Appointments").child("Booked").child(aID);
        mref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Apntments_Model apntments_model = dataSnapshot.getValue(Apntments_Model.class);

                    uname.setText(apntments_model.getuName());
                    timeInterval.setText(apntments_model.getsTime()+"-"+apntments_model.geteTime());
                    date.setText(apntments_model.getDate());
                    status.setText(apntments_model.getStatus());
                    pid=apntments_model.getuID();
                    mref2=FirebaseDatabase.getInstance().getReference("Users").child(apntments_model.getuID());
                    mref2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Glide.with(Appoinments_details.this).load(dataSnapshot.child("profilePic").getValue(String.class)).fitCenter().into(uImg);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void verify(){

    }
    public void uneditable(){
        uname.setEnabled(false);
        timeInterval.setEnabled(false);
        date.setEnabled(false);
        status.setEnabled(false);
//        call.setEnabled(false);
    }
    public void checkforPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Camera permission not granted", Toast.LENGTH_SHORT).show();
            // Permission is not granted
        }
        else if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Microphone permission not granted", Toast.LENGTH_SHORT).show();
            // Permission is not granted
        }
        else if (ContextCompat.checkSelfPermission(this, Manifest.permission.MODIFY_AUDIO_SETTINGS)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Speaker permission not granted", Toast.LENGTH_SHORT).show();
            // Permission is not granted

        }
        else {
            mref= FirebaseDatabase.getInstance().getReference("Appointments").child("Booked").child(aID);
            mref.child("active").setValue("1");
            Intent intent = new Intent(Appoinments_details.this, Calling.class);
            intent.putExtra("room",aID);
            startActivity(intent);
        }

    }
    public void openHD(View view){
        Intent intent=new Intent(this,Health_Record.class);
        intent.putExtra("pid",pid);
        startActivity(intent);
    }


}