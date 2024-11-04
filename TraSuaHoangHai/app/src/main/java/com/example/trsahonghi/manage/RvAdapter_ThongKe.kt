package com.example.trsahonghi.manage

import android.icu.text.NumberFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trsahonghi.databinding.ItemThongkeBinding
import com.example.trsahonghi.model.TongTien

class RvAdapter_ThongKe(
    private val ds: List<TongTien>
) : RecyclerView.Adapter<RvAdapter_ThongKe.ThongkeViewHolder>() {
    inner class ThongkeViewHolder(val binding: ItemThongkeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TongTien) {
            binding.txtNgay.text = item.ngayDat
            binding.txtTongTien.text = numberFormat(item.tongTien) + "đ"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThongkeViewHolder {
        val binding = ItemThongkeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ThongkeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ThongkeViewHolder, position: Int) {
        val item = ds[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return ds.size
    }

    // Sử dụng numberFormat định dạng số tiền
    private fun numberFormat(a: Double): String {
        val numberFormat = NumberFormat.getInstance()
        val formattedNumber = numberFormat.format(a)
        return formattedNumber.toString()
    }
}