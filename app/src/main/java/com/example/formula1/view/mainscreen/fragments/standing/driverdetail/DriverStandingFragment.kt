package com.example.formula1.view.mainscreen.fragments.standing.driverdetail

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.recyclerview.widget.RecyclerView
import com.example.formula1.data.model.TeamUnique
import com.example.formula1.data.model.driverstanding.Response
import com.example.formula1.databinding.FragmentDriverStandingBinding
import com.example.formula1.utils.showSearchDialog
import com.example.formula1.utils.DIALOG_TITLE_ENTER_YEAR
import com.example.formula1.utils.STRING_EMPTY
import com.example.formula1.utils.TEXT_ERROR
import com.example.formula1.utils.gone
import com.example.formula1.utils.visible
import com.example.formula1.utils.shortToast
import com.example.formula1.utils.KEY_DRIVER_ID
import com.example.formula1.utils.KEY_TEAM_ID
import com.example.formula1.utils.base.BaseFragment
import com.example.formula1.view.adapter.DriverAdapter
import com.example.formula1.viewmodel.StandingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar

class DriverStandingFragment :
    BaseFragment<FragmentDriverStandingBinding>(FragmentDriverStandingBinding::inflate) {

    private val viewModel: StandingViewModel by viewModel()
    private val driverAdapter = DriverAdapter(::onItemClick)
    private var dialog: Dialog? = null
    private val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    private val listTeamUnique = mutableListOf<TeamUnique>()
    private var carURL: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel.getDriverStanding(currentYear.toString())
    }

    override fun setup() {
        listTeamUnique.clear()
        listTeamUnique.addAll(TeamUnique.getTeamUnique())
        context.apply {
            if (this != null) {
                dialog = Dialog(this)
            }
        }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding.rvDrivers.adapter = driverAdapter
        binding.btnSearch.setOnClickListener {
            dialog?.showSearchDialog(DIALOG_TITLE_ENTER_YEAR) { key ->
                if (key == STRING_EMPTY) {
                    viewModel.getDriverStanding(currentYear.toString())
                } else {
                    viewModel.getDriverStanding(key)
                }
            }
        }
        binding.rvDrivers.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    binding.textSelectYear.gone()
                } else {
                    binding.textSelectYear.visible()
                }
            }
        })
    }

    override fun setupObservers() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it == true) {
                binding.loadingBar.visible()
            } else {
                binding.loadingBar.gone()
            }
        }

        viewModel.driverList.observe(viewLifecycleOwner) {
            driverAdapter.submitList(it.response)
            if (it.response.isNullOrEmpty()) {
                binding.textSelectYear.gone()
            } else {
                binding.textSelectYear.text = it.parameters?.season
            }
        }
    }

    private fun onItemClick(item: Response) {
        item.driver?.let {
            val historyItem = it.copy(timeStamp = getCurrentTime())
            viewModel.insertDriverLocal(historyItem)
        }
        val intent = Intent(context, DriverStandingDetail::class.java)
        val driverID = item.driver?.id
        listTeamUnique.filter { a ->
            a.id == item.team?.id
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

    private fun getCurrentTime() = System.currentTimeMillis()
}
