package com.example.formula1.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.formula1.data.model.startinggriditem.Response
import com.example.formula1.databinding.ItemQualifyLayoutBinding
import com.example.formula1.utils.base.BaseAdapter
import com.example.formula1.utils.base.BaseViewHolder

class StartingGridAdapter(private val onclickItem: (Response) -> Unit) :
    BaseAdapter<Response, ItemQualifyLayoutBinding, StartingGridAdapter.TeamViewHolder>(
        Response.diffUtil
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding =
            ItemQualifyLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamViewHolder(binding, onclickItem)
    }

    class TeamViewHolder(
        private val itemBinding: ItemQualifyLayoutBinding, onclickItem: (Response) -> Unit
    ) : BaseViewHolder<Response, ItemQualifyLayoutBinding>(itemBinding, onclickItem) {
        override fun onBindData(data: Response) {
            super.onBindData(data)
            itemBinding.textPosition.text = data.position.toString()
            itemBinding.textDriverName.text = data.driver?.nameTag
            itemBinding.textTime.text = data.time
        }
    }
}
