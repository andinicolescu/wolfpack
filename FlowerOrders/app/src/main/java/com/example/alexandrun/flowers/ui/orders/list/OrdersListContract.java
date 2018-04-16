package com.example.alexandrun.flowers.ui.orders.list;

import com.example.alexandrun.flowers.models.Order;
import com.example.alexandrun.flowers.ui.BasePresenter;
import com.example.alexandrun.flowers.ui.BaseView;

import java.util.List;

/**
 * Created by alexandrun on 4/13/2018.
 */

public interface OrdersListContract {

    interface View extends BaseView<Presenter> {
        void showMessage(String text);
        void displayOrders(List<Order> orders);
        void setRefreshing(boolean refreshing);
    }

    interface Presenter extends BasePresenter<View> {
        void getOrders();
    }
}
