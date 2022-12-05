package com.example.formula1.view.mainscreen.fragments.standing.driverdetail

import android.os.Bundle
import com.example.formula1.databinding.ActivityDriverStandingDetailBinding
import com.example.formula1.utils.PAGE_DETAIL
import com.example.formula1.utils.PAGE_HISTORY
import com.example.formula1.utils.NONE
import com.example.formula1.utils.PAGE_ONE
import com.example.formula1.utils.DRIVER_ID
import com.example.formula1.utils.loadCoverImage
import com.example.formula1.utils.base.BaseActivity
import com.example.formula1.view.adapter.DetailViewPagerAdapter
import com.example.formula1.viewmodel.StandingViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class DriverStandingDetail :
    BaseActivity<ActivityDriverStandingDetailBinding>(ActivityDriverStandingDetailBinding::inflate) {

    private val driverStandingFragment = DetailFragment()
    private val teamStandingFragment = HistoryFragment()
    private val fragmentList = listOf(driverStandingFragment, teamStandingFragment)
    private val viewPagerAdapter by lazy {
        DetailViewPagerAdapter(
            this@DriverStandingDetail,
            fragmentList
        )
    }
    private val viewModel: StandingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
        observeData()
        getData()
    }

    private fun setup() {
        binding.imgBack.setOnClickListener {
            onBackPressed()
        }
        val tabLayout = binding.tabLayoutDriver
        val viewpager = binding.viewPager
        viewpager.adapter = viewPagerAdapter
        viewpager.currentItem = NONE
        TabLayoutMediator(tabLayout, viewpager) { tab, position ->
            when (position) {
                NONE -> tab.text = PAGE_DETAIL
                PAGE_ONE -> tab.text = PAGE_HISTORY
            }
        }.attach()
    }

    private fun observeData(){
        viewModel.searchResult.observe(this) {
            val driverInformation = it.response?.get(NONE)
            binding.textName.text = driverInformation?.name
            binding.textDriverNumber.text = driverInformation?.number.toString()
            val url = driverInformation?.image.toString()
            loadCoverImage(url, binding.imgAvatar)
        }
    }

    private fun getData() {
        val id = intent.getStringExtra(DRIVER_ID)
        if (id != null) {
            viewModel.searchDriver(id.toInt())
        }
    }
}
