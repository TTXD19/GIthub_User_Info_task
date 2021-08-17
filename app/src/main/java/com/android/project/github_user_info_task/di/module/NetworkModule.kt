package com.android.project.github_user_info_task.di.module

import com.android.project.github_user_info_task.BuildConfig
import com.android.project.github_user_info_task.data.data_source.user_info.UserInfoApiService
import com.android.project.github_user_info_task.di.module.NetworkModule.provideHttpClient
import com.android.project.github_user_info_task.di.module.NetworkModule.provideHttpLoggingInterceptor
import com.android.project.github_user_info_task.di.module.NetworkModule.provideRetrofit
import com.android.project.github_user_info_task.di.module.ApiServiceModule.provideUserInfoService
import com.android.project.github_user_info_task.di.module.NetworkModule.provideNetworkManager
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    factory { provideHttpLoggingInterceptor() }
    factory { provideHttpClient(get()) }
    factory { provideRetrofit(get()) }
    single { provideNetworkManager(get()) }
}

val dataSourceModule = module {
    single { provideUserInfoService(get()) }
}

object NetworkModule{
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return interceptor
    }

    fun provideHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.GIT_BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideNetworkManager(
        retrofit: Retrofit
    ): NetworkManger{
        return NetworkManger(retrofit)
    }
}

object ApiServiceModule {
    fun provideUserInfoService(
        networkManger: NetworkManger
    ): UserInfoApiService{
        return networkManger.getApiService(UserInfoApiService::class.java)
    }
}