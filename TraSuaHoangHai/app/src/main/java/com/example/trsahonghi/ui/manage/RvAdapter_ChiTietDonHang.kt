package com.example.trsahonghi.ui.manage

import android.icu.text.NumberFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trsahonghi.databinding.ItemTraSuaBinding
import com.example.trsahonghi.api.model.TraSua

class RvAdapter_ChiTietDonHang(
    var ds: List<TraSua>
) : RecyclerView.Adapter<RvAdapter_ChiTietDonHang.TraSuaViewHolder>() {
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

            if (itemTraSuaBinding.loatTP == -1) {
                binding.txtSize.visibility = View.GONE
            } else {
                binding.txtSize.visibility = View.VISIBLE
            }
            if (itemTraSuaBinding.loatTP == 0) {
                binding.txtSize.text = "- M"
            }
            if (itemTraSuaBinding.loatTP == 1) {
                binding.txtSize.text = "- L"
            }


            binding.imbtnGiamSoLuong.visibility = View.GONE
            binding.imbtnTangSoLuong.visibility = View.GONE

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TraSuaViewHolder {
        val binding = ItemTraSuaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TraSuaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TraSuaViewHolder, position: Int) {
        val itemTraSuaBinding = ds[position]
        holder.bind(itemTraSuaBinding)
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