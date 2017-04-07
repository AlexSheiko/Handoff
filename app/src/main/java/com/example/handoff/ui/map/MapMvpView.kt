package com.example.handoff.ui.map

import com.example.handoff.data.model.Order
import com.example.handoff.ui.base.MvpView
import rx.Observable

interface MapMvpView : MvpView {

    fun showOrders(obs: Observable<List<Order>>)

    fun showAddOrder()

    fun navigateToDetails(order: Order)

    fun navigateToSettings()
}