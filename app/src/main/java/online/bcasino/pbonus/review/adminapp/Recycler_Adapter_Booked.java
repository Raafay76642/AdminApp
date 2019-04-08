package online.bcasino.pbonus.review.adminapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class Recycler_Adapter_Booked extends RecyclerView.Adapter {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_booked, viewGroup,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ListViewHolder)viewHolder).bindView(i);
    }

    @Override
    public int getItemCount() {
        return Ourdata_Booked.patient_name1.length;
    }
    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView user_name,descrption;
        private ImageView user_img;
        private Button btn_call;
        public ListViewHolder(View itemview){
            super(itemview);
            user_name=(TextView)itemview.findViewById(R.id.user_name1);
            descrption=(TextView)itemview.findViewById(R.id.description1);
            user_img=(ImageView)itemview.findViewById(R.id.user_image1);
            btn_call=(Button)itemview.findViewById(R.id.btn_call);


        }
        public void bindView(int position){
            user_name.setText(Ourdata_Booked.patient_name1[position]);
            descrption.setText(Ourdata_Booked.description1[position]);
            user_img.setImageResource(Ourdata_Booked.pictures1[position]);
            btn_call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.getContext().startActivity(new Intent(v.getContext(), Calling.class));

                }
            });

        }
        @Override
        public void onClick(View v) {

        }
    }
}
