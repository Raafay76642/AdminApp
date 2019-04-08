package online.bcasino.pbonus.review.adminapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;
import java.util.concurrent.Executor;


public class LoginUser extends Fragment {
    Button buser_login;
    FirebaseAuth firebaseAuth;
    DatabaseReference mRef;
    EditText aemail,apass;
    ProgressDialog progressDialog;
    String id;
    String role;
    Boolean flag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout_booked for this fragment
        View view=inflater.inflate(R.layout.fragment_login_user, container, false);
        firebaseAuth=FirebaseAuth.getInstance();
        mRef= FirebaseDatabase.getInstance().getReference("Admin");
        buser_login=(Button) view.findViewById(R.id.buser_login);
        progressDialog = new ProgressDialog(getActivity());
        aemail=(EditText)view.findViewById(R.id.a_mail);
        apass=(EditText)view.findViewById(R.id.a_pass);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        buser_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();

            }
        });

    }

    public void open_adminMain(){
        Intent intent=new Intent(getActivity(),Admin_main.class);
        startActivity(intent);
    }
    public void userLogin()
    {
        String Email = aemail.getText().toString().trim();
        String Pass = apass.getText().toString().trim();
        if(TextUtils.isEmpty(Email))
        {
            //email is empty
            Toast.makeText(getActivity(),"Email Can't be left Blank",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(Pass))
        {
            //email is empty
            Toast.makeText(getActivity(),"Enter the Password",Toast.LENGTH_LONG).show();
            return;
        }
        //both the edit text are not empty
        progressDialog.setMessage("logging in ...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(Email,Pass).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    get_role();
                }
                    else {
                     Toast.makeText(getActivity(),"Again",Toast.LENGTH_LONG).show();
                 }
            }
        });
}
public void log_out(){
        firebaseAuth.signOut();
        getActivity().finish();

    }


public void get_role(){
    id=FirebaseAuth.getInstance().getCurrentUser().getUid();
    mRef.child(id).child("role").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                role=dataSnapshot.getValue(String.class);
                if ( role.equals("admin") ){
                    flag=true;
                    open_adminMain();
                }
                else{
                    Toast.makeText(getActivity(),"Wrong Credentials",Toast.LENGTH_LONG).show();

                    log_out();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

}
}
