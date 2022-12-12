package com.example.formula1.view.mainscreen.fragments.races

import android.app.Dialog
import android.content.Intent
import android.view.Window
import com.example.formula1.data.model.racingsearchitem.Response
import com.example.formula1.databinding.FragmentRacingBinding
import com.example.formula1.utils.showSearchDialog
import com.example.formula1.utils.STRING_EMPTY
import com.example.formula1.utils.visible
import com.example.formula1.utils.gone
import com.example.formula1.utils.KEY_RACING_ID
import com.example.formula1.utils.DIALOG_TITLE_ENTER_YEAR
import com.example.formula1.utils.base.BaseFragment
import com.example.formula1.view.adapter.RaceAdapter
import com.example.formula1.viewmodel.RaceViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar

class RacingFragment : BaseFragment<FragmentRacingBinding>(FragmentRacingBinding::inflate) {

    private val raceAdapter = RaceAdapter(::onClickItem)
    private val viewModel: RaceViewModel by viewModel()
    private val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    private var dialog: Dialog? = null

    override fun setup() {
        binding.rvRacing.adapter = raceAdapter
        setupSearchDialog()
        viewModel.getRaces(currentYear.toString())
    }

    private fun setupSearchDialog(){
        context.apply {
            if (this != null) {
                dialog = Dialog(this)
            }
        }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding.btnSearch.setOnClickListener {
            dialog?.showSearchDialog(DIALOG_TITLE_ENTER_YEAR) { key ->
                if (key == STRING_EMPTY) {
                    viewModel.getRaces(currentYear.toString())
                } else {
                    viewModel.getRaces(key)
                }
            }
        }
    }

    override fun setupObservers() {
        viewModel.searchResult.observe(viewLifecycleOwner) {
            raceAdapter.submitList(it.response)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it == true){
                binding.loadingBar.visible()
            } else {
                binding.loadingBar.gone()
            }
        }
    }

    private fun onClickItem(data: Response) {
        val intent = Intent(context, RaceDetailActivity::class.java)
        intent.putExtra(KEY_RACING_ID, data.id.toString())
        startActivity(intent)
    }
}
