package online.bcasino.pbonus.review.adminapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private TabLayout tablayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    private FirebaseAuth firebaseAuth;
    DatabaseReference mref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tablayout=(TabLayout)findViewById(R.id.tablayout_id);
        appBarLayout=( AppBarLayout )findViewById(R.id.appbar_id);
        viewPager=(ViewPager)findViewById(R.id.viewpager_id);
        ViewpageAdapter adapter= new ViewpageAdapter(getSupportFragmentManager());
        //adding fragment
        adapter.Addfragment(new LoginDoc(), "Login As Doctor");
        adapter.Addfragment(new LoginUser(), "Login As Admin");
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);


    }

    @Override
    protected void onStart() {
        super.onStart();


    }
    public void check(){
        String id= firebaseAuth.getInstance().getCurrentUser().getUid();
        Query query = FirebaseDatabase.getInstance().getReference().child(id).orderByChild("role").equalTo("admin");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                   openAdmin();
                }
                else {
                    openDoc();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void openAdmin(){
        Intent intent=new Intent(this,Admin_main.class);
        startActivity(intent);
        finish();
    }
    public void openDoc(){
        Intent intent=new Intent(this,Doc_main.class);
        startActivity(intent);
        finish();
    }
}
