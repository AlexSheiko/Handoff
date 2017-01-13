package com.example.handoff.data.model

import com.example.handoff.util.Constants.CLIENT_PUBLIC
import com.example.handoff.util.Constants.GRANT_USER
import com.example.handoff.util.Constants.SECRET_PUBLIC

data class TokenRequest(
        val client_id: String = CLIENT_PUBLIC,
        val client_secret: String = SECRET_PUBLIC,
        val grant_type: String = GRANT_USER,
        val username: String? = null,
        val password: String? = null,
        val scope: String? = null)