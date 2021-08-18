package com.android.project.github_user_info_task.di.module

import com.android.project.github_user_info_task.data.data_source.user_info.UserInfoApiService
import com.android.project.github_user_info_task.data.data_source.user_info.UserInfoDataSource
import com.android.project.github_user_info_task.data.data_source.user_info.UserInfoRemoteDataSource
import org.koin.dsl.bind
import org.koin.dsl.module

val dataSourceModule = module {
    single { provideUserInfoRemoteDataSource(get()) } bind UserInfoDataSource::class
}

fun provideUserInfoRemoteDataSource(
    userInfoApiService: UserInfoApiService
): UserInfoRemoteDataSource{
    return UserInfoRemoteDataSource(userInfoApiService)
}