package com.example.handoff.api

import com.example.handoff.data.model.Token
import com.example.handoff.data.model.TokenRequest
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface AuthService {

    @POST("oauth/token")
    fun getToken(@Body request: TokenRequest): Observable<Token>
}