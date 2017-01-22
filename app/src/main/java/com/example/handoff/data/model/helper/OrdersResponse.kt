package com.example.handoff.data.model.helper

import com.example.handoff.data.model.Order
import com.google.gson.annotations.SerializedName

data class OrdersResponse(
        @SerializedName("data")
        val orders: List<Order>)