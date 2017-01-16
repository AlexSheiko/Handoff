package com.example.handoff.data.model

import com.google.gson.annotations.SerializedName

data class Order(
        @SerializedName("user_id")
        val user: User,
        @SerializedName("destination_id")
        val destination: Destination)