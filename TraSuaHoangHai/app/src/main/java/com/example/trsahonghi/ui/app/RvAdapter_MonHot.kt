package com.example.trsahonghi.ui.app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trsahonghi.databinding.ItemMonHotBinding
import com.example.trsahonghi.api.model.MonHot

class RvAdapter_MonHot(
    var ds_mh : List<MonHot>
) : RecyclerView.Adapter<RvAdapter_MonHot.MonHotViewHolder>(){
    inner class MonHotViewHolder(private val binding: ItemMonHotBinding) : RecyclerView.ViewHolder(binding.root){
        fun  bind(itemMonHotBinding: MonHot){
            getDataLayout(itemMonHotBinding)
        }

        private fun getDataLayout(itemMonHotBinding: MonHot) {
            binding.imgMonHot.setImageResource(itemMonHotBinding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonHotViewHolder {
        val binding = ItemMonHotBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MonHotViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MonHotViewHolder, position: Int) {
        if (ds_mh != null){
            val  itemMonHotBinding = ds_mh[position]
            holder.bind(itemMonHotBinding)
        }
    }

    override fun getItemCount(): Int {
        return ds_mh.size
    }
}