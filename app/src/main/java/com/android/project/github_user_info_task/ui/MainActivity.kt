package com.android.project.github_user_info_task.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.project.github_user_info_task.databinding.ActivityMainBinding
import com.android.project.github_user_info_task.ui.user_info.UserInfoActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        val intent = Intent(this, UserInfoActivity::class.java)
        startActivity(intent)
        finish()

    }
}