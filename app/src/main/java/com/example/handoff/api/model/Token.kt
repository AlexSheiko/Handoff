package com.example.handoff.api.model

data class Token(
        val token_type: String,
        val expires_in: Long,
        val access_token: String)