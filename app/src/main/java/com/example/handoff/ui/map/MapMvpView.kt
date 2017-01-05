package com.appigo.todopro.ui.lists

import com.appigo.todopro.ui.base.MvpView
import com.example.handoff.data.model.Order

interface MapMvpView : MvpView {

    fun showFeed(orders: List<Order>)

    fun showAddOrder()

    fun navigateToDetails(order: Order)

    fun navigateToSettings()
}