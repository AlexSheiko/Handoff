package com.example.handoff.api

import com.example.handoff.data.model.OrdersResponse
import retrofit2.http.GET
import retrofit2.http.Header
import rx.Observable

interface OrderService {

    @GET("api/v1/deliveries")
    fun orders(
            @Header("Authorization") auth: String
    ): Observable<OrdersResponse>
}