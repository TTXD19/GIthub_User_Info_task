package com.android.project.github_user_info_task.data.data_source.user_info

import com.android.project.github_user_info_task.data.data_class.user_info.UserInfoResp
import io.reactivex.rxjava3.core.Single

interface UserInfoDataSource {
    fun searchUserInfo(
        userName: String,
        page: Int
    ): Single<UserInfoResp>
}