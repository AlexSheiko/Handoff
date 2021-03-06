package com.example.handoff.api

import com.example.handoff.util.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object ServiceGenerator {

    val authService = createService(AuthService::class.java)
    val userService = createService(UserService::class.java)
    val orderService = createService(OrderService::class.java)

    fun <T> createService(clazz: Class<T>): T {
        val restAdapter = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(loggingClient())
                .build()
        return restAdapter.create(clazz)
    }

    private fun loggingClient(): OkHttpClient? {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        return client
    }
}