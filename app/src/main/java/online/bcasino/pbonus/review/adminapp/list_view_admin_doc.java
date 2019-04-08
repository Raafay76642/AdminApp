package online.bcasino.pbonus.review.adminapp;

import android.content.res.Resources;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class list_view_admin_doc extends AppCompatActivity
{
   String[] listItem;
    TextView textView;
    ListView doc_lst;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase mdatabase;
    private DatabaseReference mref;
    int point=1;
    ArrayList<String> myArrayList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_admin_doc);
        doc_lst=(ListView)findViewById(R.id.doc_lst);
        textView=(TextView)findViewById(R.id.textView1);
        firebaseAuth=FirebaseAuth.getInstance();
        mref=FirebaseDatabase.getInstance().getReference("Doctors");
        Global_class global_class=(Global_class)getApplicationContext();
        String category=global_class.getCateogory();

        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.listview_docs,R.id.textView1,myArrayList);

        Query query=FirebaseDatabase.getInstance().getReference("Doctors").orderByChild("department").equalTo(category);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String myValues=dataSnapshot.getValue().toString();
                myArrayList.add(myValues);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
