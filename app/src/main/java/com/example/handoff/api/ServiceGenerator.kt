package com.example.handoff.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerator {
    companion object {

        private val builder = Retrofit.Builder()
                .baseUrl("http://test.handoff.com.hk/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

        fun createService(): WebService {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.HEADERS
            val client = OkHttpClient.Builder().addInterceptor(logging).build()

            val retrofit = builder.client(client).build()
            return retrofit.create(WebService::class.java)
        }
    }
}