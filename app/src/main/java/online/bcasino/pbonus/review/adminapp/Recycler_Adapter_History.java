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

public class Recycler_Adapter_History extends RecyclerView.Adapter {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_histry, viewGroup, false);
        return new Recycler_Adapter_History.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((Recycler_Adapter_History.ListViewHolder) viewHolder).bindView(i);
    }

    @Override
    public int getItemCount() {
        return Our_Data_histry.patient_name2.length;
    }

    private class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView user_name, descrption;
        private ImageView user_img;

        public ListViewHolder(View itemview) {
            super(itemview);
            user_name = (TextView) itemview.findViewById(R.id.user_name2);
            descrption = (TextView) itemview.findViewById(R.id.description2);
            user_img = (ImageView) itemview.findViewById(R.id.user_image2);


        }

        public void bindView(int position) {
            user_name.setText(Our_Data_histry.patient_name2[position]);
            descrption.setText(Our_Data_histry.description2[position]);
            user_img.setImageResource(Our_Data_histry.pictures2[position]);

        }
    }
}