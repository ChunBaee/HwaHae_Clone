package com.jcorp.hwahae_clone.home

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.forEachIndexed
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jcorp.hwahae_clone.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding

    private val tabTitleArray = arrayOf(
        "홈",
        "뷰티ON",
        "이벤트",
        "랭킹",
        "어워드"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setTab()

        return binding.root
    }

    private fun setTab() {
        binding.homePager.adapter = HomePagerAdapter(requireActivity())
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
}