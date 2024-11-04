package com.example.trsahonghi.app

import android.icu.text.NumberFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trsahonghi.ClickItem
import com.example.trsahonghi.databinding.ItemTraSuaBinding
import com.example.trsahonghi.model.TraSua

class RvAdapter_ThanhToan(
    var dsDonhang: List<TraSua>,
    private val onItemClickItem: ClickItem
):RecyclerView.Adapter<RvAdapter_ThanhToan.TraSuaViewHolder>()
{
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
            binding.txtSize.visibility = View.VISIBLE
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
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TraSuaViewHolder {
        val binding = ItemTraSuaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TraSuaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TraSuaViewHolder, position: Int) {
        val itemTraSuaBinding = dsDonhang[position]
        holder.bind(itemTraSuaBinding)

        holder.binding.imbtnTangSoLuong.setOnClickListener {
            onItemClickItem.onItemClickTang(position)


        }
        holder.binding.imbtnGiamSoLuong.setOnClickListener {
            onItemClickItem.onItemClickGiam(position)

        }
    }

    override fun getItemCount(): Int {
        return dsDonhang.size
    }

    // sử dụng numberFormat đặt định dạng hàng nghìn, triệu là "." sau thập phân là ","
    fun numberFormat(a: Double): String {
        val numberFormat = NumberFormat.getInstance()
        val formattedNumber = numberFormat.format(a)
        return formattedNumber.toString()
    }
}