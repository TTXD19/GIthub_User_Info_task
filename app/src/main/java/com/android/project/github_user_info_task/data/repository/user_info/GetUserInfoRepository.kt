package com.android.project.github_user_info_task.data.repository.user_info

import com.android.project.github_user_info_task.data.data_class.user_info.UserInfoResp
import com.android.project.github_user_info_task.data.data_source.user_info.UserInfoDataSource
import com.android.project.github_user_info_task.data.data_source.user_info.UserInfoRemoteDataSource
import io.reactivex.rxjava3.core.Single

class GetUserInfoRepository(
    private val userInfoDataSource: UserInfoDataSource
) : IGetUserInfoRepository {
    override fun getUserInfoData(
        userName: String, page: Int
    ): Single<UserInfoResp> {
        return userInfoDataSource.searchUserInfo(
            userName = userName,
            page = page
        )
    }
}