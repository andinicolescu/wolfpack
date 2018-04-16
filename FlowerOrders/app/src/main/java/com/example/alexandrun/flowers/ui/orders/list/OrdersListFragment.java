package com.example.alexandrun.flowers.ui.orders.list;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alexandrun.flowers.FlowersApplication;
import com.example.alexandrun.flowers.R;
import com.example.alexandrun.flowers.models.Order;
import com.example.alexandrun.flowers.ui.OrderTransition;
import com.example.alexandrun.flowers.ui.orders.item.OrderFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexandrun on 4/13/2018.
 */

public class OrdersListFragment extends Fragment implements OrdersListContract.View {

    private OrdersListContract.Presenter presenter;

    @Inject
    OrdersAdapter ordersAdapter;

    @BindView(R.id.fr_orders_recyclerview)
    RecyclerView ordersRv;

    @BindView(R.id.fr_orders_swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fr_orders_list, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((FlowersApplication) getActivity().getApplication()).getAppComponent().inject(this);
        presenter.getOrders();
        initializeList();
    }

    private void initializeList() {
        ordersRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        ordersRv.setAdapter(ordersAdapter);
        ordersAdapter.setCallback(this::itemClicked);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.getOrders();
        });
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
    }

    private void itemClicked(OrdersAdapter.ViewHolder holder, int position) {
        OrderFragment orderFragment = OrderFragment.newInstance(ordersAdapter.getOrders().get(position));

        orderFragment.setSharedElementEnterTransition(new OrderTransition());
        orderFragment.setEnterTransition(new Fade());
        setExitTransition(new Fade());
        orderFragment.setSharedElementReturnTransition(new OrderTransition());

        getActivity().getFragmentManager()
                .beginTransaction()
                .addSharedElement(holder.imageView, "flowerImage")
                .replace(R.id.act_main_flamelayout_container, orderFragment)
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void showMessage(String text) {
        Snackbar.make(ordersRv, text, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void displayOrders(List<Order> orders) {
        ordersAdapter.setOrders(orders);
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        swipeRefreshLayout.setRefreshing(refreshing);
    }

    @Override
    public void setPresenter(OrdersListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.dropView();
    }
}
