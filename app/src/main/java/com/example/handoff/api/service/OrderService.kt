package com.example.handoff.api.service

import com.example.handoff.api.model.Order
import retrofit2.http.GET
import rx.Observable

interface OrderService {

    @GET("api/v1/deliveries")
    fun orders(): Observable<List<Order>>
}