package com.android.project.github_user_info_task.data.data_source.user_info

import com.android.project.github_user_info_task.data.data_class.user_info.UserInfoResp
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class UserInfoRemoteDataSource(
    private val userInfoApiService: UserInfoApiService
) : UserInfoDataSource {

    override fun searchUserInfo(userName: String, page: Int): Single<UserInfoResp> {
        return userInfoApiService.searchUserInfo(userName = userName, page = page).subscribeOn(
            Schedulers.io()
        )
    }
}