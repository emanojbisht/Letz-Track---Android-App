package com.manojbisht.input_order;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.manojbisht.input_order.Adapter.OrderRecycleView;
import com.manojbisht.input_order.data.OrderDataBase;
import com.manojbisht.input_order.model.Order;

import java.util.List;

public class myTrip extends AppCompatActivity {
    private RecyclerView orderRecyclerview1;
    private OrderRecycleView orderRecyclerAdapter;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trip);

        userName = getIntent().getExtras().getString("userName");


        OrderDataBase db = new OrderDataBase(this);
        List<Order> orderList = db.getAllOrder();

        //orderRecyclerview1
        orderRecyclerview1 = findViewById(R.id.orderRecyclerview1);
        orderRecyclerview1.setHasFixedSize(true);
        orderRecyclerview1.setLayoutManager(new LinearLayoutManager(this));
        orderRecyclerAdapter = new OrderRecycleView(this, orderList,userName);
        orderRecyclerview1.setAdapter(orderRecyclerAdapter);
    }
}
