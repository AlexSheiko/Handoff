package com.example.handoff.api

import com.example.handoff.api.model.User
import okhttp3.ResponseBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import rx.Observable

interface UserService {

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