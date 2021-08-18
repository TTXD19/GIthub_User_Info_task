package com.android.project.github_user_info_task.di.module

import com.android.project.github_user_info_task.data.repository.user_info.GetUserInfoRepository
import com.android.project.github_user_info_task.data.repository.user_info.IGetUserInfoRepository
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    single { GetUserInfoRepository(get()) } bind IGetUserInfoRepository::class
}