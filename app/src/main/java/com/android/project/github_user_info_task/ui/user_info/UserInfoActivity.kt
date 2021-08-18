package com.android.project.github_user_info_task.ui.user_info

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.project.github_user_info_task.databinding.ActivityUserInfoBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserInfoActivity: AppCompatActivity() {

    private val userInfoViewModel: UserInfoViewModel by viewModel()


    private lateinit var binding: ActivityUserInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserInfoBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        userInfoViewModel.getUserData()
        userInfoViewModel.data.observe(this){

        }
    }
}