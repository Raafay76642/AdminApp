package online.bcasino.pbonus.review.adminapp;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ViewUtils;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Edit_Doc_profile extends AppCompatActivity {

    String key;
    String slot1start,slot2start,slot3start,slot4start,slot5start,datevar;
    TextView t1,t2,t3,t4,t5,datetext;
    CheckBox slot1,slot2,slot3,slot4,slot5;
    DatabaseReference mref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__doc_profile);
        mref= FirebaseDatabase.getInstance().getReference("Doctors");
        slot1 = findViewById(R.id.slot1);
        slot2 = findViewById(R.id.slot2);
        slot3 = findViewById(R.id.slot3);
        slot4 = findViewById(R.id.slot4);
        slot5 = findViewById(R.id.slot5);
        t1 =findViewById(R.id.t1);
        t2 =findViewById(R.id.t2);
        t3 =findViewById(R.id.t3);
        t4 =findViewById(R.id.t4);
        t5 =findViewById(R.id.t5);

        datetext = findViewById(R.id.date);
        Intent intent=getIntent();
        key=intent.getStringExtra("key");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }
    public void timePicker1(View view)
    {
        if (slot1.isChecked()==true)
    {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(Edit_Doc_profile.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                slot1start = selectedHour+":"+selectedMinute;
                t1.setText(slot1start);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    }
    public void timePicker2(View view)
    {
        if (slot2.isChecked()==true)
        {
            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(Edit_Doc_profile.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    slot2start = selectedHour+":"+selectedMinute;
                    t2.setText(slot2start);
                }
            }, hour, minute, true);//Yes 24 hour time
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();
        }

    }
    public void timePicker3(View view)
    {
        if (slot3.isChecked()==true)
        {
            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(Edit_Doc_profile.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    slot3start = selectedHour+":"+selectedMinute;
                    t3.setText(slot3start);
                }
            }, hour, minute, true);//Yes 24 hour time
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();
        }


    }
    public void timePicker4(View view)
    {
        if (slot4.isChecked()==true)
        {
            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(Edit_Doc_profile.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    slot4start = selectedHour+":"+selectedMinute;
                    t4.setText(slot4start);
                }
            }, hour, minute, true);//Yes 24 hour time
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();
        }


    }


    public void timePicker5(View view)
    {
        if (slot5.isChecked()==true)
        {
            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(Edit_Doc_profile.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    slot5start = selectedHour+":"+selectedMinute;
                    t5.setText(slot5start);
                }
            }, hour, minute, true);//Yes 24 hour time
            mTimePicker.setTitle("Select Time");
            mTimePicker.show();
        }
    }
public void datePicker(View view)
{
    // Get Current Date
    final Calendar c = Calendar.getInstance();
    int mYear = c.get(Calendar.YEAR);
   int  mMonth = c.get(Calendar.MONTH);
   int  mDay = c.get(Calendar.DAY_OF_MONTH);

//Data dialoague Lucnher
    DatePickerDialog datePickerDialog = new DatePickerDialog(this,
            new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    datevar = dayOfMonth +":"+monthOfYear+":"+year;

                    datetext.setText(datevar);
                }
            }, mYear, mMonth, mDay);
    datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
    c.add(Calendar.DATE, +2);
    datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
    datePickerDialog.show();


    };

public void SaveDateTime(View view)
{
    if(datevar.isEmpty()) {
        Toast.makeText(Edit_Doc_profile.this,"Date Can't be Empty",Toast.LENGTH_LONG).show();
    }
    else
    {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Doctors").child(key).child(datevar);
        Doctor_Model doctor_model = new Doctor_Model(slot1start, slot2start, slot3start, slot4start, slot5start, datevar);
        if (slot1.isChecked() == true) {
            databaseReference.child("slot1").setValue(slot1start);
        }
        if (slot2.isChecked() == true) {
            databaseReference.child("slot2").setValue(slot2start);
        }
        if (slot3.isChecked() == true) {
            databaseReference.child("slot3").setValue(slot3start);
        }
        if (slot4.isChecked() == true) {
            databaseReference.child("slot4").setValue(slot4start);
        }
        if (slot5.isChecked() == true) {
            databaseReference.child("slot5").setValue(slot5start);
        }
    }
       }


}







