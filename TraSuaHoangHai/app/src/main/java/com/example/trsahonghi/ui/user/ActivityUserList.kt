//package com.example.trsahonghi.ui.user
//
//import android.content.Intent
//import android.icu.text.SimpleDateFormat
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import androidx.lifecycle.MutableLiveData
//import androidx.recyclerview.widget.DividerItemDecoration
//import androidx.recyclerview.widget.LinearLayoutManager
//
//import com.example.trsahonghi.ClickItem
//
//import com.example.trsahonghi.databinding.ActivityUserListBinding
//
//import com.example.trsahonghi.api.model.ItemDonHang
//import com.google.firebase.database.*
//import java.util.*
//import kotlin.collections.ArrayList
//
//
//class ActivityUserList : AppCompatActivity(), ClickItem {
//    private lateinit var binding: ActivityUserListBinding
//    private lateinit var dbDonHang: DatabaseReference
//    private val dsLiveData: MutableLiveData<ArrayList<Oder>> = MutableLiveData()
//    private lateinit var adapter: RvAdapter_UserList
//    private var dsDonhang = ArrayList<Oder>()
//    private var dsDonHangMin = mutableListOf<ItemDonHang>()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityUserListBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//
//
//        dbDonHang = FirebaseDatabase.getInstance().getReference("DonHang")
//        dbDonHang.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                dsDonhang.clear()
//                if (snapshot.exists()) {
//                    for (data in snapshot.children) {
//                        val user = data.getValue(Oder::class.java)
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
//            dsDonHangMin.clear()
//            val bundle = intent.extras
//            if (bundle != null) {
//                val sdt = bundle.getString("SDT")
//                for (donhang in dsDonhang) {
//                    if (donhang.sdt == sdt) {
//                        dsDonHangMin.add(
//                            ItemDonHang(
//                                donhang.id,
//                                donhang.ngayDat,
//                                donhang.name,
//                                donhang.sdt,
//                                donhang.trangThai.toInt()
//                            )
//                        )
//                    }
//
//                }
//
//                // Định nghĩa định dạng ngày tháng
//                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
//
//                //  sắp xếp theo thứ tự giảm dần
//                dsDonHangMin.sortByDescending { item ->
//                    dateFormat.parse(item.ngay)
//                }
//
//                adapter.notifyDataSetChanged()
//            }
//
//        })
//
//
//        addRv()
//
//        binding.imgbtnBack.setOnClickListener {
//            finish()
//        }
//    }
//
//    private fun addRv() {
//        // rv trà sữa
//        adapter = RvAdapter_UserList(dsDonHangMin, this)
//        binding.recyclerView.adapter = adapter
//        binding.recyclerView.layoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//
//
//        val dividerItemDecoration = DividerItemDecoration(this, 1)
//        binding.recyclerView.addItemDecoration(dividerItemDecoration)
//    }
//
//    override fun onItemClickTang(position: Int) {
//        var bundle = Bundle()
//        bundle.putString("id", dsDonHangMin[position].id)
//
//        var intent = Intent(this, ActivityUser_ChiTietDonHang::class.java)
//        intent.putExtras(bundle)
//        startActivity(intent)
//    }
//
//    override fun onItemClickGiam(position: Int) {
//
//    }
//}