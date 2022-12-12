package com.example.formula1.view.mainscreen.fragments.races

import android.os.Bundle
import com.example.formula1.databinding.ActivityRaceDetailBinding
import com.example.formula1.utils.NONE
import com.example.formula1.utils.PAGE_RESULT
import com.example.formula1.utils.PAGE_STARTING_GRID
import com.example.formula1.utils.PAGE_ONE
import com.example.formula1.utils.KEY_RACING_ID
import com.example.formula1.utils.base.BaseActivity
import com.example.formula1.view.adapter.DetailViewPagerAdapter
import com.example.formula1.viewmodel.RaceViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class RaceDetailActivity :
    BaseActivity<ActivityRaceDetailBinding>(ActivityRaceDetailBinding::inflate) {

    private val viewModel: RaceViewModel by viewModel()
    private val gridFragment = GridFragment()
    private val resultFragment = ResultFragment()
    private val fragmentList = listOf(gridFragment, resultFragment)
    private val viewPagerAdapter by lazy { DetailViewPagerAdapter(this, fragmentList) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
        getData()
    }

    private fun setup() {
        binding.imgBack.setOnClickListener {
            onBackPressed()
        }
        val tabLayout = binding.tabLayoutDetail
        val viewpager = binding.viewPager
        viewpager.adapter = viewPagerAdapter
        viewpager.currentItem = NONE
        TabLayoutMediator(tabLayout, viewpager) { tab, position ->
            when (position) {
                NONE -> tab.text = PAGE_STARTING_GRID
                PAGE_ONE -> tab.text = PAGE_RESULT
            }
        }.attach()
    }

    private fun getData() {
        val id = intent.getStringExtra(KEY_RACING_ID)
        id?.let {
            viewModel.getStartingGrid(it)
            viewModel.getFinishPosition(it)
            viewModel.getFastestLap(it)
        }
    }
}
