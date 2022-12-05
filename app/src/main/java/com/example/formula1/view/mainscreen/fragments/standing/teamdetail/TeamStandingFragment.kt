package com.example.formula1.view.mainscreen.fragments.standing.teamdetail

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.formula1.data.model.teamstanding.Response
import com.example.formula1.databinding.FragmentTeamStandingBinding
import com.example.formula1.utils.gone
import com.example.formula1.utils.visible
import com.example.formula1.utils.STRING_EMPTY
import com.example.formula1.utils.DIALOG_TITLE_ENTER_YEAR
import com.example.formula1.utils.showSearchDialog
import com.example.formula1.view.adapter.TeamAdapter
import com.example.formula1.viewmodel.StandingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar

class TeamStandingFragment : Fragment() {

    private val binding by lazy { FragmentTeamStandingBinding.inflate(layoutInflater) }
    private val viewModel: StandingViewModel by viewModel()
    private val teamAdapter = TeamAdapter(::onItemClick)
    private var dialog: Dialog? = null
    private val currentYear = Calendar.getInstance().get(Calendar.YEAR)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        setupObservers()
        viewModel.getTeamStanding(currentYear.toString())
    }

    private fun setup() {
        dialog = Dialog(requireActivity())
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding.rvTeams.adapter = teamAdapter
        binding.rvTeams.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    binding.textSelectYear.gone()
                } else {
                    binding.textSelectYear.visible()
                }
            }
        })

        binding.btnSearch.setOnClickListener {
            dialog?.showSearchDialog(DIALOG_TITLE_ENTER_YEAR) { key ->
                if (key == STRING_EMPTY) {
                    viewModel.getTeamStanding(currentYear.toString())
                } else {
                    viewModel.getTeamStanding(key)
                }
            }
        }
    }

    private fun setupObservers(){
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it == true) {
                binding.loadingBar.visible()
            } else {
                binding.loadingBar.gone()
            }
        }

        viewModel.teamList.observe(viewLifecycleOwner) {
            teamAdapter.submitList(it.response)
            if (it.response.isNullOrEmpty()) {
                binding.textSelectYear.gone()
            } else {
                binding.textSelectYear.text = it.parameters?.season
            }
        }
    }

    private fun onItemClick(item: Response) {
        //Do it later
        item
    }
}
