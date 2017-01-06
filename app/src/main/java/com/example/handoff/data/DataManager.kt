package com.example.handoff.data

import com.example.handoff.api.ServiceGenerator.orderService
import com.example.handoff.data.model.Order
import com.example.handoff.data.model.Token
import com.example.handoff.util.Extensions
import rx.Observable
import rx.android.schedulers.AndroidSchedulers.mainThread
import rx.schedulers.Schedulers.io

class DataManager : Extensions {

    fun getOrders(token: Token): Observable<List<Order>> {

        val ordersApi = getOrdersFromApi(token)
        // TODO: Get orders from database
        // TODO: Concat API and DB observables

        return ordersApi
                .observeOn(mainThread())
    }

    private fun getOrdersFromApi(token: Token): Observable<List<Order>> {
        return orderService.orders(bearer(token))
                .subscribeOn(io())
    }
}