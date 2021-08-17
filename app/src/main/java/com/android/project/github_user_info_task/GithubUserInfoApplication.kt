package com.android.project.github_user_info_task

import android.app.Application
import com.android.project.github_user_info_task.di.module.dataSourceModule
import com.android.project.github_user_info_task.di.module.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GithubUserInfoApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GithubUserInfoApplication)
            modules(listOf(networkModule, dataSourceModule))
        }
    }
}