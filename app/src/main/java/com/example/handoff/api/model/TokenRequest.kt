package com.example.handoff.api.model

import com.example.handoff.api.Constants.CLIENT_COMMON
import com.example.handoff.api.Constants.GRANT_COMMON
import com.example.handoff.api.Constants.SECRET_COMMON

data class TokenRequest(
        val client_id: String = CLIENT_COMMON,
        val client_secret: String = SECRET_COMMON,
        val grant_type: String = GRANT_COMMON,
        val username: String? = null,
        val password: String? = null,
        val scope: String? = null)