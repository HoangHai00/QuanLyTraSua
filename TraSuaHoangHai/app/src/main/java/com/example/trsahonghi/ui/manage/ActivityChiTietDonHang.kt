//package com.example.trsahonghi.ui.manage
//
//import android.icu.text.NumberFormat
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.View
//import androidx.lifecycle.MutableLiveData
//import androidx.recyclerview.widget.DividerItemDecoration
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.trsahonghi.databinding.ActivityChiTietDonHangBinding
//import com.example.trsahonghi.api.model.BubbleTea
//import com.example.trsahonghi.api.model.Order
//import com.google.firebase.database.*
//
//
//class ActivityChiTietDonHang : AppCompatActivity() {
//
//    private lateinit var binding: ActivityChiTietDonHangBinding
//    private lateinit var dbDonHang: DatabaseReference
//    private val dsLiveData: MutableLiveData<ArrayList<Order>> = MutableLiveData()
//    private var dsDonhang = ArrayList<Order>()
//    private var ds = ArrayList<BubbleTea>()
//    private lateinit var adapterChitietdonhang: RvAdapter_ChiTietDonHang
//    private var id: String = ""
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityChiTietDonHangBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//
//        val bundle = intent.extras
//        if (bundle != null) {
//            id = bundle.getString("id").toString()
//        }
//
//        dbDonHang = FirebaseDatabase.getInstance().getReference("DonHang")
//        dbDonHang.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                dsDonhang.clear()
//                if (snapshot.exists()) {
//                    for (data in snapshot.children) {
//                        val user = data.getValue(Order::class.java)
//                        user?.let {
//                            dsDonhang.add(user!!)
//                        }
//
//                    }
//                }
//                dsLiveData.value = dsDonhang // Gán giá trị mới cho dsLiveData
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                // Xử lý khi có lỗi truy vấn dữ liệu
//            }
//        })
//
//        // Lắng nghe sự kiện thay đổi dữ liệu trong dsLiveData
//        dsLiveData.observe(this, { userList ->
//            ds.clear()
//            for (donhang in dsDonhang) {
//                if (id == donhang.id) {
//                    binding.txtNgay.text = donhang.ngayDat
//                    binding.txtName.text = donhang.name
//                    binding.txtSDT.text = donhang.sdt
//                    binding.txtTongTien.text =
//                        "Tổng tiền: " + numberFormat(donhang.tongTien.toDouble())
//                    ds.addAll(donhang.dsMon)
//                    adapterChitietdonhang.notifyDataSetChanged()
//                    if (donhang.trangThai == "0") {
//                        binding.btnGiaoHang.visibility = View.GONE
//                        binding.btnHuyDonHang.visibility = View.GONE
//                        binding.btnHoanThanh.visibility = View.GONE
//                        binding.txtTrangThai.visibility = View.VISIBLE
//                        binding.txtTrangThai.text = "Giao hàng thành công"
//                    }
//                    if (donhang.trangThai == "1") {
//                        binding.btnGiaoHang.visibility = View.GONE
//                        binding.btnHuyDonHang.visibility = View.VISIBLE
//                        binding.txtTrangThai.visibility = View.GONE
//                        binding.btnHoanThanh.visibility = View.VISIBLE
//                    }
//                    if (donhang.trangThai == "2") {
//                        binding.btnHoanThanh.visibility = View.GONE
//                        binding.txtTrangThai.visibility = View.GONE
//                        binding.btnGiaoHang.visibility = View.VISIBLE
//                        binding.btnHuyDonHang.visibility = View.VISIBLE
//                    }
//                    if (donhang.trangThai == "-1") {
//                        binding.btnGiaoHang.visibility = View.GONE
//                        binding.btnHuyDonHang.visibility = View.GONE
//                        binding.btnHoanThanh.visibility = View.GONE
//                        binding.txtTrangThai.visibility = View.VISIBLE
//                        binding.txtTrangThai.text = "Đơn hàng Đã Hủy"
//                    }
//
//                    break
//                }
//            }
//
//        })
//
//        addRv()
//        clickBtn()
//
//    }
//
//    private fun clickBtn() {
//        binding.imgbtnBack.setOnClickListener {
//            finish()
//        }
//        binding.btnGiaoHang.setOnClickListener {
//            updateTrangThai("1")
//            finish()
//        }
//        binding.btnHuyDonHang.setOnClickListener {
//            updateTrangThai("-1")
//            finish()
//        }
//        binding.btnHoanThanh.setOnClickListener {
//            updateTrangThai("0")
//            finish()
//        }
//    }
//
//    private fun updateTrangThai(trangThai: String) {
//        // Cập nhật trạng thái đơn hàng lên Firebase
//        val donHangRef = dbDonHang.child(id)
//
//        val updates: MutableMap<String, Any> = HashMap()
//        updates["trangThai"] = trangThai
//
//        donHangRef.updateChildren(updates)
//            .addOnSuccessListener {
//                // Gọi hàm hoặc thực hiện hành động khác sau khi cập nhật thành công
//            }
//            .addOnFailureListener {
//            }
//    }
//
//    private fun addRv() {
//        // rv trà sữa
//        adapterChitietdonhang = RvAdapter_ChiTietDonHang(ds)
//        binding.recyclerView.adapter = adapterChitietdonhang
//        binding.recyclerView.layoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//
//
//        val dividerItemDecoration = DividerItemDecoration(this, 1)
//        binding.recyclerView.addItemDecoration(dividerItemDecoration)
//    }
//
//
//    // sử dụng numberFormat đặt định dạng hàng nghìn, triệu là "." sau thập phân là ","
//    fun numberFormat(a: Double): String {
//        val numberFormat = NumberFormat.getInstance()
//        val formattedNumber = numberFormat.format(a)
//        return formattedNumber.toString()
//    }
//
//}
//
