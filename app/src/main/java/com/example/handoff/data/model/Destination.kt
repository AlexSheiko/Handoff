package com.example.handoff.data.model

data class Destination(
        val id: String,
        val user_id: Int,
        val type: Int,
        val address1: String,
        val address2: String,
        val lat: Double,
        val long: Double)