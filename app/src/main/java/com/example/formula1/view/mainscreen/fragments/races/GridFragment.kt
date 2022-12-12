package com.example.formula1.view.mainscreen.fragments.races

import android.content.Intent
import com.example.formula1.data.model.TeamUnique
import com.example.formula1.data.model.startinggriditem.Response
import com.example.formula1.databinding.FragmentGridBinding
import com.example.formula1.utils.KEY_DRIVER_ID
import com.example.formula1.utils.KEY_TEAM_ID
import com.example.formula1.utils.TEXT_ERROR
import com.example.formula1.utils.base.BaseFragment
import com.example.formula1.utils.shortToast
import com.example.formula1.view.adapter.StartingGridAdapter
import com.example.formula1.view.mainscreen.fragments.standing.driverdetail.DriverStandingDetail
import com.example.formula1.viewmodel.RaceViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class GridFragment : BaseFragment<FragmentGridBinding>(FragmentGridBinding::inflate) {

    private val gridAdapter = StartingGridAdapter(::onClickItem)
    private val viewModel: RaceViewModel by sharedViewModel()
    private val listTeamUnique = mutableListOf<TeamUnique>()
    private var carURL: Int? = null

    override fun setup() {
        binding.rvGrid.adapter = gridAdapter
        listTeamUnique.clear()
        listTeamUnique.addAll(TeamUnique.getTeamUnique())
    }

    override fun setupObservers() {
        viewModel.gridResult.observe(viewLifecycleOwner) {
            gridAdapter.submitList(it.response)
        }
    }

    private fun onClickItem(data: Response) {
        val intent = Intent(context, DriverStandingDetail::class.java)
        val driverID = data.driver?.id
        listTeamUnique.filter { a ->
            a.id == data.team?.id
        }.forEach { a ->
            carURL = a.carImage
        }
        if (driverID != null && carURL != null) {
            intent.putExtra(KEY_DRIVER_ID, driverID.toString())
            intent.putExtra(KEY_TEAM_ID, carURL)
            startActivity(intent)
        } else {
            context?.shortToast(TEXT_ERROR)
        }
    }
}
