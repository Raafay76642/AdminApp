package online.bcasino.pbonus.review.adminapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Admin_main extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminmain);

    }

    @Override
    public void onClick(View v) {
        open_addDoc(v);
    }


    public void open_addDoc(View view) {
        Intent intent = new Intent(this, Add_Doctor.class);
        startActivity(intent);

    }

    public void open_editDoc(View view) {
        Intent intent = new Intent(this, Admin_select_Doc.class);
        startActivity(intent);

    }
}