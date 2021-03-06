package com.android.project.github_user_info_task.ui.user_info

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.LoadState
import com.android.project.github_user_info_task.databinding.ActivityUserInfoBinding
import io.reactivex.rxjava3.kotlin.subscribeBy
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserInfoActivity : AppCompatActivity(){

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

        searchUser("Tom")
        initUserList()
        initSearchBox()
        initErrorRetry()
        setLoadingVisibility(true)
    }

    private fun initUserList(){
        binding.rvUserData.adapter = userInfoAdapter.withLoadStateFooter(
            UserInfoLoadStateAdapter{
                userInfoAdapter.retry()
            }
        )
    }


    private fun initSearchBox(){
        binding.editSearchUser.apply {
            setOnEditorActionListener { v, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        setLoadingVisibility(true)
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
            binding.rvUserData.scrollToPosition(0)
        }

        userInfoAdapter.addLoadStateListener { loadState ->
            when(loadState.source.refresh){
                is LoadState.Error -> {
                    setLoadingVisibility(false)
                    setErrorVisibility(true)
                    setListVisibility(false)
                }
                is LoadState.Loading -> {
                    setLoadingVisibility(true)
                    setErrorVisibility(false)
                }
                is LoadState.NotLoading -> {
                    setLoadingVisibility(false)
                    setListVisibility(true)
                }
            }
        }
    }

    private fun setLoadingVisibility(show: Boolean){
        if (show){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun setListVisibility(show: Boolean){
        if (show){
            binding.rvUserData.visibility = View.VISIBLE
        }else{
            binding.rvUserData.visibility = View.GONE
        }
    }

    private fun setErrorVisibility(show: Boolean){
        if (show){
            binding.clError.visibility = View.VISIBLE
        }else{
            binding.clError.visibility = View.GONE
        }
    }

    private fun initErrorRetry(){
        binding.btnErrorRetry.setOnClickListener {
            searchUser("Tom")
        }
    }
}