package online.bcasino.pbonus.review.adminapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Trace;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Edit_Doc_profile extends AppCompatActivity {
    private DatabaseReference mref;
    TextView name,dep,today,today1,today_start,today_end,tdate;
    String key;
    EditText today1_start,today1_end;
    private int mYear, mMonth, mDay, mHour, mMinute;
    CheckBox Cb0,Cb1;
    int today_start_hours,today_start_minuts,today_end_hours,today_end_minuts;
    long today_start_time,today_end_time;
    List<Long> array0;
    static final long ONE_M_IN_MILIS=60000;//millisecs
    Map<String , Object> map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__doc_profile);
        Intent intent=getIntent();
        key=intent.getStringExtra("key");
        mref= FirebaseDatabase.getInstance().getReference("Doctors");
        name=(TextView)findViewById(R.id.text_viewname);
        dep=(TextView)findViewById(R.id.textdep);
        today_start=(TextView) findViewById(R.id.e0_start);
        today_end=(TextView) findViewById(R.id.e0_end);
        today1_start=(EditText)findViewById(R.id.e1_start);
        Cb0=(CheckBox)findViewById(R.id.cb0);
        Cb1=(CheckBox)findViewById(R.id.cb1);
        today1_end=(EditText)findViewById(R.id.e1_end);
        map= new HashMap<>();


        tdate=(TextView)findViewById(R.id.tdate);
//        today1=(TextView)findViewById(R.id.ttoday1);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        get_data();

    }

    public void get_data() {
        //For showing the doctor profile details
        mref.child(key).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        mref.child(key).child("department").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dep.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
    });
    }
    public void today(View view){
        {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

//Data dialoague Lucnher
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            today.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            today_start.setEnabled(true);
                            today_end.setEnabled(true);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
            c.add(Calendar.DATE, +2);
            datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
            datePickerDialog.show();

        }}



}
