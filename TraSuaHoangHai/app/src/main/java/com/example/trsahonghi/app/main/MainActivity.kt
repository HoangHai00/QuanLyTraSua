package com.example.trsahonghi.app.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.manage.ActivityAdmin
import com.example.trsahonghi.app.ActivityTrungTam
import com.example.trsahonghi.base.BaseDataBindActivity
import com.example.trsahonghi.databinding.ActivityMainBinding
import com.example.trsahonghi.model.User
import com.google.firebase.database.*
import com.example.trsahonghi.R
import com.example.trsahonghi.login.LoginFragment


class MainActivity : BaseDataBindActivity<ActivityMainBinding, MainContract.Presenter>(),
    MainContract.view {

    companion object {
        private lateinit var instance: MainActivity

        fun self(): MainActivity {
            return instance
        }
    }

    private lateinit var ds: ArrayList<User>
    private lateinit var dbRef: DatabaseReference
    private lateinit var binding: ActivityMainBinding
    private val dsLiveData: MutableLiveData<ArrayList<User>> = MutableLiveData()


    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initView() {
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
    }

    override fun initData() {
        replaceFragment(LoginFragment.newInstance(),R.id.fl_main)
    }

    private fun DangNhap() {
        binding.btnDangNhap.setOnClickListener {
            var sdt = binding.edtSDT.text.toString().trim()
            var matKhau = binding.edtMatKhau.text.toString().trim()

            if (sdt == "0000" && matKhau == "admin") {
                var intent2 = Intent(this, ActivityAdmin::class.java)
                startActivity(intent2)
                Toast.makeText(this, "Xin chào Admin!", Toast.LENGTH_SHORT).show()
                binding.edtSDT.setText("")
                binding.edtMatKhau.setText("")
            } else {

                var isLoginSuccessful = false
                for (user in ds) {
                    if (user.account == sdt && user.password == matKhau) {
                        // Tìm thấy trùng khớp
                        isLoginSuccessful = true
                        break
                    }
                }

                if (isLoginSuccessful) {
                    // Đăng nhập thành công
                    Toast.makeText(this@MainActivity, "Đăng nhập thành công!", Toast.LENGTH_SHORT)
                        .show()
                    // Thực hiện các hành động sau khi đăng nhập thành công
                    next(sdt)
                    binding.edtSDT.setText("")
                    binding.edtMatKhau.setText("")
                } else {
                    // Mật khẩu không trùng khớp hoặc số điện thoại không tồn tại trong danh sách
                    Toast.makeText(
                        this@MainActivity,
                        "Số điện thoại hoặc mật khẩu không đúng!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
    }

    private fun next(sdt: String) {
        var intent = Intent(this, ActivityTrungTam::class.java)
        var bundle = Bundle()
        bundle.putString("SDT", sdt)
        intent.putExtras(bundle)
        startActivity(intent)
    }


    // bật đăng ký tài khoản
    private fun DangKy() {
        binding.btnDKHuy.setOnClickListener {
            showRegistrationSuccessLayout()
        }

        binding.btnDangKy.setOnClickListener {
            binding.edtSDT.setText("")
            binding.edtMatKhau.setText("")
            binding.constraintLogin.visibility = View.GONE
            binding.constraintBtnLogin.visibility = View.GONE
            binding.constraintRegister.visibility = View.VISIBLE

            binding.btnDKXacNhan.setOnClickListener {
                val soDienThoai = binding.edtDKSDT.text.toString()
                var isLoginSuccessful = false
                for (user in ds) {
                    if (user.account == soDienThoai) {
                        // Tìm thấy trùng khớp
                        isLoginSuccessful = true
                        break
                    }
                }

                if (isLoginSuccessful == false) {
                    layoutDangKy()
                } else {
                    //  số điện thoại đã tồn tại trong danh sách
                    Toast.makeText(
                        this@MainActivity,
                        "Số điện thoại đã tồn tại!",
                        Toast.LENGTH_SHORT
                    ).show()
                }


            }
        }
    }

    private fun layoutDangKy() {

        // Trong onClickListener khi nhấn nút Đăng ký:
        val tenTaiKhoan = binding.edtDKTenTaiKhoan.text.toString()
        val soDienThoai = binding.edtDKSDT.text.toString()
        val matKhau = binding.edtDKMatKhau.text.toString()
        val xacNhanMatKhau = binding.edtDKXacNhanMK.text.toString()


        if (!isValidTenTaiKhoan(tenTaiKhoan)) {
            Toast.makeText(this, "Tên đăng nhập phải có ít nhất 6 kí tự!", Toast.LENGTH_SHORT)
                .show()
        } else if (!isValidSoDienThoai(soDienThoai)) {
            Toast.makeText(this, "Số điện thoại không hợp lệ!", Toast.LENGTH_SHORT).show()
        } else if (!isValidMatKhau(matKhau)) {
            Toast.makeText(
                this,
                "Mật khẩu phải có từ 12 đến 16 kí tự và không chứa dấu cách!",
                Toast.LENGTH_SHORT
            )
                .show()
        } else if (!isMatchingMatKhau(matKhau, xacNhanMatKhau)) {
            Toast.makeText(this, "Xác nhận mật khẩu không trùng khớp!", Toast.LENGTH_SHORT)
                .show()
        } else {
            showRegistrationSuccessLayout()
            dkThanhCong()
        }


    }

    private fun dkThanhCong() {
        // Lưu thông tin người dùng vào Firestore
        val tenTaiKhoan = binding.edtDKTenTaiKhoan.text.toString()
        val soDienThoai = binding.edtDKSDT.text.toString()
        val matKhau = binding.edtDKMatKhau.text.toString()

        val userID = dbRef.push().key!!
        val userLogin = User(userID, tenTaiKhoan, soDienThoai, matKhau)

        dbRef.child(userID).setValue(userLogin)
            .addOnCompleteListener {
                Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show()
                binding.edtDKTenTaiKhoan.text = null
                binding.edtDKSDT.text = null
                binding.edtDKMatKhau.text = null
                binding.edtDKXacNhanMK.text = null
                ds.add(userLogin)
                dsLiveData.value = ds
            }
            .addOnFailureListener { err ->
                Toast.makeText(this, "Vui lòng kiểm tra kết nối mạng!", Toast.LENGTH_SHORT).show()
            }


    }

    private fun showRegistrationSuccessLayout() {
        binding.constraintRegister.visibility = View.GONE
        binding.constraintLogin.visibility = View.VISIBLE
        binding.constraintBtnLogin.visibility = View.VISIBLE
    }


    // Hàm kiểm tra tên đăng nhập
    fun isValidTenTaiKhoan(tenTaiKhoan: String): Boolean {
        return tenTaiKhoan.length >= 6
    }

    // Hàm kiểm tra số điện thoại hợp lệ
    // Hàm kiểm tra số điện thoại
    fun isValidSoDienThoai(soDienThoai: String): Boolean {
        val validCountryCodes = setOf("+84", "84", "0") // Ví dụ: Việt Nam và Hoa Kỳ

        // Kiểm tra số điện thoại có hợp lệ hay không
        return validCountryCodes.any { soDienThoai.startsWith(it) } &&
                soDienThoai.replace("[^0-9+]".toRegex(), "").length == 10
    }


    // Hàm kiểm tra mật khẩu
    fun isValidMatKhau(matKhau: String): Boolean {
        return matKhau.length in 6..32 && !matKhau.contains(" ")
    }

    // Hàm kiểm tra xác nhận mật khẩu
    fun isMatchingMatKhau(matKhau: String, xacNhanMatKhau: String): Boolean {
        return matKhau == xacNhanMatKhau
    }


}



