package com.android.project.github_user_info_task.data.data_class.user_info

import com.google.gson.annotations.SerializedName

data class UserInfoResp(
    val total_count: Long? = null,
    val incomplete_results: Boolean? = null,
    val items: List<UserDetail>? = null
)

data class UserDetail(
    @SerializedName("login")
    val userName: String? = null,
    val id: Long? = null,
    val avatar_url: String? = null
)
