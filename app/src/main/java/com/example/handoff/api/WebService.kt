package com.example.handoff.api

import com.example.handoff.api.model.Token
import com.example.handoff.api.model.TokenRequest
import com.example.handoff.api.model.User
import okhttp3.ResponseBody
import retrofit2.http.*
import rx.Observable

interface WebService {

    @FormUrlEncoded
    @POST("oauth/token")
    fun getToken(@Body request: TokenRequest): Observable<Token>

    @FormUrlEncoded
    @POST("api/v1/users")
    fun createUser(@Field("name") name: String,
                   @Field("email") email: String,
                   @Field("password") password: String,
                   @Field("password_confirmation") passConfirm: String
    ): Observable<ResponseBody>

    @GET("api/v1/users")
    fun users(): Observable<List<User>>
}