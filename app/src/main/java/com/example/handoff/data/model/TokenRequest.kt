package com.example.handoff.data.model

import com.example.handoff.util.Constants.PUBLIC_CLIENT
import com.example.handoff.util.Constants.PUBLIC_GRANT
import com.example.handoff.util.Constants.PUBLIC_SECRET

data class TokenRequest(
        val client_id: String = PUBLIC_CLIENT,
        val client_secret: String = PUBLIC_SECRET,
        val grant_type: String = PUBLIC_GRANT,
        val username: String? = null,
        val password: String? = null,
        val scope: String? = null)