package com.manojbisht.input_order.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.manojbisht.input_order.R;
import com.manojbisht.input_order.explore.Culture;
import com.manojbisht.input_order.explore.adventure;
import com.manojbisht.input_order.explore.peaks;
import com.manojbisht.input_order.explore.tour;
import com.manojbisht.input_order.explore.villages;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mNAmes = new ArrayList<>();
    private ArrayList<String> mImagesUrls = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context mContext, ArrayList<String> mNAmes, ArrayList<String> mImagesUrls) {
        this.mNAmes = mNAmes;
        this.mImagesUrls = mImagesUrls;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: ");
        Glide.with(mContext)
                .asBitmap()
                .load(mImagesUrls.get(position))
                .into(holder.image);

        holder.name.setText(mNAmes.get(position));
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                final Intent intent;
                switch (position){
                    case 0:
                        intent = new Intent(mContext, peaks.class);
                        mContext.startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(mContext, villages.class);
                        mContext.startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(mContext, Culture.class);
                        mContext.startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(mContext, adventure.class);
                        mContext.startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(mContext, tour.class);
                        mContext.startActivity(intent);
                        break;
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mNAmes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
        }
    }



}
