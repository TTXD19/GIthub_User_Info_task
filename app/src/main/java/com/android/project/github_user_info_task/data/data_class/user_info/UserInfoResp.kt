package com.android.project.github_user_info_task.data.data_class.user_info

import com.google.gson.annotations.SerializedName

data class UserInfoResp(
    @SerializedName("login")
    val userName: String? = null,
    val id: Long? = null,
    val avatar_url: String? = null
)
