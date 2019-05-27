package online.bcasino.pbonus.review.adminapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Health_Record extends AppCompatActivity {
    CheckBox cbF,cbH,cbS,cbD,cbHP;
    Spinner bp;
    EditText details;
    DatabaseReference mref;
    String pid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health__record);
        cbD=findViewById(R.id.cbD);
        cbD.setEnabled(false);
        cbF=findViewById(R.id.cbF);
        cbF.setEnabled(false);
        cbH=findViewById(R.id.cbH);
        cbH.setEnabled(false);
        cbS=findViewById(R.id.cbS);
        cbS.setEnabled(false);
        cbHP=findViewById(R.id.cbHP);
        cbHP.setEnabled(false);
        bp=findViewById(R.id.bp);
        mref= FirebaseDatabase.getInstance().getReference("Users");
        details=findViewById(R.id.details);
        getintent();
        getdata();
    }
    public void getintent(){
        Intent intent=getIntent();
       pid =intent.getStringExtra("pid");
    }
    public void getdata(){
        mref.child(pid).child("HR").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    HealthD_model healthDModel=dataSnapshot.getValue(HealthD_model.class);
                    if (healthDModel.getSmoker().equals("1")){
                        cbS.setChecked(true);
                        cbS.setEnabled(false);
                    }
                    if (healthDModel.getDiabetes().equals("1")){
                        cbD.setChecked(true);
                        cbD.setEnabled(false);
                    }
                    if (healthDModel.getFever().equals("1")){
                        cbF.setChecked(true);
                        cbF.setEnabled(false);
                    }
                    if (healthDModel.getHeadache().equals("1")){
                        cbH.setChecked(true);
                        cbH.setEnabled(false);
                    }
                    if (healthDModel.getHp().equals("1")){
                        cbHP.setChecked(true);
                        cbHP.setEnabled(false);

                    }
                    bp.setSelection(Integer.parseInt(healthDModel.getBp()));
                    bp.setEnabled(false);
                    details.setText(healthDModel.getDetails());
                    details.setEnabled(false);


                }
                else
                    Toast.makeText(Health_Record.this, "Kindly update your health details", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}

