package com.jcorp.hwahae_clone.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.jcorp.hwahae_clone.R
import com.jcorp.hwahae_clone.databinding.ActivityMainBinding
import com.jcorp.hwahae_clone.home.HomeFragment
import com.jcorp.hwahae_clone.home.beautyon.HomeBeautyOnViewModel
import com.jcorp.hwahae_clone.home.now.HomeNowViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val homeNowViewModel by viewModels<HomeNowViewModel>()
    private val homeBeautyOnViewModel by viewModels<HomeBeautyOnViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.fab.bringToFront()
        binding.mainBottomView.background = null

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_toolbar_container, ToolbarMainFragment()).commit()
        supportFragmentManager.beginTransaction().replace(R.id.container, HomeFragment()).commit()


    }
}