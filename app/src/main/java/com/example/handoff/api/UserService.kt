package com.example.handoff.api

import com.example.handoff.api.model.Token
import com.example.handoff.api.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import rx.Observable

interface UserService {

    @POST("api/v1/users")
    fun createUser(@Body user: User,
                   @Header("Authorization") auth: String
    ): Observable<Token>

    @GET("api/v1/users")
    fun users(): Observable<List<User>>
}