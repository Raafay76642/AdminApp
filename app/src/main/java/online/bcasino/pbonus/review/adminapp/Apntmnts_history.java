package online.bcasino.pbonus.review.adminapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Apntmnts_history extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout_booked for this fragment
        View view = inflater.inflate(R.layout.fragment_apntmnts_history, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rec_lst_hstry);
        Recycler_Adapter_History recycler_adapter_history = new Recycler_Adapter_History();
        recyclerView.setAdapter(recycler_adapter_history);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;


    }
    }
