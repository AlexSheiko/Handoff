package com.example.handoff.api.model

import com.example.handoff.api.Constants.CLIENT_AUTH
import com.example.handoff.api.Constants.GRANT_AUTH
import com.example.handoff.api.Constants.SECRET_AUTH

data class TokenRequest(
        val client_id: String = CLIENT_AUTH,
        val client_secret: String = SECRET_AUTH,
        val grant_type: String = GRANT_AUTH,
        val username: String? = null,
        val password: String? = null,
        val scope: String? = null)