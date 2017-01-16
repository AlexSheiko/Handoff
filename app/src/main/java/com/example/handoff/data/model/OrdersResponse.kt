package com.example.handoff.data.model

import com.google.gson.annotations.SerializedName

data class OrdersResponse(
        @SerializedName("data")
        val orders: List<Order>)