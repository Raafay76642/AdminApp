package online.bcasino.pbonus.review.adminapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.zip.Inflater;

public class Profile_ extends AppCompatActivity {
EditText name,email,department,country;
FirebaseAuth firebaseAuth;
DatabaseReference mref;
String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);
        mref= FirebaseDatabase.getInstance().getReference("Doctors");

        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        department=(EditText)findViewById(R.id.department);
        country=(EditText)findViewById(R.id.country);
        id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        get_data();
        uneditable();

    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,Doc_main.class);
        startActivity(intent);
        super.onBackPressed();
    }
    public void uneditable(){
        email.setEnabled(false);
        name.setEnabled(false);
        department.setEnabled(false);
        country.setEnabled(false);

    }
    public void get_data(){
        id=firebaseAuth.getInstance().getCurrentUser().getUid();
        mref.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                        Doctor_Model doctorModel = dataSnapshot.getValue(Doctor_Model.class);
                        name.setText(doctorModel.name);
                        department.setText(doctorModel.department);
                        email.setText(doctorModel.email);
                        country.setText(doctorModel.country);

                     }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
