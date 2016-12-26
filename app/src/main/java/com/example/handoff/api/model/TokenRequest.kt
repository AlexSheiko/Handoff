package com.example.handoff.api.model

import com.example.handoff.api.Constants.CLIENT_DEFAULT
import com.example.handoff.api.Constants.GRANT_DEFAULT
import com.example.handoff.api.Constants.SECRET_DEFAULT

data class TokenRequest(
        val client_id: String = CLIENT_DEFAULT,
        val client_secret: String = SECRET_DEFAULT,
        val grant_type: String = GRANT_DEFAULT,
        val username: String,
        val password: String,
        val scope: String)