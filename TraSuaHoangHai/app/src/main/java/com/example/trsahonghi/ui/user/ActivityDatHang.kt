package com.example.trsahonghi.ui.user

import android.app.Activity
import android.content.Intent
import android.icu.text.NumberFormat
import java.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trsahonghi.ui.app.ActivityTrungTam
import com.example.trsahonghi.ui.app.RvAdapter_ThanhToan
import com.example.trsahonghi.ClickItem
import com.example.trsahonghi.databinding.ActivityDatHangBinding
import com.example.trsahonghi.api.model.DonHang
import com.example.trsahonghi.api.model.TraSua
import com.example.trsahonghi.api.model.User
import com.google.firebase.database.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


class ActivityDatHang : AppCompatActivity(), ClickItem {
    private lateinit var binding: ActivityDatHangBinding
    private lateinit var dbDonHang: DatabaseReference
    private val dsLiveData: MutableLiveData<ArrayList<User>> = MutableLiveData()
    private lateinit var adapterTrasua: RvAdapter_ThanhToan
    private var dsDonhang = mutableListOf<TraSua>()
    private lateinit var ds: ArrayList<User>
    private lateinit var dbRef: DatabaseReference
    private var tongTien: Int = 15000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatHangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val dsDonHangJson = bundle?.getString("DsDonHang")
        val sdt = bundle?.getString("SDT")
        val gson = Gson()
        val type = object : TypeToken<List<TraSua>>() {}.type
        dsDonhang = gson.fromJson<List<TraSua>>(dsDonHangJson, type) as MutableList<TraSua>
        for (trasua in dsDonhang) {
            tongTien += (trasua.giaMon + trasua.loatTP * 5000) * trasua.soLuong
        }

        binding.txtTongTien.text = numberFormat(tongTien.toDouble())



        addRv()
        dbDonHang = FirebaseDatabase.getInstance().getReference("DonHang")
        dbRef = FirebaseDatabase.getInstance().getReference("UserLogin")
        ds = arrayListOf<User>()
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                ds.clear()
                if (snapshot.exists()) {
                    for (data in snapshot.children) {
                        val userData = data.getValue(User::class.java)
                        ds.add(userData!!)
                    }
                    dsLiveData.value = ds
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        binding.imgbtnBack.setOnClickListener {
            finish()
        }

        binding.btnDathang.setOnClickListener {
            sdt?.let { it1 -> putDataNow(it1) }
            setResult(Activity.RESULT_OK)
            finish()
        }


    }

    private fun putDataNow(sdt: String) {
        var name = ""

        for (user in ds) {
            if (user.account == sdt) {
                name = user.name.toString()
                break
            }

        }

        // Lấy thời gian hiện tại
        val calendar = Calendar.getInstance()

        // Định dạng ngày/tháng/năm thành chuỗi
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val formattedDate = dateFormat.format(calendar.time)


        val DonHangId = dbDonHang.push().key!!
        val Donhang =
            DonHang(dsDonhang, DonHangId, name, formattedDate, sdt, "2", tongTien.toString())

        dbDonHang.child(DonHangId).setValue(Donhang)
            .addOnCompleteListener {
                Toast.makeText(this, "Đặt hàng thành công!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { err ->
                Toast.makeText(this, "Vui lòng kiểm tra kết nối mạng!", Toast.LENGTH_SHORT).show()
            }

    }

    private fun addRv() {
        // rv trà sữa
        adapterTrasua = RvAdapter_ThanhToan(dsDonhang, this)
        binding.recyclerView.adapter = adapterTrasua
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        val dividerItemDecoration = DividerItemDecoration(this, 1)
        binding.recyclerView.addItemDecoration(dividerItemDecoration)
    }

    override fun onItemClickTang(position: Int) {
        dsDonhang[position].soLuong += 1
        adapterTrasua.notifyDataSetChanged()
        // Cộng dồn số lượng cho từng mục trong danh sách
        tongTien += (dsDonhang[position].giaMon + dsDonhang[position].loatTP * 5000)

        binding.txtTongTien.text =
            numberFormat(tongTien.toDouble()) + "đ "

    }

    override fun onItemClickGiam(position: Int) {
        // Cộng dồn số lượng cho từng mục trong danh sách
        tongTien -= (dsDonhang[position].giaMon + dsDonhang[position].loatTP * 5000)

        binding.txtTongTien.text =
            numberFormat(tongTien.toDouble()) + "đ "

        dsDonhang[position].soLuong -= 1
        adapterTrasua.notifyDataSetChanged()


        if (dsDonhang[position].soLuong == 0) {
            dsDonhang.removeAt(position)
            adapterTrasua.notifyDataSetChanged()
        }
        if (dsDonhang.isEmpty()) {
            setResult(Activity.RESULT_OK)
            finish()

        }
    }

    private fun next() {
        var intent = Intent(applicationContext, ActivityTrungTam::class.java)
        startActivity(intent)
    }

    // sử dụng numberFormat đặt định dạng hàng nghìn, triệu là "." sau thập phân là ","
    fun numberFormat(a: Double): String {
        val numberFormat = NumberFormat.getInstance()
        val formattedNumber = numberFormat.format(a)
        return formattedNumber.toString()
    }
}


