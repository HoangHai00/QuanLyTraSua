package com.example.trsahonghi.App

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.icu.text.NumberFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Adapter
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.marginBottom
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.trsahonghi.ClickItem
import com.example.trsahonghi.R
import com.example.trsahonghi.User.ActivityDatHang
import com.example.trsahonghi.User.ActivityThongTin
import com.example.trsahonghi.databinding.ActivityTrungTamBinding

import com.example.trsahonghi.model.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson

class ActivityTrungTam : AppCompatActivity(), ClickItem {
    private lateinit var binding: ActivityTrungTamBinding
    private var dsTP = mutableListOf<LoaiTp>()
    private var dsDonhang = mutableListOf<TraSua>()
    private var filteredDs = mutableListOf<TraSua>()
    private var ds = mutableListOf<TraSua>()
    private var ds_mh = mutableListOf<MonHot>()
    private lateinit var adapterTrasua: RvAdapter_TraSua
    private lateinit var adapterMonhot: RvAdapter_MonHot
    private var totalSoLuong = 0
    private var totalTongTien = 0
    private lateinit var dbRef: DatabaseReference
    private var soLuong: Int = 1
    private var loatTP: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrungTamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // thêm trà sữa vào ds
        addDS_Tra_Sua()


        // thêm món hót vào ds_mh
        adDS_MonHot()


        // add Layout
        addRV()

        // SearchView
        SearchView()

        nextActivity()

        binding.imgbtnBack.setOnClickListener {
            finish()
            Toast.makeText(this, "Bạn đã đăng xuất!", Toast.LENGTH_SHORT).show()
        }

