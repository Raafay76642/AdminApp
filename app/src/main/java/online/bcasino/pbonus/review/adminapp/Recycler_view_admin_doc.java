package online.bcasino.pbonus.review.adminapp;

import android.content.Intent;
import android.content.res.Resources;
import android.nfc.Tag;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Recycler_view_admin_doc extends AppCompatActivity
{

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase mdatabase;
    private DatabaseReference mref;
    private RecyclerView recyclerView;
    private DoctorsAdapter adapter;
    private List<Doctor_Model> doctorModelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_admin_doc);
        firebaseAuth=FirebaseAuth.getInstance();
        mref=FirebaseDatabase.getInstance().getReference("Doctors");
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        doctorModelList = new ArrayList<>();
        adapter = new DoctorsAdapter(this, doctorModelList);
        recyclerView.setAdapter(adapter);
        Intent intent=getIntent();
        String selected=intent.getStringExtra("selected");
        Query query = FirebaseDatabase.getInstance().getReference("Doctors")
                .orderByChild("department")
                .equalTo(selected);
        query.addValueEventListener(valueEventListener);


}
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            doctorModelList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Doctor_Model doctorModel = snapshot.getValue(Doctor_Model.class);
                    doctorModelList.add(doctorModel);
                }
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
