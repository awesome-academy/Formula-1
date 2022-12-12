package com.example.formula1.data.model.racingsearchitem

import androidx.recyclerview.widget.DiffUtil

data class Response(
    val circuit: Circuit?,
    val competition: Competition?,
    val date: String?,
    val distance: String?,
    val id: Int?,
    val laps: Laps?,
    val season: Int?,
    val status: String?,
    val type: String?,
) {
    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<Response>() {
            override fun areItemsTheSame(oldItem: Response, newItem: Response) = false

            override fun areContentsTheSame(oldItem: Response, newItem: Response) =
                oldItem == newItem
        }
    }
}
