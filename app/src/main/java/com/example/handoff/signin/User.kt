package com.example.handoff.signin

data class User(
        val name: String,
        var email: String,
        var password: String,
        var password_confirmation: String)