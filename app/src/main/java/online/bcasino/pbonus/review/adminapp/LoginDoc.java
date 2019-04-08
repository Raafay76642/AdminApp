package online.bcasino.pbonus.review.adminapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class LoginDoc extends Fragment {
    EditText doc_em,doc_pass;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_login_doc, container, false);
        Button btn=(Button)view.findViewById(R.id.blogindoc);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), Doc_main.class);
                ((MainActivity) getActivity()).startActivity(intent);
            }
        });
        // Inflate the layout_booked for this fragment
        return view;
    }

}
