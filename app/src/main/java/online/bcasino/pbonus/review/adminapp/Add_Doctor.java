package online.bcasino.pbonus.review.adminapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_Doctor extends AppCompatActivity {

    EditText dname,demail,dpass,dage,drepass;
    Spinner ddep,dgender,dcountry;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase mdatabase;
    private DatabaseReference mref;
    Button bdadd;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__doctor);
        dname=(EditText)findViewById(R.id.d_name);
        demail=(EditText)findViewById(R.id.d_email);
        dpass=(EditText)findViewById(R.id.d_password);
        dage=(EditText)findViewById(R.id.d_age);
        bdadd=(Button) findViewById(R.id.b_d_add);
        ddep=(Spinner) findViewById(R.id.d_dep);
        dgender=(Spinner) findViewById(R.id.d_gender);
        drepass=(EditText)findViewById(R.id.d_repassword);
        dcountry=(Spinner) findViewById(R.id.d_country);firebaseAuth = FirebaseAuth.getInstance();
        mdatabase=FirebaseDatabase.getInstance();
        mref=mdatabase.getReference().child("Doctors");
        progressDialog = new ProgressDialog(this);

    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();// optional depending on your needs
        Intent intent=new Intent(this,Admin_main.class);
        startActivity(intent);
    }

    public void registerDoc(View view)
    {
        String email = demail.getText().toString().trim();
        String pass = dpass.getText().toString();
        String repass = drepass.getText().toString();
        String Name = "Dr. "+dname.getText().toString();
        String Gender = dgender.getSelectedItem().toString();
        String Country = dcountry.getSelectedItem().toString();
        String Age=dage.getText().toString();
        String dep=ddep.getSelectedItem().toString();
        if( TextUtils.isEmpty(Name))
        {
            //email is empty
            Toast.makeText(this,"Name Can't be left Blank",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(Gender))
        {
            //email is empty
            Toast.makeText(this,"Please choose your gender",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(email))
        {
            //email is empty
            Toast.makeText(this,"Email Can't be left Blank",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(pass))
        {
            //email is empty
            Toast.makeText(this,"Enter the Password",Toast.LENGTH_LONG).show();
            return;
        }
        if (!pass.equals(repass))
        {
            Toast.makeText(this,"Password Does Not Match",Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(dep))
        {
            Toast.makeText(this,"Please choose a Department ",Toast.LENGTH_LONG).show();
            return;
        }
        //both the edit text are not empty
        progressDialog.setMessage("Registring Doctor...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            id=FirebaseAuth.getInstance().getCurrentUser().getUid();

                            //display some message here
                            ProfileModel profileModel =new ProfileModel(Name,Gender,Country,Age,id,email,dep);
                            mref.child(id).setValue(profileModel);
                            Toast.makeText(Add_Doctor.this,"Successfully registered",Toast.LENGTH_LONG).show();



                        }else{
                            //display some message here
                            Toast.makeText(Add_Doctor.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();



                    }
                });


    }


}
