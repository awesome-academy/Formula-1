package com.example.formula1.view.mainscreen.fragments.standing.driverdetail

import android.content.Intent
import com.example.formula1.data.model.driversearchitem.Team
import com.example.formula1.databinding.FragmentHistoryBinding
import com.example.formula1.utils.KEY_SEASON
import com.example.formula1.utils.KEY_TEAM_ID
import com.example.formula1.utils.NONE
import com.example.formula1.utils.base.BaseFragment
import com.example.formula1.view.adapter.HistoryAdapter
import com.example.formula1.view.mainscreen.fragments.standing.teamdetail.TeamDetailActivity
import com.example.formula1.viewmodel.StandingViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HistoryFragment : BaseFragment<FragmentHistoryBinding>(FragmentHistoryBinding::inflate) {

    private val viewModel: StandingViewModel by sharedViewModel()
    private val adapter = HistoryAdapter(::onClickItem)

    override fun setup() {
        binding.rvHistory.adapter = adapter
    }

    override fun setupObservers() {
        viewModel.searchResult.observe(viewLifecycleOwner) {
            val data = it.response?.get(NONE)?.teams
            adapter.submitList(data)
        }
    }

    private fun onClickItem(data: Team) {
        val intent = Intent(context, TeamDetailActivity::class.java)
        intent.putExtra(KEY_SEASON, data.season.toString())
        intent.putExtra(KEY_TEAM_ID, data.team?.id.toString())
        startActivity(intent)
    }
}
