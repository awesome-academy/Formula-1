package com.example.formula1.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.formula1.data.model.finishpositionitem.Response
import com.example.formula1.data.model.getScoreByPosition
import com.example.formula1.databinding.ItemFinishResultLayoutBinding
import com.example.formula1.utils.NONE
import com.example.formula1.utils.LAST_POSITION
import com.example.formula1.utils.base.BaseAdapter
import com.example.formula1.utils.base.BaseViewHolder

class FinishPositionAdapter(private val onclickItem: (Response) -> Unit) :
    BaseAdapter<Response, ItemFinishResultLayoutBinding, FinishPositionAdapter.TeamViewHolder>(
        Response.diffUtil
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding =
            ItemFinishResultLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamViewHolder(binding, onclickItem)
    }

    class TeamViewHolder(
        private val itemBinding: ItemFinishResultLayoutBinding, onclickItem: (Response) -> Unit
    ) : BaseViewHolder<Response, ItemFinishResultLayoutBinding>(itemBinding, onclickItem) {
        override fun onBindData(data: Response) {
            super.onBindData(data)
            val scoreList = getScoreByPosition()
            itemBinding.textPosition.text = data.position.toString()
            itemBinding.textDriverName.text = data.driver?.nameTag
            itemBinding.textTime.text = data.time
            if (adapterPosition > LAST_POSITION) {
                itemBinding.textPoint.text = NONE.toString()
            } else {
                itemBinding.textPoint.text = scoreList[adapterPosition].toString()
            }
        }
    }
}
