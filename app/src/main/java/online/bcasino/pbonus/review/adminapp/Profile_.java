package online.bcasino.pbonus.review.adminapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.zip.Inflater;

public class Profile_ extends AppCompatActivity {
EditText name,email,department,fee,age;
    Spinner country,gender;
    Button update,edit;
    ImageView pimage;
FirebaseAuth firebaseAuth;
DatabaseReference mref;
String id;
    private StorageTask mUploadTask;
    private Uri mImageUri;
    final static int Gallery_Pick = 1;
    private ProgressDialog mProgressBarsaving;
    private StorageReference mStorageRef;
    String ProfilePic;
    TextView test;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);
        mref= FirebaseDatabase.getInstance().getReference("Doctors");
        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        department=(EditText)findViewById(R.id.department);
        country=findViewById(R.id.country);
        fee=findViewById(R.id.fee);
        gender=findViewById(R.id.gender);
        age=findViewById(R.id.age);
        pimage=findViewById(R.id.P_image);
        update=findViewById(R.id.update);
        edit=findViewById(R.id.edit);
        test=findViewById(R.id.test);
        id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mStorageRef = FirebaseStorage.getInstance().getReference().child("Profile Images");
        mProgressBarsaving = new ProgressDialog(this);
        get_data();
        load_image();
        uneditable();

    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,Doc_main.class);
        startActivity(intent);
        super.onBackPressed();
    }
    public void uneditable(){
        email.setEnabled(false);
        name.setEnabled(false);
        department.setEnabled(false);
        country.setEnabled(false);
        fee.setEnabled(false);
        age.setEnabled(false);
        gender.setEnabled(false);
        update.setEnabled(false);
        pimage.setClickable(false);

    }
    public void editable(View view){
        name.setEnabled(true);
        country.setEnabled(true);
        fee.setEnabled(true);
        update.setEnabled(true);
        pimage.setClickable(true);
    }
    public void get_data(){
        id=firebaseAuth.getInstance().getCurrentUser().getUid();
        mref.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                        Doctor_Model doctorModel = dataSnapshot.getValue(Doctor_Model.class);
                        name.setText(doctorModel.name);
                        department.setText(doctorModel.department);
                        email.setText(doctorModel.email);
                        fee.setText(doctorModel.fee);
                        age.setText(doctorModel.age);
                        String x=doctorModel.country;
                        switch (x){
                            case "Pakistan":
                            {
                            country.setSelection(0);
                                break;
                            }
                            case "India":
                            {
                                country.setSelection(1);
                                break;
                            }case "UAE":
                            {
                                country.setSelection(2);
                                break;
                            }
                        }
                    String y=doctorModel.gender;
                    switch (y){
                        case "Male":
                        {
                            gender.setSelection(0);
                            break;
                        }
                        case "Female":
                        {
                            gender.setSelection(1);
                            break;
                        }case "Other":
                        {
                            gender.setSelection(2);
                            break;
                        }
                    }
                     }
                else {
                    Toast.makeText(Profile_.this, "Complete Your Profile please", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void load_image(){
        mref.child(id).addValueEventListener(new ValueEventListener() {
            @Override


            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String value = dataSnapshot.child("profilePic").getValue(String.class);
                    Glide.with(Profile_.this).load(value).into(pimage);
                }
                else {
                    Toast.makeText(Profile_.this, "Complete your profile", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                name.setText("Error");
            }

        });
    }
    public void update(View view){

        mProgressBarsaving.setMessage("Saving. . .!");
        mProgressBarsaving.show();
        if (mImageUri != null) {
            StorageReference fileReference = mStorageRef.child(id);

            fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {


                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                    String Name=name.getText().toString();
                    String Country=country.getSelectedItem().toString();
                    String Fee=fee.getText().toString();
                    fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            ProfilePic=uri.toString();
                            mref.child(id).child("name").setValue(Name);
                            mref.child(id).child("profilePic").setValue(ProfilePic);
                            mref.child(id).child("country").setValue(Country);
                            mref.child(id).child("fee").setValue(Fee);
                        }
                    });

//                    Doctor_Model doctorModel =new Doctor_Model(Name,Country,ProfilePic,Fee);

                    mProgressBarsaving.cancel();
                    final Toast toast = Toast.makeText(Profile_.this, "Data is Saved", Toast.LENGTH_LONG);
                    toast.show();

                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Profile_.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }



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

            Glide.with(this).load(mImageUri).into(pimage);
        }

    }

}
