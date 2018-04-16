package com.example.alexandrun.flowers.ui.orders.item;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.alexandrun.flowers.R;
import com.example.alexandrun.flowers.models.Order;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexandrun on 4/16/2018.
 */

public class OrderFragment extends Fragment {

    private static final String ARG_ORDER = "argOrder";

    @BindView(R.id.fr_order_item_deliver_to)
    TextView deliverToTv;

    @BindView(R.id.fr_order_item_description)
    TextView descriptionTv;

    @BindView(R.id.fr_order_item_price)
    TextView priceTv;

    @BindView(R.id.fr_order_item_imageview)
    ImageView image;

    public static OrderFragment newInstance(Order order) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_ORDER, order);

        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fr_order_item, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        displayOrder();
    }

    private void displayOrder() {
        Bundle args = getArguments();
        Order order = args.containsKey(ARG_ORDER) ? args.getParcelable(ARG_ORDER) : null;

        if (order != null) {
            descriptionTv.setText(order.getDescription());
            priceTv.setText(String.format("%d$", order.getPrice()));
            deliverToTv.setText(String.format("Deliver to: %s", order.getDeliverTo()));
            image.setImageResource(R.drawable.flowers);
        }
    }
}
