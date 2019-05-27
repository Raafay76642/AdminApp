package online.bcasino.pbonus.review.adminapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Admin_main extends AppCompatActivity implements View.OnClickListener {
Button logout_admin;
FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminmain);
        logout_admin=(Button) findViewById(R.id.logout_admin);
        logout_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log_out();

            }
        });

    }

    @Override
    public void onClick(View v) {
        open_addDoc(v);
    }


    public void open_addDoc(View view) {
        Intent intent = new Intent(this, Signup_DOC.class);
        startActivity(intent);

    }

    public void open_editDoc(View view) {
        Intent intent = new Intent(this, Admin_select_Doc.class);
        startActivity(intent);

    }
    public void log_out(){
        firebaseAuth.signOut();
        finish();
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}