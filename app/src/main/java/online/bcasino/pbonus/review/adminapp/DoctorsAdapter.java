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

import java.util.List;



public class DoctorsAdapter extends RecyclerView.Adapter<DoctorsAdapter.DoctorsViewHolder> implements View.OnClickListener {

    private Context mCtx;
    private List<Doctor_Model> doctorModelList;

    public DoctorsAdapter(Context mCtx, List<Doctor_Model> doctorModelList) {
        this.mCtx = mCtx;
        this.doctorModelList = doctorModelList;
    }

    @NonNull
    @Override
    public DoctorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recylerview_design_doc, parent, false);
        return new DoctorsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorsViewHolder holder, int position) {
        Doctor_Model doctorModel = doctorModelList.get(position);
        holder.textViewName.setText(doctorModel.name);
        holder.textViewDep.setText("Department: " + doctorModel.department);
        holder.textViewAge.setText("Age: " + doctorModel.age);
        holder.textViewDoc_Id.setText("Doc ID:" + doctorModel.id);
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mCtx, Update_schedule.class);
                intent.putExtra("key",doctorModel.getId());
                mCtx.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return doctorModelList.size();
    }

    @Override
    public void onClick(View v ) {

    }


    class DoctorsViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewDep, textViewAge, textViewDoc_Id;
       CardView cardview;

        public DoctorsViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewDep = itemView.findViewById(R.id.text_dep);
            textViewAge = itemView.findViewById(R.id.text_view_age);
            textViewDoc_Id = itemView.findViewById(R.id.text_view_id);
            cardview=itemView.findViewById(R.id.cardview);
        }
    }
}
