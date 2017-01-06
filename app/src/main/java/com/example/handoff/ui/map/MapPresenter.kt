package com.example.handoff.ui.map

import com.appigo.todopro.ui.base.BasePresenter
import com.example.handoff.data.DataManager
import com.example.handoff.data.model.Order
import com.example.handoff.data.model.Token

class MapPresenter : BasePresenter<MapMvpView>() {

    fun loadOrders(token: Token) {
        val orders = DataManager().getOrders(token)
        mvpView?.showOrders(orders)
    }

    fun onOrderClicked(order: Order) {
        mvpView?.navigateToDetails(order)
    }

    fun createOrder() {
        mvpView?.showAddOrder()
    }
}