package com.example.handoff.ui.map

import com.appigo.todopro.ui.base.MvpView
import com.example.handoff.data.model.Order
import rx.Observable

interface MapMvpView : MvpView {

    fun showOrders(obs: Observable<List<Order>>)

    fun showAddOrder()

    fun navigateToDetails(order: Order)

    fun navigateToSettings()
}