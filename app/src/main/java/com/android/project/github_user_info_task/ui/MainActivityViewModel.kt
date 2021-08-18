package com.android.project.github_user_info_task.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.project.github_user_info_task.data.data_class.user_info.UserInfoResp
import com.android.project.github_user_info_task.data.repository.user_info.GetUserInfoRepository
import com.android.project.github_user_info_task.data.repository.user_info.IGetUserInfoRepository
import io.reactivex.rxjava3.kotlin.subscribeBy
import org.koin.java.KoinJavaComponent.get
import org.koin.java.KoinJavaComponent.inject

class MainActivityViewModel(
    private val getUserInfoRepository: IGetUserInfoRepository
): ViewModel() {

    val data = MutableLiveData<UserInfoResp>()

    fun getUserData(){
        getUserInfoRepository.getUserInfoData("Tom", 1).subscribeBy {
            data.postValue(it)
        }
    }

}