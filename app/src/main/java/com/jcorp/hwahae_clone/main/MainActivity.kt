package com.jcorp.hwahae_clone.main

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.forEachIndexed
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jcorp.hwahae_clone.R
import com.jcorp.hwahae_clone.databinding.ActivityMainBinding
import com.jcorp.hwahae_clone.home.HomePagerAdapter
import com.jcorp.hwahae_clone.home.beautyon.HomeBeautyOnViewModel
import com.jcorp.hwahae_clone.home.now.HomeNowViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val homeNowViewModel by viewModels<HomeNowViewModel>()
    private val homeBeautyOnViewModel by viewModels<HomeBeautyOnViewModel>()

    private val tabTitleArray = arrayOf(
        "홈",
        "뷰티ON",
        "이벤트",
        "랭킹",
        "어워드"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.fab.bringToFront()
        binding.mainBottomView.background = null

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_toolbar_container, ToolbarMainFragment()).commit()
        //supportFragmentManager.beginTransaction().replace(R.id.container, HomeNowFragment()).commit()

        setTab()
        checkMainTabHeight()
    }

    private fun setTab() {
        binding.homePager.adapter = HomePagerAdapter(this)
        TabLayoutMediator(binding.homeTabLayout, binding.homePager) { tab, position ->
            tab.text =  tabTitleArray[position]

        }.attach()
        binding.homeTabLayout.changeTabsFont(0)


        binding.homeTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.d("----", "onTabSelected: ${tab?.text}")
                tab?.position?.let {
                    binding.homeTabLayout.changeTabsFont(it)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }
    private fun TabLayout.changeTabsFont (selectedPosition : Int) {
        val viewGroup = this.getChildAt(0) as ViewGroup
        val tabsCount = viewGroup.childCount
        for(i in 0 until tabsCount) {
            val tab = viewGroup.getChildAt(i) as ViewGroup
            tab.forEachIndexed { index, _ ->
                val tabChild = tab.getChildAt(index)
                if(tabChild is TextView) {
                    tabChild.setTextBold(i == selectedPosition)
                }
            }
        }
    }

    private fun TextView.setTextBold(isBold : Boolean) {
        when(isBold) {
            true -> {
                this.setTypeface(null, Typeface.BOLD)
            }
            false -> {
                this.setTypeface(null, Typeface.NORMAL)
            }
        }
    }

    private fun checkMainTabHeight() {
        binding.scroll.setOnScrollChangeListener { view, i, i2, i3, i4 ->

            var location = IntArray(2)
            binding.homeTabLayout.getLocationOnScreen(location)

            homeBeautyOnViewModel.curScroll.value = i2
            homeBeautyOnViewModel.pastScroll.value = i4
            homeBeautyOnViewModel.mainTabCurHeight.value = location[1]
            homeBeautyOnViewModel.mainTabHeight = (binding.homeTabLayout.bottom - binding.homeTabLayout.top)
        }
    }
}