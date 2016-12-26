package com.example.handoff.api

import com.example.handoff.api.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerator {
    companion object {

        fun createAuthService(): AuthService {
            return createService(AuthService::class.java)
        }

        fun createUserService(): UserService {
            return createService(UserService::class.java)
        }

        fun <T> createService(clazz: Class<T>): T {
            val restAdapter = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build()
            val service = restAdapter.create(clazz)

            return service
        }
    }
}