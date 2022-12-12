package com.example.formula1.view.mainscreen.fragments.races

import android.content.Intent
import com.example.formula1.data.model.TeamUnique
import com.example.formula1.data.model.finishpositionitem.Response
import com.example.formula1.databinding.FragmentResultBinding
import com.example.formula1.utils.KEY_DRIVER_ID
import com.example.formula1.utils.NONE
import com.example.formula1.utils.KEY_TEAM_ID
import com.example.formula1.utils.TEXT_ERROR
import com.example.formula1.utils.shortToast
import com.example.formula1.utils.loadCoverImage
import com.example.formula1.utils.base.BaseFragment
import com.example.formula1.view.adapter.FinishPositionAdapter
import com.example.formula1.view.mainscreen.fragments.standing.driverdetail.DriverStandingDetail
import com.example.formula1.viewmodel.RaceViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ResultFragment : BaseFragment<FragmentResultBinding>(FragmentResultBinding::inflate) {

    private val viewModel: RaceViewModel by sharedViewModel()
    private val listTeamUnique = mutableListOf<TeamUnique>()
    private var carURL: Int? = null
    private val finishPositionAdapter = FinishPositionAdapter(::onClickItem)

    override fun setup() {
        binding.rvFinishPosition.adapter = finishPositionAdapter
        listTeamUnique.clear()
        listTeamUnique.addAll(TeamUnique.getTeamUnique())
    }

    override fun setupObservers() {
        viewModel.finishPositionResult.observe(viewLifecycleOwner) {
            finishPositionAdapter.submitList(it.response)
        }
        viewModel.fastestLapResult.observe(viewLifecycleOwner) {
            it.response[NONE].run {
                context?.loadCoverImage(driver?.image.toString(), binding.imgDriver)
                binding.textShortenedName.text = driver?.nameTag
                binding.textFastestTime.text = time
            }
        }
    }

    private fun onClickItem(data: Response) {
        val intent = Intent(context, DriverStandingDetail::class.java)
        val driverID = data.driver?.id
        listTeamUnique.filter { team ->
            team.id == data.team?.id
        }.forEach { team ->
            carURL = team.carImage
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
