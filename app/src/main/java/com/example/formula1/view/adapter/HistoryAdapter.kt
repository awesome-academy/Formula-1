package com.example.formula1.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.formula1.data.model.driversearchitem.Team
import com.example.formula1.databinding.ItemDriverHistoryBinding
import com.example.formula1.utils.base.BaseAdapter
import com.example.formula1.utils.base.BaseViewHolder
import com.example.formula1.utils.loadCoverImage

class HistoryAdapter(private val onclickItem: (Team) -> Unit) :
    BaseAdapter<Team, ItemDriverHistoryBinding, HistoryAdapter.TeamViewHolder>(
        Team.diffUtil
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding =
            ItemDriverHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamViewHolder(binding, onclickItem)
    }

    class TeamViewHolder(
        private val itemBinding: ItemDriverHistoryBinding, onclickItem: (Team) -> Unit
    ) : BaseViewHolder<Team, ItemDriverHistoryBinding>(itemBinding, onclickItem) {
        override fun onBindData(data: Team) {
            super.onBindData(data)
            itemBinding.textYear.text = data.season.toString()
            itemBinding.textTeamName.text = data.team?.name
            val context = itemBinding.root.context
            context.loadCoverImage(data.team?.logo.toString(), itemBinding.imgLogo)
        }
    }
}
