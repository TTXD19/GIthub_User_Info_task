package com.android.project.github_user_info_task.ui.user_info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import androidx.paging.rxjava3.flowable
import com.android.project.github_user_info_task.data.data_class.user_info.UserDetail
import com.android.project.github_user_info_task.data.data_class.user_info.UserInfoResp
import com.android.project.github_user_info_task.data.repository.user_info.IGetUserInfoRepository
import io.reactivex.rxjava3.core.Flowable

class UserInfoViewModel(
    private val getUserInfoRepository: IGetUserInfoRepository
) : ViewModel() {

    val data = MutableLiveData<UserInfoResp>()

    fun getUserDataList(name: String): Flowable<PagingData<UserDetail>> {
        return Pager(
            config = PagingConfig(
                pageSize = MAX_DATA_COUNT,
                enablePlaceholders = false,
                initialLoadSize = MAX_DATA_COUNT,
                prefetchDistance = PRE_FETCH_COUNT
            ),
            pagingSourceFactory = {
                UserInfoPagingSource(
                    repository = getUserInfoRepository,
                    userName = name
                )
            }
        ).flowable.cachedIn(viewModelScope)
    }

    companion object{
        const val MAX_DATA_COUNT = 30
        const val PRE_FETCH_COUNT = 2
    }
}