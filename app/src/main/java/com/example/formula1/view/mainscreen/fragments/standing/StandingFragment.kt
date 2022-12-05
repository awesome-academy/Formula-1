package com.example.formula1.view.mainscreen.fragments.standing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.formula1.databinding.FragmentStandingBinding
import com.example.formula1.utils.NONE
import com.example.formula1.utils.PAGE_CONSTRUCTORS
import com.example.formula1.utils.PAGE_DRIVERS
import com.example.formula1.utils.PAGE_ONE
import com.example.formula1.view.adapter.ViewPagerAdapter
import com.example.formula1.view.mainscreen.fragments.standing.driverdetail.DriverStandingFragment
import com.example.formula1.view.mainscreen.fragments.standing.teamdetail.TeamStandingFragment
import com.google.android.material.tabs.TabLayoutMediator

class StandingFragment : Fragment() {

    private val binding by lazy { FragmentStandingBinding.inflate(layoutInflater) }

    private val driverStandingFragment = DriverStandingFragment()
    private val teamStandingFragment = TeamStandingFragment()
    private val fragmentList = listOf(driverStandingFragment, teamStandingFragment)
    private val viewPagerAdapter by lazy { ViewPagerAdapter(this, fragmentList) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        val tabLayout = binding.tabLayoutStanding
        val viewpager = binding.viewPager
        viewpager.adapter = viewPagerAdapter
        viewpager.currentItem = NONE
        TabLayoutMediator(tabLayout, viewpager) { tab, position ->
            when (position) {
                NONE -> tab.text = PAGE_DRIVERS
                PAGE_ONE -> tab.text = PAGE_CONSTRUCTORS
            }
        }.attach()
    }
}
