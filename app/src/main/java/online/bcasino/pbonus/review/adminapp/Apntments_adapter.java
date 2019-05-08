package online.bcasino.pbonus.review.adminapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Apntments_adapter extends RecyclerView.Adapter<Apntments_adapter.ApntmentsViewHolder> {

    private Context mCtx;
    private List<Apntments_Model> apntmentsList;

    public Apntments_adapter(Context mCtx, List<Apntments_Model> apntmentsList) {
        this.mCtx = mCtx;
        this.apntmentsList = apntmentsList;
    }

    @NonNull
    @Override
    public ApntmentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recycler_apt_design, parent, false);
        return new ApntmentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApntmentsViewHolder holder, int position) {
        Apntments_Model apntments_model = apntmentsList.get(position);
        holder.textViewName.setText(apntments_model.uName);
        holder.textViewDate.setText(apntments_model.date);
        holder.textViewtime.setText(apntments_model.sTime);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mCtx, apntments_model.aID, Toast.LENGTH_LONG).show();
                Intent intent=new Intent(mCtx,Appoinments_details.class);
                intent.putExtra("aID",apntments_model.aID);
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return apntmentsList.size();
    }

    class ApntmentsViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewDate, textViewtime;
        CardView cardView;

        public ApntmentsViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_name1);
            textViewDate = itemView.findViewById(R.id.text_date1);
            textViewtime = itemView.findViewById(R.id.text_view_time1);
            cardView= itemView.findViewById(R.id.cardview1);
        }
    }
}