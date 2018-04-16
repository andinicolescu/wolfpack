package com.example.alexandrun.flowers.ui.orders.list;

import android.annotation.SuppressLint;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexandrun.flowers.R;
import com.example.alexandrun.flowers.models.Order;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexandrun on 4/16/2018.
 */

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {

    private List<Order> orders;
    private OrderClickListener orderClickListener;

    public void setCallback(OrderClickListener orderClickListener) {
        this.orderClickListener = orderClickListener;
    }

    public void setOrders(List<Order> orders) {
        if (this.orders == null) {
            this.orders = new ArrayList<>();
        }
        this.orders.clear();
        this.orders.addAll(orders);
        notifyDataSetChanged();
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (orders != null) {
            holder.description.setText(orders.get(position).getDescription());
            holder.price.setText(String.format("%d$", orders.get(position).getPrice()));

            holder.constraintLayout.setOnClickListener(view -> {
                orderClickListener.orderClicked(holder, position);
            });

            ViewCompat.setTransitionName(holder.imageView, String.valueOf(position) + "_image");
        }
    }

    @Override
    public int getItemCount() {
        return orders != null ? orders.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.order_item_price)
        TextView price;

        @BindView(R.id.order_item_description)
        TextView description;

        @BindView(R.id.order_item_imageview)
        ImageView imageView;

        @BindView(R.id.order_item_layout)
        ConstraintLayout constraintLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
