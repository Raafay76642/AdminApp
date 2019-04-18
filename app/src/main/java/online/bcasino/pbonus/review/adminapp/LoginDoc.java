package online.bcasino.pbonus.review.adminapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LoginDoc extends Fragment {
    EditText doc_em,doc_pass;
    Button bdoc_login;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_login_doc, container, false);
        Button btn=(Button)view.findViewById(R.id.blogindoc);
        firebaseAuth = FirebaseAuth.getInstance();
        mref= FirebaseDatabase.getInstance().getReference("Doctors");
        progressDialog = new ProgressDialog(getActivity());
        doc_em=(EditText)view.findViewById(R.id.doc_em);
        doc_pass=(EditText)view.findViewById(R.id.doc_pass);
        bdoc_login=(Button)view.findViewById(R.id.blogindoc);
        bdoc_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docLogin();
            }
        });
        return view;
    }
    public void docLogin()
    {
        String Email = doc_em.getText().toString().trim();
        String Pass = doc_pass.getText().toString().trim();
        if( TextUtils.isEmpty(Email))
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
                   String id=firebaseAuth.getCurrentUser().getUid();
                   Intent intent=new Intent(getActivity(),Doc_main.class);
                   startActivity(intent);
                   getActivity().finish();
                }
                else {
                    Toast.makeText(getActivity(),"Wrong Credentials",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
