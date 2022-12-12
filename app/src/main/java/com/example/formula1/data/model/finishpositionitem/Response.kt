package com.example.formula1.data.model.finishpositionitem

import androidx.recyclerview.widget.DiffUtil
import com.example.formula1.data.model.startinggriditem.Driver
import com.example.formula1.data.model.startinggriditem.Race
import com.example.formula1.data.model.startinggriditem.Team

data class Response(
    val driver: Driver?,
    val grid: String?,
    val laps: Int?,
    val pits: Int?,
    val position: Int?,
    val race: Race?,
    val team: Team?,
    val time: String?,
    val point: Int?
) {
    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<Response>() {
            override fun areItemsTheSame(oldItem: Response, newItem: Response) = false

            override fun areContentsTheSame(oldItem: Response, newItem: Response) =
                oldItem == newItem
        }
    }
}
