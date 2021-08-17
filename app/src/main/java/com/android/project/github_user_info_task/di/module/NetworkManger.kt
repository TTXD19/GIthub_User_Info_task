package com.android.project.github_user_info_task.di.module

import retrofit2.Retrofit

open class NetworkManger(
    private val retrofit: Retrofit
) {
    fun getRetrofit(): Retrofit {
        return retrofit
    }

    inline fun <reified T> getApiService(serviceClazz: Class<T>): T {
        return getRetrofit().create(serviceClazz)
    }
}