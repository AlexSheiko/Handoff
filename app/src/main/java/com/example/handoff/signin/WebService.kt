package com.example.handoff.signin

import retrofit2.Call
import retrofit2.http.GET

interface WebService {

    @GET("/users")
    fun users(): Call<List<User>>
}