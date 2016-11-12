package com.example.handoff.signin

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerator {

    private val builder = Retrofit.Builder()
            .baseUrl("http://test.handoff.com.hk/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

    fun createService(): WebService {
        val retrofit = builder.client(OkHttpClient()).build()
        return retrofit.create(WebService::class.java)
    }
}