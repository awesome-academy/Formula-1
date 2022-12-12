package com.example.formula1.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.formula1.data.model.racingsearchitem.Response
import com.example.formula1.databinding.ItemRacingBinding
import com.example.formula1.utils.RB_ID
import com.example.formula1.utils.INPUT_TIME_FORMAT
import com.example.formula1.utils.OUTPUT_TIME_FORMAT
import com.example.formula1.utils.base.BaseAdapter
import com.example.formula1.utils.base.BaseViewHolder
import com.example.formula1.utils.loadCoverImage
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Locale

class RaceAdapter(private val onclickItem: (Response) -> Unit) :
    BaseAdapter<Response, ItemRacingBinding, RaceAdapter.TeamViewHolder>(
        Response.diffUtil
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding =
            ItemRacingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamViewHolder(binding, onclickItem)
    }

    class TeamViewHolder(
        private val itemBinding: ItemRacingBinding, onclickItem: (Response) -> Unit
    ) : BaseViewHolder<Response, ItemRacingBinding>(itemBinding, onclickItem) {
        override fun onBindData(data: Response) {
            super.onBindData(data)
            val context = itemBinding.root.context
            itemBinding.textRacingName.text = data.competition?.name
            itemBinding.textPosition.text = adapterPosition.plus(RB_ID).toString()
            itemBinding.textDate.text = data.date?.let { convertDate(it) }
            context.loadCoverImage(data.circuit?.image.toString(), itemBinding.imgCircuit)
        }

        private fun convertDate(value: String): String {
            val inputFormat: DateFormat = SimpleDateFormat(INPUT_TIME_FORMAT, Locale.ROOT)
            val outputFormat: DateFormat = SimpleDateFormat(OUTPUT_TIME_FORMAT, Locale.ROOT)
            val parsedDate = inputFormat.parse(value)
            val result = parsedDate?.let { outputFormat.format(it) }
            return result.toString()
        }
    }
}