        dbRef = FirebaseDatabase.getInstance().getReference("DonHang")

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_ACTIVITY_DAT_HANG && resultCode == Activity.RESULT_OK) {
            ds.clear()
            adapterTrasua.notifyDataSetChanged()
            addDS_Tra_Sua()
            totalSoLuong = 0
            totalTongTien = 0

            binding.nestedScrollView.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                bottomMargin =
                    resources.getDimensionPixelSize(R.dimen.fix_margin_bottom_nested_scroll_view)
            }

            binding.cvGioHang.visibility = View.GONE

            // Đây là nơi bạn thực hiện các tác vụ reset trạng thái của ActivityTrungTam
            // Ví dụ: clear danh sách dsDonhang, reset các biến, hay cập nhật lại giao diện, vv.
            // Code trong phần này sẽ được thực thi sau khi kết thúc ActivityDatHang và quay lại ActivityTrungTam.
            // Hãy đảm bảo là bạn reset các trạng thái cần thiết ở đây.
        }

    }

    companion object {
        const val REQUEST_CODE_ACTIVITY_DAT_HANG = 1001
    }

    private fun nextActivity() {
        val bundle = intent.extras
        if (bundle != null) {
            val sdt = bundle.getString("SDT")
            binding.imgThongTin.setOnClickListener {
                var nextAcThongTin = Intent(this, ActivityThongTin::class.java)
                var bundleTT = Bundle()
                bundleTT.putString("SDT", sdt)
                nextAcThongTin.putExtras(bundleTT)
                startActivity(nextAcThongTin)
            }
        }

        binding.btnTrangThanhToan.setOnClickListener {
            dsDonhang.clear()
            for (trasua in ds) {
                if (trasua.soLuong > 0) {
                    dsDonhang.add(trasua)
                }
            }
            val bundle = intent.extras
            if (bundle != null) {
                val sdt = bundle.getString("SDT")
                val gson = Gson()
                val dsDonHangJson = gson.toJson(dsDonhang)

                var bundleThT = Bundle()
                bundleThT.putString("DsDonHang", dsDonHangJson)
                bundleThT.putString("SDT", sdt)

                var nextAcDatHang = Intent(this, ActivityDatHang::class.java)
                nextAcDatHang.putExtras(bundleThT)
                // Thay đổi thành startActivityForResult()
                startActivityForResult(nextAcDatHang, REQUEST_CODE_ACTIVITY_DAT_HANG)
            }
        }
    }


    private fun loatSoluong(position: Int, soLuong: Int, loatTp: Int) {

        ds[position].soLuong = soLuong
        ds[position].loatTP = loatTp
        adapterTrasua.notifyDataSetChanged()

        if (dsTP.size != null) {
            for (a in dsTP) {
                if (position == a.position) {
                    dsTP.remove(a)
                }
            }
        }

        dsTP.add(LoaiTp(position, loatTp))


        // Cộng dồn số lượng cho từng mục trong danh sách
        totalSoLuong += soLuong

        for (i in dsTP) {
            if (position == i.position) {
                totalTongTien += (ds[position].giaMon + i.loatTp * 5000) * soLuong
            }
        }
        binding.txtSoLuongTrongGioHang.text = "$totalSoLuong"
        binding.btnTrangThanhToan.text =
            "Trang thanh Toán . " + numberFormat(totalTongTien.toDouble())

    }


    private fun SearchView() {
        binding.timMonAn.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // khi người dùng nhập vào searchview
                val query = newText.trim()

                AnitemView()

                search(query)
                adapterTrasua.notifyDataSetChanged()
                if (query.isEmpty()) {
                    binding.txtTieuDeQuan.visibility = View.VISIBLE
                    binding.txtTieuDeHot.visibility = View.VISIBLE
                    binding.rvMonHot.visibility = View.VISIBLE
                    binding.constraintLayoutGiamGia.visibility = View.VISIBLE

                    binding.txtTieuDeMonAn.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                        topMargin = resources.getDimensionPixelSize(R.dimen.confirm_margin_top_txt)
                    }
                }
                return true
            }
        })
    }

    // khi nhập vào tìm kiếm seachview
    private fun AnitemView() {
        binding.txtTieuDeQuan.visibility = View.GONE
        binding.txtTieuDeHot.visibility = View.GONE
        binding.rvMonHot.visibility = View.GONE
        binding.constraintLayoutGiamGia.visibility = View.GONE

        binding.txtTieuDeMonAn.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            topMargin = resources.getDimensionPixelSize(R.dimen.fix_margin_top_txt)
        }
    }

    private fun search(query: String) {
        filteredDs.clear()

        if (query.isNotEmpty()) {
            filteredDs.addAll(ds.filter { traSua ->
                traSua.tenMon.contains(query, ignoreCase = true)
            })
        } else {
            filteredDs.addAll(ds)
        }

        adapterTrasua = RvAdapter_TraSua(filteredDs, this)
        binding.rvMonAn.adapter = adapterTrasua
        binding.rvMonAn.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        val dividerItemDecoration =
            DividerItemDecoration(binding.rvMonAn.context, LinearLayoutManager.VERTICAL)
        dividerItemDecoration.setDrawable(getDrawable(R.drawable.custom_ngan_rv)!!)
        binding.rvMonAn.addItemDecoration(dividerItemDecoration)
    }


    private fun adDS_MonHot() {
        ds_mh.add(MonHot(R.drawable.trasuahot))
        ds_mh.add(MonHot(R.drawable.trasuachanchauthachluu))
        ds_mh.add(MonHot(R.drawable.suachuavietquat))

    }

    private fun addDS_Tra_Sua() {
        // add trà sữa vào danh sách
        ds.add(
            TraSua(
                R.drawable.trasuachanchauduongden,
                "Trà sữa trân châu hoàng gia  ",
                20000,
                0,
                -1
            )
        )
        ds.add(TraSua(R.drawable.trasuachanchauthachzaizai, "Trà sữa panđa", 20000, 0, -1))
        ds.add(TraSua(R.drawable.trasuahot, "Trà sữa 3 anh em", 20000, 0, 0))
        ds.add(TraSua(R.drawable.trasuahot, "Trà sữa hoàng gia kem phomai", 20000, 0, -1))
        ds.add(TraSua(R.drawable.trasuachanchauthachluu, "Trà sữa thạch lựu", 20000, 0, -1))
        ds.add(TraSua(R.drawable.trasua3loai, "Trà sữa trân châu + milo", 20000, 0, -1))
        ds.add(TraSua(R.drawable.trasua3loai, "Trà sữa trân châu + soda", 20000, 0, -1))
        ds.add(TraSua(R.drawable.trasua3loai, "Trà sữa chân châu + lô hội", 20000, 0, -1))
        ds.add(TraSua(R.drawable.trasuamattra, "Trà sữa chân châu + mattra", 20000, 0, -1))
        ds.add(TraSua(R.drawable.trasuadaumatra, "Kem tươi mattra", 20000, 0, -1))
        ds.add(TraSua(R.drawable.trasuadaumatra, "Trà sữa chân châu vải", 20000, 0, -1))
        ds.add(TraSua(R.drawable.trasuaduahau, "Trà sữa chân châu dưa hấu", 20000, 0, -1))
        ds.add(TraSua(R.drawable.sodabacha, "Sođa bạc hà ", 15000, 0, -1))
        ds.add(TraSua(R.drawable.sodavietquat, "Sođa việt quất ", 15000, 0, -1))
        ds.add(TraSua(R.drawable.suachuadautay, "sữa chua dâu tây ", 15000, 0, -1))
        ds.add(TraSua(R.drawable.suachuavietquat, "sữa chua việt quất ", 15000, 0, -1))

    }

    private fun addRV() {
        // rv món hot
        adapterMonhot = RvAdapter_MonHot(ds_mh)
        binding.rvMonHot.adapter = adapterMonhot
        binding.rvMonHot.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvMonHot)


        // rv trà sữa
        adapterTrasua = RvAdapter_TraSua(ds, this)
        binding.rvMonAn.adapter = adapterTrasua
        binding.rvMonAn.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        val dividerItemDecoration =
            DividerItemDecoration(binding.rvMonAn.context, LinearLayoutManager.VERTICAL)
        dividerItemDecoration.setDrawable(getDrawable(R.drawable.custom_ngan_rv)!!)
        binding.rvMonAn.addItemDecoration(dividerItemDecoration)
    }


    // sử dụng numberFormat đặt định dạng hàng nghìn, triệu là "." sau thập phân là ","
    fun numberFormat(a: Double): String {
        val numberFormat = NumberFormat.getInstance()
        val formattedNumber = numberFormat.format(a)
        return formattedNumber.toString()
    }

    override fun onItemClickTang(position: Int) {
        when (ds[position].soLuong) {
            0 -> {
                binding.nestedScrollView.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                    bottomMargin =
                        resources.getDimensionPixelSize(R.dimen.margin_bottom_nested_scroll_view)
                }
                soLuong = 1
                showTopPing(position)
            }
            else -> {
                ds[position].soLuong += 1
                adapterTrasua.notifyDataSetChanged()
                // Cộng dồn số lượng cho từng mục trong danh sách
                totalSoLuong += 1
                for (i in dsTP) {
                    if (position == i.position) {
                        totalTongTien += (ds[position].giaMon + i.loatTp * 5000)
                    }
                }
                binding.txtSoLuongTrongGioHang.text = "$totalSoLuong"
                binding.btnTrangThanhToan.text =
                    "Trang thanh Toán . " + numberFormat(totalTongTien.toDouble())

            }

        }

    }

    override fun onItemClickGiam(position: Int) {
        // Cộng dồn số lượng cho từng mục trong danh sách
        totalSoLuong -= 1
        for (i in dsTP) {
            if (position == i.position) {
                totalTongTien -= (ds[position].giaMon + i.loatTp * 5000)
            }
        }

        binding.txtSoLuongTrongGioHang.text = "$totalSoLuong"
        binding.btnTrangThanhToan.text =
            "Trang thanh Toán . " + numberFormat(totalTongTien.toDouble())

        ds[position].soLuong -= 1
        adapterTrasua.notifyDataSetChanged()

        if (totalSoLuong == 0) {
            binding.nestedScrollView.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                bottomMargin =
                    resources.getDimensionPixelSize(R.dimen.fix_margin_bottom_nested_scroll_view)
            }
            binding.cvGioHang.visibility = View.GONE
        }


    }


    private fun showTopPing(position: Int) {

        binding.nestedScrollView.visibility = View.GONE
        binding.constraintTopPing.visibility = View.VISIBLE
        binding.txtSoLuongTP.text = "$soLuong"
        binding.imgTpGiam.setBackgroundColor(Color.parseColor("#E3DAD5"))


        binding.imgTpAnhMon.setImageResource(ds[position].image)

        // Kiểm tra và đặt lựa chọn mặc định cho RadioButton
        binding.rdTpSizeM.isChecked = false
        binding.rdTpSizeL.isChecked = false

        var clickRdM: View.OnClickListener? = null
        var clickRdL: View.OnClickListener? = null
        clickRdM = View.OnClickListener {
            if (binding.rdTpSizeM.isChecked) {
                binding.rdTpSizeM.isChecked = true
                binding.rdTpSizeL.isChecked = false
            }
        }
        binding.lnM.setOnClickListener(clickRdM)
        binding.rdTpSizeM.setOnClickListener(clickRdM)


        clickRdL = View.OnClickListener {
            if (binding.rdTpSizeL.isChecked) {
                binding.rdTpSizeL.isChecked = true
                binding.rdTpSizeM.isChecked = false
            }
        }

        binding.lnL.setOnClickListener(clickRdL)
        binding.rdTpSizeL.setOnClickListener(clickRdL)


        binding.imgTpTang.setOnClickListener {
            soLuong += 1
            binding.txtSoLuongTP.text = "$soLuong"
            if (soLuong == 2) {
                binding.imgTpGiam.setBackgroundColor(Color.parseColor("#D2374A"))
            }
        }
        binding.imgTpGiam.setOnClickListener {
            soLuong -= 1
            binding.txtSoLuongTP.text = "$soLuong"
            if (soLuong <= 1) {
                binding.imgTpGiam.setBackgroundColor(Color.parseColor("#E3DAD5"))
                binding.txtSoLuongTP.text = "1"
                soLuong = 1
            }
        }

        // Sự kiện khi người dùng nhấn nút "Chọn topping"
        binding.btnTpThem.setOnClickListener {
            if (binding.rdTpSizeM.isChecked) {
                loatTP = 0
                loatSoluong(position, soLuong, loatTP)
                binding.nestedScrollView.visibility = View.VISIBLE
                binding.constraintTopPing.visibility = View.GONE
                binding.cvGioHang.visibility = View.VISIBLE

            } else if (binding.rdTpSizeL.isChecked) {
                loatTP = 1
                loatSoluong(position, soLuong, loatTP)
                binding.nestedScrollView.visibility = View.VISIBLE
                binding.constraintTopPing.visibility = View.GONE
                binding.cvGioHang.visibility = View.VISIBLE

            } else {
                // Nếu không có lựa chọn nào được chọn
                Toast.makeText(this, "Vui lòng chọn kích cỡ", Toast.LENGTH_SHORT).show()
            }
        }
    }


}

