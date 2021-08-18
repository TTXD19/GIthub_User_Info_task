package com.android.project.github_user_info_task.ui.user_info

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.project.github_user_info_task.databinding.ActivityUserInfoBinding
import io.reactivex.rxjava3.kotlin.subscribeBy
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserInfoActivity : AppCompatActivity() {

    private val userInfoViewModel: UserInfoViewModel by viewModel()


    private lateinit var binding: ActivityUserInfoBinding
    private val userInfoAdapter: UserInfoAdapter by lazy {
        UserInfoAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserInfoBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        binding.rvUserData.adapter = userInfoAdapter

        userInfoViewModel.getUserDataList().subscribeBy {
            userInfoAdapter.submitData(lifecycle, it)
        }
    }
}