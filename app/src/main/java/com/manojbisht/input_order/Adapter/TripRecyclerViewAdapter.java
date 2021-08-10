package com.manojbisht.input_order.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.manojbisht.input_order.R;
import com.manojbisht.input_order.model.Trip;
import com.manojbisht.input_order.planTrip;

import java.util.List;

public class TripRecyclerViewAdapter extends RecyclerView.Adapter<TripRecyclerViewAdapter.ViewHolder> {


    private Context context;
    private List<Trip> tripsList;
    private String userName;

    public TripRecyclerViewAdapter(Context context, List<Trip> tripsList, String userName) {
        this.context = context;
        this.tripsList = tripsList;
        this.userName = userName;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_destination, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Trip trip = tripsList.get(position);
        holder.tripDesNumber.setText("#"+trip.getInputTripNumber().toUpperCase());
//        holder.tripDesImage.setImageResource();
        Glide.with(context).asBitmap().load(trip.getInputTripImage()).into(holder.tripDesImage);
        holder.tripDescType.setText(trip.getInputTripType().toUpperCase());
        holder.tripDescDetail.setText(trip.getInputTripDetail());
        holder.tripDescPrice.setText("$"+trip.getGetInputTripPrice());

    }

    @Override
    public int getItemCount() {
        return tripsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tripDesNumber;
        ImageView tripDesImage;
        TextView tripDescType;
        TextView tripDescDetail;
        TextView tripDescPrice;
        Button tripDescButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tripDesNumber = itemView.findViewById(R.id.destinationNumber);
            tripDesImage = itemView.findViewById(R.id.destinationImage);
            tripDescType = itemView.findViewById(R.id.destinationdetail);
            tripDescDetail = itemView.findViewById(R.id.destinationDesc);
            tripDescPrice = itemView.findViewById(R.id.destinationPrice);
            tripDescButton = itemView.findViewById(R.id.destinationBook);
            tripDescButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Intent intent = new Intent(context, planTrip.class);
            intent.putExtra("number", tripsList.get(position).getInputTripNumber());
            intent.putExtra("detail",tripsList.get(position).getInputTripDetail());
            intent.putExtra("image",tripsList.get(position).getInputTripImage());
            intent.putExtra("price",tripsList.get(position).getGetInputTripPrice());
            intent.putExtra("userName",userName);
            context.startActivity(intent);
        }
    }

}
