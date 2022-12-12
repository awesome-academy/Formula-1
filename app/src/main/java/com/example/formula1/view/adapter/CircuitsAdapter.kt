package com.example.formula1.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.formula1.data.model.circuitsearchitem.Response
import com.example.formula1.databinding.ItemCircuitBinding
import com.example.formula1.utils.RB_ID
import com.example.formula1.utils.base.BaseAdapter
import com.example.formula1.utils.base.BaseViewHolder
import com.example.formula1.utils.loadCoverImage

class CircuitsAdapter(private val onclickItem: (Response) -> Unit) :
    BaseAdapter<Response, ItemCircuitBinding, CircuitsAdapter.TeamViewHolder>(
        Response.diffUtil
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding =
            ItemCircuitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamViewHolder(binding, onclickItem)
    }

    class TeamViewHolder(
        private val itemBinding: ItemCircuitBinding, onclickItem: (Response) -> Unit
    ) : BaseViewHolder<Response, ItemCircuitBinding>(itemBinding, onclickItem) {
        override fun onBindData(data: Response) {
            super.onBindData(data)
            val context = itemBinding.root.context
            itemBinding.textCircuitName.text = data.name
            itemBinding.textPosition.text = adapterPosition.plus(RB_ID).toString()
            context.loadCoverImage(data.image.toString(), itemBinding.imgCircuit)
        }
    }
}
