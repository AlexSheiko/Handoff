package com.example.handoff.ui.map

import com.appigo.todopro.ui.base.BasePresenter
import com.example.handoff.data.DataManager
import com.example.handoff.data.model.Order

class MapPresenter : BasePresenter<MapMvpView>() {

    fun loadOrders() {
        val orders = DataManager().getOrders()
        mvpView?.showOrders(orders)
    }

    fun onOrderClicked(order: Order) {
        mvpView?.navigateToDetails(order)
    }

    fun createOrder() {
        mvpView?.showAddOrder()
    }
}