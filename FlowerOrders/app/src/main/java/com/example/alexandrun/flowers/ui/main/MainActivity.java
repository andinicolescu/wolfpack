package com.example.alexandrun.flowers.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.alexandrun.flowers.FlowersApplication;
import com.example.alexandrun.flowers.R;
import com.example.alexandrun.flowers.ui.orders.list.OrdersListContract;
import com.example.alexandrun.flowers.ui.orders.list.OrdersListFragment;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    OrdersListContract.Presenter presenter;

    OrdersListFragment ordersListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((FlowersApplication) getApplication()).getAppComponent().inject(this);

        ordersListFragment = new OrdersListFragment();
        getFragmentManager().beginTransaction().replace(R.id.act_main_flamelayout_container, ordersListFragment).commit();
        presenter.takeView(ordersListFragment);
        ordersListFragment.setPresenter(presenter);

    }


}
