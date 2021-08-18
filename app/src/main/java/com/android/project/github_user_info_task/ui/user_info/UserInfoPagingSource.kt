package com.android.project.github_user_info_task.ui.user_info

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.android.project.github_user_info_task.data.data_class.user_info.UserDetail
import com.android.project.github_user_info_task.data.data_class.user_info.UserInfoResp
import com.android.project.github_user_info_task.data.repository.DataResult
import com.android.project.github_user_info_task.data.repository.user_info.IGetUserInfoRepository
import io.reactivex.rxjava3.core.Single
import java.lang.Exception

class UserInfoPagingSource(
    private val userName: String,
    private val repository: IGetUserInfoRepository
) : RxPagingSource<Int, UserDetail>() {

    override fun getRefreshKey(state: PagingState<Int, UserDetail>): Int? = null

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, UserDetail>> {

        val page = params.key ?: 1

        return repository.getUserInfoData(userName, page)
            .map { toLoadResult(page, it) }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(page: Int, userInfoResp: UserInfoResp): LoadResult<Int, UserDetail> {

        if (page != 1) {
            userInfoResp.items?.toMutableList()?.removeFirst()
        }

        return LoadResult.Page(
            data = userInfoResp.items ?: listOf(),
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (userInfoResp.items.isNullOrEmpty()) null else page + 1
        )
    }
}