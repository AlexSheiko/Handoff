package com.example.handoff.ui.map

import com.appigo.todopro.ui.base.BasePresenter
import com.example.handoff.data.model.Order

class MapPresenter : BasePresenter<MapMvpView>() {

    fun initView() {
        checkViewAttached()
    }

    fun loadFeed() {
        // TODO: Get from repository
        // val feed = mDataManager.feed
        // mvpView?.showFeed(feed)
    }

    fun onOrderClicked(order: Order) {
        mvpView?.navigateToDetails(order)
    }

    fun createOrder() {
        mvpView?.showAddOrder()
    }
}