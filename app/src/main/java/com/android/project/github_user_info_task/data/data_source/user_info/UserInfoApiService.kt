package com.android.project.github_user_info_task.data.data_source.user_info

import com.android.project.github_user_info_task.data.data_class.user_info.UserInfoResp
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UserInfoApiService {

    @GET("$SEARCH_END_POINT/users")
    fun searchUserInfo(
        @Query("q") userName: String,
        @Query("page") page: Int
    ): Single<UserInfoResp>

    companion object{
        private const val SEARCH_END_POINT = "search"
    }

}