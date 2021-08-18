package com.android.project.github_user_info_task.data.util

import com.android.project.github_user_info_task.data.repository.DataResult
import io.reactivex.rxjava3.core.Single

object RxUtil {
    fun <T> Single<T>.mapToResult(): Single<DataResult<T>> {
        return map<DataResult<T>> {
            DataResult.Success(it)
        }.onErrorReturn {
            DataResult.Failure()
        }
    }
}