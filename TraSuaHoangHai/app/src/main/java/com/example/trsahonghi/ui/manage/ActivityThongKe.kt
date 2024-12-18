//package com.example.trsahonghi.ui.manage
//
//import android.app.DatePickerDialog
//import android.icu.text.SimpleDateFormat
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import androidx.lifecycle.MutableLiveData
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.trsahonghi.databinding.ActivityThongKeBinding
//import com.google.firebase.database.*
//import java.util.*
//import kotlin.collections.ArrayList
//
//
//class ActivityThongKe : AppCompatActivity() {
//
//    private lateinit var binding: ActivityThongKeBinding
//    private lateinit var dbDonHang: DatabaseReference
//    private val dsLiveData: MutableLiveData<ArrayList<DonHangAdmin>> = MutableLiveData()
//    private lateinit var adapter: RvAdapter_ThongKe
//    private var dsDonhang = ArrayList<DonHangAdmin>()
//    private var dsTongTien = mutableListOf<TongTien>()
//    private var selectedDate: Calendar = Calendar.getInstance()
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityThongKeBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//
//        dbDonHang = FirebaseDatabase.getInstance().getReference("DonHang")
//        dbDonHang.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                dsDonhang.clear()
//                if (snapshot.exists()) {
//                    for (data in snapshot.children) {
//                        val user = data.getValue(DonHangAdmin::class.java)
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
//            dsTongTien.clear()
//            val ngayTongTienMap = mutableMapOf<String, Double>()
//            for (donHang in dsDonhang) {
//                if (donHang.trangThai == "0") {
//                    val tongTien = donHang.tongTien.toDouble()
//                    if (ngayTongTienMap.containsKey(donHang.ngayDat)) {
//                        ngayTongTienMap[donHang.ngayDat] =
//                            ngayTongTienMap[donHang.ngayDat]!! + tongTien
//                    } else {
//                        ngayTongTienMap[donHang.ngayDat] = tongTien
//                    }
//                }
//
//            }
//
//            // Thêm dữ liệu vào danh sách ngayTongTienList
//            for ((ngayDat, tongTien) in ngayTongTienMap) {
//                dsTongTien.add(TongTien(ngayDat, tongTien))
//            }
//
//            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
//            // Sắp xếp lại theo ngày tháng
//            dsTongTien.sortByDescending { item ->
//                dateFormat.parse(item.ngayDat)
//            }
//            adapter.notifyDataSetChanged()
//
//        })
//
//
//
//        timkiem()
//
//        clickBtn()
//
//        addRv()
//
//
//    }
//
//    private fun clickBtn() {
//        binding.imgbtnBack.setOnClickListener {
//            finish()
//        }
//        binding.imgbtnClose.setOnClickListener {
//            binding.txtSearch.text = ""
//            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
//            // Sắp xếp lại theo ngày tháng
//            dsTongTien.sortByDescending { item ->
//                dateFormat.parse(item.ngayDat)
//            }
//            adapter.notifyDataSetChanged()
//        }
//    }
//
//    private fun timkiem() {
//        binding.imgbtnSearch.setOnClickListener {
//            showDatePickerDialog()
//        }
//        binding.linearSearch.setOnClickListener {
//            showDatePickerDialog()
//        }
//    }
//
//    private fun showDatePickerDialog() {
//        val datePicker = DatePickerDialog(
//            this,
//            { _, year, month, dayOfMonth ->
//                selectedDate.set(year, month, dayOfMonth)
//                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
//                val formattedDate = dateFormat.format(selectedDate.time)
//                binding.txtSearch.text = formattedDate
//                val searchedDate = binding.txtSearch.text.toString()
//
//                for (index in 0 until dsTongTien.size) {
//                    val ds = dsTongTien[index]
//                    if (ds.ngayDat == searchedDate) {
//                        // Chuyển item lên đầu danh sách
//                        dsTongTien.removeAt(index)
//                        dsTongTien.add(0, ds)
//                        adapter.notifyDataSetChanged() // Cập nhật RecyclerView
//                        break // Kết thúc vòng lặp nếu đã tìm thấy
//                    }
//
//                }
//            },
//            selectedDate.get(Calendar.YEAR),
//            selectedDate.get(Calendar.MONTH),
//            selectedDate.get(Calendar.DAY_OF_MONTH)
//        )
//
//        datePicker.show()
//    }
//
//
//    private fun addRv() {
//        adapter = RvAdapter_ThongKe(dsTongTien)
//        binding.rvThongKe.adapter = adapter
//        binding.rvThongKe.layoutManager =
//            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//    }
//}
//
//
