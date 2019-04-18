package online.bcasino.pbonus.review.adminapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Admin_select_Doc extends AppCompatActivity implements View.OnClickListener {

    LinearLayout cardialogy,ortho,neuoro,dent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_select__doc);
        cardialogy=(LinearLayout)findViewById(R.id.cardialogy);
        ortho=(LinearLayout)findViewById(R.id.orthopedic);
        dent=(LinearLayout)findViewById(R.id.dent);
        neuoro=(LinearLayout)findViewById(R.id.neuro);
        return ;
    }
public void doc_list_card(View view){
    Global_class global_class=(Global_class)getApplicationContext();
    global_class.setSelected(1);
    global_class.setCateogory(null);
    global_class.setCateogory("Cardiologist");
    Intent intent= new Intent(this, Recycler_view_admin_doc.class);
    intent.putExtra("selected","Cardiologist");
    startActivity(intent);
}
    public void doc_list_dent(View view){
        Global_class global_class=(Global_class)getApplicationContext();
        global_class.setSelected(3);
        global_class.setCateogory(null);
        global_class.setCateogory("Dentist");
        Intent intent= new Intent(this, Recycler_view_admin_doc.class);
        intent.putExtra("selected","Dentist");
        startActivity(intent);
    }
    public void doc_list_neuro(View view){
        Global_class global_class=(Global_class)getApplicationContext();
        global_class.setSelected(2);
        global_class.setCateogory(null);
        global_class.setCateogory("Neuro Surgeon");
        Intent intent= new Intent(this, Recycler_view_admin_doc.class);
        intent.putExtra("selected","Neuro Surgeon");
        startActivity(intent);


    }
    public void doc_list_ortho(View view){
        Global_class global_class=(Global_class)getApplicationContext();
        global_class.setSelected(4);
        global_class.setCateogory(null);
        global_class.setCateogory("Orthopedist");
        Intent intent= new Intent(this, Recycler_view_admin_doc.class);
        intent.putExtra("selected","Orthopedist");
        startActivity(intent);
    }
    @Override
    public void onClick(View v) {

    }
}
