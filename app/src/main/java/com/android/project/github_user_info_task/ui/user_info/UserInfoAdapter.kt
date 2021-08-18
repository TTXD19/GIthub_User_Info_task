package com.android.project.github_user_info_task.ui.user_info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.project.github_user_info_task.data.data_class.user_info.UserDetail
import com.android.project.github_user_info_task.databinding.VhUserInfoBinding
import com.bumptech.glide.Glide

class UserInfoAdapter: PagingDataAdapter<UserDetail, UserInfoAdapter.UserInfoViewHolder>(
    ItemDiffCallBack()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserInfoViewHolder {
        return UserInfoViewHolder(
            VhUserInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserInfoViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class UserInfoViewHolder(
        private val binding: VhUserInfoBinding
    ): RecyclerView.ViewHolder(binding.root){

        fun bind(data: UserDetail){
            binding.tvUserName.text = data.userName
            Glide.with(binding.root.context).load(data.avatar_url).into(binding.ivUserAvatar)
        }
    }


    companion object{
        class ItemDiffCallBack: DiffUtil.ItemCallback<UserDetail>(){
            override fun areItemsTheSame(oldItem: UserDetail, newItem: UserDetail): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UserDetail, newItem: UserDetail): Boolean {
                return oldItem == newItem
            }

        }
    }
}