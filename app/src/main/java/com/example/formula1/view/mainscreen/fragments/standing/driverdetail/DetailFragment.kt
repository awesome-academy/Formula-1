package com.example.formula1.view.mainscreen.fragments.standing.driverdetail

import com.example.formula1.R
import com.example.formula1.data.model.TeamUnique
import com.example.formula1.databinding.FragmentDetailBinding
import com.example.formula1.utils.NONE
import com.example.formula1.utils.base.BaseFragment
import com.example.formula1.utils.loadCoverImage
import com.example.formula1.viewmodel.StandingViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val viewModel: StandingViewModel by sharedViewModel()
    private val listTeamUnique = mutableListOf<TeamUnique>()

    override fun setup() {
        listTeamUnique.clear()
        listTeamUnique.addAll(TeamUnique.getTeamUnique())
    }

    override fun setupObservers() {
        viewModel.searchResult.observe(requireActivity()) {
            val driverInfor = it.response?.get(NONE)
            val teamID = driverInfor?.teams?.get(NONE)?.team?.id
            for(x in listTeamUnique) {
                if (x.id == teamID) {
                    context?.loadCoverImage(x.carImage, binding.imgCar)
                    break
                } else {
                    context?.loadCoverImage(R.drawable.img_car_fer, binding.imgCar)
                }
            }
            binding.textBirthdayContent.text = driverInfor?.birthdate
            binding.textBirthplaceContent.text = driverInfor?.birthplace
            binding.textNationalityContent.text = driverInfor?.nationality
            binding.textGrandPrixNumber.text = driverInfor?.grandsPrixEntered.toString()
            binding.textPodiumNumber.text = driverInfor?.podiums.toString()
            binding.textCareerPointsNumber.text = driverInfor?.careerPoints.toString()
            binding.textHighestPositionNumber.text = driverInfor?.highestRaceFinish?.position.toString()
        }
    }
}
