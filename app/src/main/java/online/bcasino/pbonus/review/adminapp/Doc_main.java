package online.bcasino.pbonus.review.adminapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Doc_main extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    DatabaseReference mref;
    TextView htname;
    String name;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_main);
        BottomNavigationView bottomnav =findViewById(R.id.btm_nav_doc);
        htname=(TextView)findViewById(R.id.htName);
        bottomnav.setOnNavigationItemSelectedListener(navlistner);
        mref= FirebaseDatabase.getInstance().getReference("Doctors");
        id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        get_Name();
    }

    public void openlogin(){
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public  void get_Name() {
        mref.child(id).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name = dataSnapshot.getValue(String.class);
                htname.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navlistner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {


                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.nav_profile:
                            KillThisActivity();
                            openprofile();
                            break;
                        case R.id.nav_doc_appointments:
                            KillThisActivity();
                            openDoc_aptmnts();
                            break;
                    }
                    return true;
                }

            };
    public void openprofile()
    {
        Intent intentdocter = new Intent(this, Profile_.class);
        startActivity(intentdocter);
    }
    public void openDoc_aptmnts()
    {
        Intent intentdocter = new Intent(this, Doc_apntments.class);
        startActivity(intentdocter);
    }
    public void KillThisActivity()
    {
        this.finish();
    }
}
