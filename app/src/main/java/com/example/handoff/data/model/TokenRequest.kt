package com.example.handoff.data.model

import com.example.handoff.util.Constants.PUBLIC_CLIENT
import com.example.handoff.util.Constants.PUBLIC_SECRET
import com.example.handoff.util.Constants.USER_GRANT

data class TokenRequest(
        val client_id: String = PUBLIC_CLIENT,
        val client_secret: String = PUBLIC_SECRET,
        val grant_type: String = USER_GRANT,
        val username: String? = null,
        val password: String? = null,
        val scope: String? = null)