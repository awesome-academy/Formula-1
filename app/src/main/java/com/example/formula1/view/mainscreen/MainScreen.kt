package com.example.formula1.view.mainscreen

import android.os.Bundle
import com.example.formula1.R
import com.example.formula1.databinding.ActivityMainScreenBinding
import com.example.formula1.utils.base.BaseActivity
import com.example.formula1.view.mainscreen.fragments.circuits.CircuitsFragment
import com.example.formula1.view.mainscreen.fragments.races.RacingFragment
import com.example.formula1.view.mainscreen.fragments.standing.StandingFragment

class MainScreen : BaseActivity<ActivityMainScreenBinding>(ActivityMainScreenBinding::inflate) {

    private val raceFragment = RacingFragment()
    private val standingFragment = StandingFragment()
    private val circuitFragment = CircuitsFragment()
    private val fm = supportFragmentManager
    private var standingState = false
    private var circuitState = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setup()
    }

    private fun setup() {
        val racingId = R.id.racing
        val standing = R.id.standing
        val circuitId = R.id.circuit
        fm.beginTransaction().add(R.id.fragment_holder, raceFragment).commit()
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                racingId -> fm.beginTransaction().show(raceFragment).hide(standingFragment)
                    .hide(circuitFragment).commit()
                standing -> {
                    if (!standingState) {
                        fm.beginTransaction().add(R.id.fragment_holder, standingFragment)
                            .show(standingFragment).hide(raceFragment).hide(circuitFragment)
                            .commit()
                        standingState = true
                    } else {
                        fm.beginTransaction().show(standingFragment).hide(raceFragment)
                            .hide(circuitFragment).commit()
                    }
                }

                circuitId -> {
                    if (!circuitState) {
                        fm.beginTransaction().add(R.id.fragment_holder, circuitFragment)
                            .show(circuitFragment).hide(standingFragment).hide(raceFragment)
                            .commit()
                        circuitState = true
                    } else {
                        fm.beginTransaction().show(circuitFragment).hide(standingFragment)
                            .hide(raceFragment).commit()
                    }
                }
            }
            true
        }
    }
}
