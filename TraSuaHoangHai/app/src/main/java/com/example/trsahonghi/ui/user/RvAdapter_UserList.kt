//package com.example.trsahonghi.ui.user
//
//import android.graphics.Color
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.trsahonghi.ClickItem
//import com.example.trsahonghi.databinding.ItemdonhangBinding
//import com.example.trsahonghi.api.model.ItemDonHang
//
//class RvAdapter_UserList (
//    var dsDonhang: List<ItemDonHang>,
//    private val onItemClickItem: ClickItem
//        ):RecyclerView.Adapter<RvAdapter_UserList.ItemDonHangViewHolder>() {
//
//    inner class ItemDonHangViewHolder(val binding: ItemdonhangBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun bind(itemdonhangBinding: ItemDonHang) {
//            getDataLayout(itemdonhangBinding)
//        }
//
//        private fun getDataLayout(itemdonhangBinding: ItemDonHang) {
//
//            binding.txtNgay.text = itemdonhangBinding.ngay
//            binding.txtName.text = itemdonhangBinding.name
//            binding.txtSDT.text = itemdonhangBinding.sdt
//            if (itemdonhangBinding.trangThai == 0) {
//                binding.txtTrangThai.text = "Giao hàng thành công"
//                binding.txtTrangThai.setTextColor(Color.parseColor("#050831"))
//            }
//            if (itemdonhangBinding.trangThai == 1) {
//                binding.txtTrangThai.text = "Đang giao hàng"
//                binding.txtTrangThai.setTextColor(Color.parseColor("#F8D90F"))
//            }
//            if (itemdonhangBinding.trangThai == 2) {
//                binding.txtTrangThai.text = "Xác nhận đơn"
//                binding.txtTrangThai.setTextColor(Color.parseColor("#3DF10B"))
//            }
//            if (itemdonhangBinding.trangThai == -1) {
//                binding.txtTrangThai.text = "Đơn hàng đã bị hủy"
//                binding.txtTrangThai.setTextColor(Color.parseColor("#FFF11B0B"))
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDonHangViewHolder {
//        val binding = ItemdonhangBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return ItemDonHangViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: ItemDonHangViewHolder, position: Int) {
//        val itemdonhangBinding = dsDonhang[position]
//        holder.bind(itemdonhangBinding)
//        holder.itemView.setOnClickListener {
//            onItemClickItem.onItemClickTang(position)
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return dsDonhang.size
//    }
//}