package online.bcasino.pbonus.review.adminapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class Add_Doctor extends AppCompatActivity {

    EditText dname,demail,dpass,dage,drepass,d_fee;
    Spinner ddep,dgender,dcountry;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase mdatabase;
    private DatabaseReference mref;
    private ProgressDialog mProgressBarsaving;
    Button bdadd;
    ImageView ProfileImage;
    private StorageReference mStorageRef;
    private StorageTask mUploadTask;
    private Uri mImageUri;
    final static int Gallery_Pick = 1;
    String id;
    String ProfilePic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__doctor);
        dname=(EditText)findViewById(R.id.d_name);
        demail=(EditText)findViewById(R.id.d_email);
        dpass=(EditText)findViewById(R.id.d_password);
        dage=(EditText)findViewById(R.id.d_age);
        bdadd=(Button) findViewById(R.id.b_d_add);
        bdadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });
        ddep=(Spinner) findViewById(R.id.d_dep);
        d_fee=(EditText)findViewById(R.id.dfee);
        ProfileImage=findViewById(R.id.profile_image);
        dgender=(Spinner) findViewById(R.id.d_gender);
        drepass=(EditText)findViewById(R.id.d_repassword);
        dcountry=(Spinner) findViewById(R.id.d_country);firebaseAuth = FirebaseAuth.getInstance();
        mdatabase=FirebaseDatabase.getInstance();
        mref=mdatabase.getReference().child("Doctors");
        progressDialog = new ProgressDialog(this);
        mStorageRef = FirebaseStorage.getInstance().getReference().child("Profile Images");
        mProgressBarsaving = new ProgressDialog(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();// optional depending on your needs
        Intent intent=new Intent(this,Admin_main.class);
        startActivity(intent);
    }
    public void open_gallery(View view)
    {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, Gallery_Pick);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Gallery_Pick && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Glide.with(this).load(mImageUri).into(ProfileImage);
        }
    }
    public void uploadFile() {
        registerDoc();
        mProgressBarsaving.setMessage("Saving. . .!");
        mProgressBarsaving.show();
        if (mImageUri != null) {
            StorageReference fileReference = mStorageRef.child(id);

            fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {


                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                    String email = demail.getText().toString().trim();
                    String pass = dpass.getText().toString();
                    String repass = drepass.getText().toString();
                    String Name = "Dr. "+dname.getText().toString();
                    String Gender = dgender.getSelectedItem().toString();
                    String Country = dcountry.getSelectedItem().toString();
                    String Age=dage.getText().toString();
                    String dep=ddep.getSelectedItem().toString();
                    String fee=d_fee.getText().toString();
                    String role="Doctor";
                    fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            ProfilePic=uri.toString();
                        }
                    });
                    Doctor_Model doctorModel =new Doctor_Model(Name,Gender,Country,Age,id,email,dep,fee,role,ProfilePic);
                            mref.child(id).setValue(doctorModel);
                    mProgressBarsaving.cancel();
                    final Toast toast = Toast.makeText(Add_Doctor.this, "Data is Saved", Toast.LENGTH_LONG);
                    toast.show();

                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Add_Doctor.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }


    public void registerDoc()
    {
        String email = demail.getText().toString().trim();
        String pass = dpass.getText().toString();
        String repass = drepass.getText().toString();
        String Name = "Dr. "+dname.getText().toString();
        String Gender = dgender.getSelectedItem().toString();
        String Country = dcountry.getSelectedItem().toString();
        String Age=dage.getText().toString();
        String dep=ddep.getSelectedItem().toString();
        String fee=d_fee.getText().toString();
        String role="Doctor";
        if( TextUtils.isEmpty(Name))
        {
            //email is empty
            Toast.makeText(this,"Name Can't be left Blank",Toast.LENGTH_LONG).show();
            return ;

        }
        if(TextUtils.isEmpty(Gender))
        {
            //email is empty
            Toast.makeText(this,"Please choose your gender",Toast.LENGTH_LONG).show();
            return ;

        }

        if(TextUtils.isEmpty(email))
        {
            //email is empty
            Toast.makeText(this,"Email Can't be left Blank",Toast.LENGTH_LONG).show();
            return ;

        }
        if(TextUtils.isEmpty(pass))
        {
            //email is empty
            Toast.makeText(this,"Enter the Password",Toast.LENGTH_LONG).show();
            return ;

        }
        if (!pass.equals(repass))
        {
            Toast.makeText(this,"Password Does Not Match",Toast.LENGTH_LONG).show();
            return ;

        }
        if (TextUtils.isEmpty(dep))
    {
        Toast.makeText(this,"Please choose a Department ",Toast.LENGTH_LONG).show();
        return ;

    }
        if (TextUtils.isEmpty(fee))
        {
            Toast.makeText(this,"Please enter fee ",Toast.LENGTH_LONG).show();
            return ;

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
//                            Doctor_Model doctorModel =new Doctor_Model(Name,Gender,Country,Age,id,email,dep,fee,role);
//                            mref.child(id).setValue(doctorModel);
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
