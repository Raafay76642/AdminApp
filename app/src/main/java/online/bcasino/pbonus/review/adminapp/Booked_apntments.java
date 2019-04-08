package online.bcasino.pbonus.review.adminapp;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Booked_apntments extends Fragment {
    Button btn_call;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booked_apntments, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rec_lst_bkd);
        Recycler_Adapter_Booked recycler_adapterBooked = new Recycler_Adapter_Booked();
        recyclerView.setAdapter(recycler_adapterBooked);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }

}