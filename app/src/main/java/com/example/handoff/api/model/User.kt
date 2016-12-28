package com.example.handoff.api.model

data class User(
        val name: String? = null,
        val email: String,
        val password: String,
        val password_confirmation: String = password,
        val role_id: String? = null,
        val photo: String? = null)