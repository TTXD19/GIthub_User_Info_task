package com.android.project.github_user_info_task.di.module

import com.android.project.github_user_info_task.ui.MainActivityViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { MainActivityViewModel(get()) }
}