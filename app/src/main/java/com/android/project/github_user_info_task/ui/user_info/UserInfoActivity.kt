package com.android.project.github_user_info_task.ui.user_info

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
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
        searchUser("Tom")
        initSearchBox()
    }


    private fun initSearchBox(){
        binding.editSearchUser.apply {
            setOnEditorActionListener { v, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        searchUser(v.text.toString())
                        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                            .hideSoftInputFromWindow(windowToken, 0)
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun searchUser(name: String){
        userInfoViewModel.getUserDataList(name).subscribeBy {
            userInfoAdapter.submitData(lifecycle, it)
        }
    }
}