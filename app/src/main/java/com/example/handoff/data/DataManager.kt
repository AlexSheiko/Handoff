package com.example.handoff.data

import com.example.handoff.api.ServiceGenerator.orderService
import com.example.handoff.data.model.Order
import rx.Observable
import rx.android.schedulers.AndroidSchedulers.mainThread
import rx.schedulers.Schedulers.io

class DataManager {

    fun getOrders(): Observable<List<Order>> {

        val ordersApi = getOrdersFromApi()
        // TODO: Get orders from database
        // TODO: Concat API and DB observables

        return ordersApi
                .observeOn(mainThread())
    }

    private fun getOrdersFromApi(): Observable<List<Order>> {
        return orderService.orders()
                .subscribeOn(io())
    }
}