package com.example.formula1.data.model.driversearchitem

import androidx.recyclerview.widget.DiffUtil

data class Team(
    val season: Int?,
    val team: TeamX?
) {
    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<Team>() {
            override fun areItemsTheSame(oldItem: Team, newItem: Team) =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Team, newItem: Team) =
                oldItem == newItem
        }
    }
}
