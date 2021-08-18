package com.android.project.github_user_info_task.di.module

import com.android.project.github_user_info_task.ui.user_info.UserInfoViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { UserInfoViewModel(get()) }
}