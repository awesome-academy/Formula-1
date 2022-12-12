package com.example.formula1.data.model.startinggriditem

import androidx.recyclerview.widget.DiffUtil

data class Response(
    val driver: Driver?,
    val team: Team?,
    val position: Int?,
    val race: Race?,
    val time: String?
){
    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<Response>() {
            override fun areItemsTheSame(oldItem: Response, newItem: Response) = false

            override fun areContentsTheSame(oldItem: Response, newItem: Response) =
                oldItem == newItem
        }
    }
}
