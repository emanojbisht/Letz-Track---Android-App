package com.manojbisht.input_order.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.manojbisht.input_order.R;
import com.manojbisht.input_order.data.TripDataBase;
import com.manojbisht.input_order.model.Order;
import com.manojbisht.input_order.model.Trip;

import java.util.List;

public class OrderRecycleView extends RecyclerView.Adapter<OrderRecycleView.ViewHolder> {

    private Context context;
    private List<Order> orderList;
    private String uName;
    public OrderRecycleView(Context context, List<Order> orderList, String uName) {
        this.context = context;
        this.orderList = orderList;
        this.uName = uName;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_details, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = orderList.get(position);
        TripDataBase db = new TripDataBase(context);
        List<Trip> tripList = db.getAllTrip();
        if(order.getOrderUserNAme().equals(uName)){
            for(Trip trip : tripList){
                if (order.getOrderNumber().equals(trip.getInputTripNumber())){
                    holder.orderType.setText(trip.getInputTripDetail());
                    Glide.with(context).load(trip.getInputTripImage()).into(holder.orderImage);

                }
            }
            holder.orderCurrentDate.setText(order.getOrdercurrentDate());
        }

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView orderImage;
        TextView orderType;
        TextView orderCurrentDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderImage = itemView.findViewById(R.id.orderImage);
            orderType = itemView.findViewById(R.id.orderType);
            orderCurrentDate = itemView.findViewById(R.id.orderCurrentDate);
        }

    }

}
