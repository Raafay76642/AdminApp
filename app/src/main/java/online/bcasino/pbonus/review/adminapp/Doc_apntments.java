package online.bcasino.pbonus.review.adminapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Doc_apntments extends AppCompatActivity {
    private RecyclerView recyclerView1;
    private Apntments_adapter adapter;
    private List<Apntments_Model> apntments_List;
    DatabaseReference mref;
    FirebaseAuth firebaseAuth;
    Button bBooked,bHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_apntments);
        firebaseAuth=FirebaseAuth.getInstance();
        mref= FirebaseDatabase.getInstance().getReference("Doctors");
        recyclerView1 = findViewById(R.id.recyclerView1);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        apntments_List = new ArrayList<>();
        adapter = new Apntments_adapter(this, apntments_List);
        recyclerView1.setAdapter(adapter);
        bBooked=findViewById(R.id.bBooked);
        bBooked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_data_booked();
            }
        });
        bHistory=findViewById(R.id.bhistory);
        bHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apntments_List.clear();
                get_data_history();
            }
        });
        get_data_booked();
    }
    public void get_data_booked(){
        String key=FirebaseAuth.getInstance().getCurrentUser().getUid();
        Query query =FirebaseDatabase.getInstance().getReference("Appointments").child("Booked").orderByChild("dID").equalTo(key);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                apntments_List.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Apntments_Model apntments_model = snapshot.getValue(Apntments_Model.class);
                        apntments_List.add(apntments_model);
                    }
                    adapter.notifyDataSetChanged();
                }
                else {
                    apntments_List.clear();
                    adapter.notifyDataSetChanged();
                    Toast.makeText(Doc_apntments.this, "No Booked Appointments", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                adapter.notifyDataSetChanged();
            }
        });
    }
    public void get_data_history(){
        String key=FirebaseAuth.getInstance().getCurrentUser().getUid();
        Query query =FirebaseDatabase.getInstance().getReference("Appointments").child("History").orderByChild("aID").equalTo(key);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                apntments_List.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Apntments_Model apntments_model = snapshot.getValue(Apntments_Model.class);
                        apntments_List.add(apntments_model);
                    }

                    adapter.notifyDataSetChanged();
                }
                else {
                    apntments_List.clear();
                    adapter.notifyDataSetChanged();
                    Toast.makeText(Doc_apntments.this, "No Completed Appoinment ", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                adapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(this,Doc_main.class);
        startActivity(intent);
    }
}


