package com.example.handoff.data.model

data class Token(
        val token_type: String,
        val expires_in: Long,
        val access_token: String)