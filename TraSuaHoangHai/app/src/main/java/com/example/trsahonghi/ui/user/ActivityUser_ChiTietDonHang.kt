package com.example.trsahonghi.ui.user

import android.graphics.Color
import android.icu.text.NumberFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trsahonghi.ui.manage.RvAdapter_ChiTietDonHang
import com.example.trsahonghi.databinding.ActivityUserChiTietDonHangBinding
import com.example.trsahonghi.api.model.DonHang
import com.example.trsahonghi.api.model.TraSua
import com.google.firebase.database.*


class ActivityUser_ChiTietDonHang : AppCompatActivity() {
    private lateinit var binding: ActivityUserChiTietDonHangBinding
    private lateinit var dbDonHang: DatabaseReference
    private val dsLiveData: MutableLiveData<ArrayList<DonHang>> = MutableLiveData()
    private var dsDonhang = ArrayList<DonHang>()
    private var ds = ArrayList<TraSua>()
    private lateinit var adapterChitietdonhang: RvAdapter_ChiTietDonHang
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserChiTietDonHangBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var id: String = ""
        val bundle = intent.extras
        if (bundle != null) {
            id = bundle.getString("id").toString()
        }

        dbDonHang =
            FirebaseDatabase.getInstance().getReference("DonHang")
        dbDonHang.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dsDonhang.clear()
                if (snapshot.exists()) {
                    for (data in snapshot.children) {
                        val user = data.getValue(DonHang::class.java)
                        user?.let {
                            dsDonhang.add(user!!)
                        }

                    }
                }
                dsLiveData.value =
                    dsDonhang // Gán giá trị mới cho dsLiveData
            }

            override fun onCancelled(error: DatabaseError) {
                // Xử lý khi có lỗi truy vấn dữ liệu
            }
        })

        // Lắng nghe sự kiện thay đổi dữ liệu trong dsLiveData
        dsLiveData.observe(this, { userList ->
            ds.clear()
            for (donhang in dsDonhang) {
                if (id == donhang.id) {
                    binding.txtNgay.text = donhang.ngayDat
                    binding.txtName.text = donhang.name
                    binding.txtSDT.text = donhang.sdt
                    binding.txtTongTien.text =
                        "Tổng tiền: " + numberFormat(donhang.tongTien.toDouble())
                    ds.addAll(donhang.dsMon)
                    adapterChitietdonhang.notifyDataSetChanged()
                    if (donhang.trangThai == "0") {
                        binding.txtTrangThai.text = "Đã hoàn thành"
                        binding.txtTrangThai.setTextColor(Color.parseColor("#FFF11B0B"))
                    }
                    if (donhang.trangThai == "1") {
                        binding.txtTrangThai.text = "Đang giao hàng"
                        binding.txtTrangThai.setTextColor(Color.parseColor("#F8D90F"))
                    }
                    if (donhang.trangThai == "2") {
                        binding.txtTrangThai.text = "Xác nhận đơn"
                        binding.txtTrangThai.setTextColor(Color.parseColor("#3DF10B"))
                    }
                    if (donhang.trangThai == "-1") {
                        binding.txtTrangThai.text = "Đơn hàng đã bị hủy"
                        binding.txtTrangThai.setTextColor(Color.parseColor("#050831"))
                    }
                    break
                }
            }

        })

        addRv()
        clickBtn()

    }

    private fun clickBtn() {
        binding.imgbtnBack.setOnClickListener {
            finish()
        }
    }

    private fun addRv() {
        // rv trà sữa
        adapterChitietdonhang = RvAdapter_ChiTietDonHang(ds)
        binding.recyclerView.adapter = adapterChitietdonhang
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        val dividerItemDecoration = DividerItemDecoration(this, 1)
        binding.recyclerView.addItemDecoration(dividerItemDecoration)
    }


    // sử dụng numberFormat đặt định dạng hàng nghìn, triệu là "." sau thập phân là ","
    fun numberFormat(a: Double): String {
        val numberFormat = NumberFormat.getInstance()
        val formattedNumber = numberFormat.format(a)
        return formattedNumber.toString()
    }
}