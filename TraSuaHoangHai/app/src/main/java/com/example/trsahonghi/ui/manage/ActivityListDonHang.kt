package com.example.trsahonghi.ui.manage

import android.content.Intent
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trsahonghi.ClickItem
import com.example.trsahonghi.api.model.DonHangAdmin
import com.example.trsahonghi.api.model.ItemDonHang
import com.example.trsahonghi.databinding.ActivityListDonHangBinding
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList


class ActivityListDonHang : AppCompatActivity(), ClickItem {
    private lateinit var binding: ActivityListDonHangBinding
    private lateinit var dbDonHang: DatabaseReference
    private val dsLiveData: MutableLiveData<ArrayList<DonHangAdmin>> = MutableLiveData()
    private lateinit var adapterDonhang: RvApdapter_ListDonHang
    private var dsDonhang = ArrayList<DonHangAdmin>()
    private var dsDonHangMin = mutableListOf<ItemDonHang>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListDonHangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbDonHang = FirebaseDatabase.getInstance().getReference("DonHang")
        dbDonHang.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dsDonhang.clear()
                if (snapshot.exists()) {
                    for (data in snapshot.children) {
                        val user = data.getValue(DonHangAdmin::class.java)
                        user?.let {
                            dsDonhang.add(user!!)
                        }

                    }
                }
                dsLiveData.value = dsDonhang // Gán giá trị mới cho dsLiveData
            }

            override fun onCancelled(error: DatabaseError) {
                // Xử lý khi có lỗi truy vấn dữ liệu
            }
        })

        // Lắng nghe sự kiện thay đổi dữ liệu trong dsLiveData
        dsLiveData.observe(this, { userList ->
            dsDonHangMin.clear()
            for (donhang in dsDonhang) {
                dsDonHangMin.add(
                    ItemDonHang(
                        donhang.id,
                        donhang.ngayDat,
                        donhang.name,
                        donhang.sdt,
                        donhang.trangThai.toInt()
                    )
                )
            }


            // Sắp xếp theo trạng thái đơn hàng trước
            sortDonHangByTrangThai(dsDonHangMin)

            // Định nghĩa định dạng ngày tháng
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

            // Sắp xếp lại theo ngày tháng
            dsDonHangMin.sortByDescending { item ->
                dateFormat.parse(item.ngay)
            }
            adapterDonhang.notifyDataSetChanged()
        })


        addRv()

        binding.imgbtnBack.setOnClickListener {
            finish()
        }
    }

    private fun sortDonHangByTrangThai(donHangList: MutableList<ItemDonHang>) {
        donHangList.sortByDescending { it.trangThai }
    }

    private fun addRv() {
        // rv trà sữa
        adapterDonhang = RvApdapter_ListDonHang(dsDonHangMin, this)
        binding.recyclerView.adapter = adapterDonhang
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        val dividerItemDecoration = DividerItemDecoration(this, 1)
        binding.recyclerView.addItemDecoration(dividerItemDecoration)
    }

    override fun onItemClickTang(position: Int) {
        var bundle = Bundle()
        bundle.putString("id", dsDonHangMin[position].id)

        var intent = Intent(this, ActivityChiTietDonHang::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun onItemClickGiam(position: Int) {

    }
}

