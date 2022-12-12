package com.example.formula1.view.mainscreen

import android.content.Intent
import android.os.Bundle
import com.example.formula1.data.model.TeamUnique
import com.example.formula1.data.model.driverstanding.Driver
import com.example.formula1.databinding.ActivityHistoryBinding
import com.example.formula1.utils.NONE
import com.example.formula1.utils.KEY_DRIVER_ID
import com.example.formula1.utils.KEY_TEAM_ID
import com.example.formula1.utils.HISTORY_NUMBER_ITEM
import com.example.formula1.utils.TEXT_ERROR
import com.example.formula1.utils.shortToast
import com.example.formula1.utils.base.BaseActivity
import com.example.formula1.view.adapter.SeenDriverAdapter
import com.example.formula1.view.mainscreen.fragments.standing.driverdetail.DriverStandingDetail
import com.example.formula1.viewmodel.StandingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryActivity : BaseActivity<ActivityHistoryBinding>(ActivityHistoryBinding::inflate) {

    private val driverAdapter = SeenDriverAdapter(::onItemClick)
    private val viewModel: StandingViewModel by viewModel()
    private val listTeamUnique = mutableListOf<TeamUnique>()
    private var carURL: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
        setupObservers()
        viewModel.getDriverLocal()
    }

    private fun setup() {
        listTeamUnique.clear()
        listTeamUnique.addAll(TeamUnique.getTeamUnique())
        binding.rvDrivers.adapter = driverAdapter
        binding.imgBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupObservers() {
       viewModel.driverLocalList.observe(this@HistoryActivity){ value ->
           val historyList = value.sortedBy { it.timeStamp }
           driverAdapter.submitList(historyList.reversed().take(HISTORY_NUMBER_ITEM))
       }
    }

    private fun onItemClick(item: Driver) {
        val intent = Intent(this@HistoryActivity, DriverStandingDetail::class.java)
        val driverID = item.id
        listTeamUnique.filter { a ->
            a.id == NONE
        }.forEach { a ->
            carURL = a.carImage
        }
        if (driverID != null && carURL != null) {
            intent.putExtra(KEY_DRIVER_ID, driverID.toString())
            intent.putExtra(KEY_TEAM_ID, carURL)
            startActivity(intent)
        } else {
           shortToast(TEXT_ERROR)
        }
    }
}
