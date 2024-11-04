package com.example.trsahonghi.app

import android.icu.text.NumberFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trsahonghi.ClickItem
import com.example.trsahonghi.databinding.ItemTraSuaBinding
import com.example.trsahonghi.model.TraSua

class RvAdapter_TraSua(
    var ds: List<TraSua>,
    private val onItemClickItem: ClickItem

    ) : RecyclerView.Adapter<RvAdapter_TraSua.TraSuaViewHolder>() {
    inner class TraSuaViewHolder(val binding: ItemTraSuaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemTraSuaBinding: TraSua) {
            getDataLayout(itemTraSuaBinding)
        }

        private fun getDataLayout(itemTraSuaBinding: TraSua) {

            binding.imgTraSua.setImageResource(itemTraSuaBinding.image)
            binding.txtTenMon.text = itemTraSuaBinding.tenMon
            binding.txtGia.text = numberFormat(itemTraSuaBinding.giaMon.toDouble())
            binding.txtSoLuong.text = itemTraSuaBinding.soLuong.toString()

            if (itemTraSuaBinding.loatTP == -1){
                binding.txtSize.visibility = View.GONE
            }
            else{
                binding.txtSize.visibility = View.VISIBLE
            }
            if (itemTraSuaBinding.loatTP == 0){
                binding.txtSize.text = "- M"
            }
            if (itemTraSuaBinding.loatTP == 1){
                binding.txtSize.text = "- L"
            }

            if (itemTraSuaBinding.soLuong >= 1) {
                binding.imbtnGiamSoLuong.visibility = View.VISIBLE
            }

            if (itemTraSuaBinding.soLuong == 0) {
                binding.imbtnGiamSoLuong.visibility = View.GONE
                binding.txtSize.visibility = View.GONE
            }

        }

    }

    // ctrl+i
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TraSuaViewHolder {
        val binding = ItemTraSuaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TraSuaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TraSuaViewHolder, position: Int) {
        val itemTraSuaBinding = ds[position]
        holder.bind(itemTraSuaBinding)
        holder.binding.imbtnTangSoLuong.setOnClickListener {
            onItemClickItem.onItemClickTang(position)


        }
        holder.binding.imbtnGiamSoLuong.setOnClickListener {
            onItemClickItem.onItemClickGiam(position)

        }


    }

    override fun getItemCount(): Int {
        return ds.size
    }


    // sử dụng numberFormat đặt định dạng hàng nghìn, triệu là "." sau thập phân là ","
    fun numberFormat(a: Double): String {
        val numberFormat = NumberFormat.getInstance()
        val formattedNumber = numberFormat.format(a)
        return formattedNumber.toString()
    }

}