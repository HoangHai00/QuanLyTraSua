package com.example.trsahonghi.User

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.databinding.ActivityThongTinBinding
import com.example.trsahonghi.model.User
import com.google.firebase.database.*


class ActivityThongTin : AppCompatActivity() {
    private lateinit var binding: ActivityThongTinBinding
    private val dsLiveData: MutableLiveData<ArrayList<User>> = MutableLiveData()
    private lateinit var ds: ArrayList<User>
    private lateinit var userMain: User
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThongTinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbRef = FirebaseDatabase.getInstance().getReference("UserLogin")
        ds = arrayListOf<User>()

        // Lắng nghe sự kiện thay đổi dữ liệu trong dsLiveData
        dsLiveData.observe(this, { userList ->
            ds.clear()
            ds.addAll(userList)

            val bundle = intent.extras
            if (bundle != null) {
                val sdt = bundle.getString("SDT")
                for (user in ds) {
                    if (user.sdt == sdt) {
                        // Cập nhật giao diện với tên của người dùng
                        binding.txtName.text = user.tenTK
                        userMain = user
                        break
                    }
                }
            }
        })

        getData()
        clickBtn()
    }

    private fun clickBtn() {
        binding.linerDonHang.setOnClickListener {
            val bundle = intent.extras
            if (bundle != null) {
                val sdt = bundle.getString("SDT")
                var next = Intent(this, ActivityUserList::class.java)
                var bundleTT = Bundle()
                bundleTT.putString("SDT", sdt)
                next.putExtras(bundleTT)
                startActivity(next)
            }
        }
        binding.imgbtnBack.setOnClickListener {
            finish()
        }

        binding.linerDoiMatKhau.setOnClickListener {
            binding.txtTitle.text = "Đổi Mật Khẩu"
            binding.constraintThongTin.visibility = View.GONE
            binding.constraintMatKhau.visibility = View.VISIBLE

            binding.btnDoiMatKhau.setOnClickListener {
                doiMatKhau()
            }

            binding.btnHuy.setOnClickListener {
                layoutThongTin()

            }

        }
    }

    private fun layoutThongTin() {
        binding.txtTitle.text = "Thông tin tài khoản"
        binding.constraintThongTin.visibility = View.VISIBLE
        binding.constraintMatKhau.visibility = View.GONE
        binding.edtMatKhauCu.setText("")
        binding.edtMatKhauMoi.setText("")
        binding.edtXNMatKhauMoi.setText("")
    }

    private fun doiMatKhau() {
        val matKhauCu = binding.edtMatKhauCu.text.toString()
        val matKhauMoi = binding.edtMatKhauMoi.text.toString()
        val xacNhanMatKhau = binding.edtXNMatKhauMoi.text.toString()

        if (matKhauCu != userMain.matKhau) {
            Toast.makeText(this, "Mật khẩu không đúng!", Toast.LENGTH_SHORT).show()
        } else if (!isValidMatKhauMoi(matKhauMoi)) {
            Toast.makeText(
                this,
                "Mật khẩu phải có từ 12 đến 16 kí tự và không chứa dấu cách!",
                Toast.LENGTH_SHORT
            ).show()
        } else if (!isMatchingMatKhauMoi(matKhauMoi, xacNhanMatKhau)) {
            Toast.makeText(this, "Xác nhận mật khẩu không trùng khớp!", Toast.LENGTH_SHORT)
                .show()
        } else {
            // Cập nhật mật khẩu mới vào đối tượng userMain
            userMain.matKhau = matKhauMoi

            // Cập nhật dữ liệu lên Firebase
            val userRef = dbRef.child(userMain.id ?: "")

            val updates: MutableMap<String, Any> = HashMap()
            updates["matKhau"] = matKhauMoi

            userRef.updateChildren(updates)
                .addOnSuccessListener {
                    Toast.makeText(this, "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show()

                    // Trở về giao diện thông tin tài khoản
                    layoutThongTin()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Có lỗi xảy ra khi cập nhật dữ liệu!", Toast.LENGTH_SHORT)
                        .show()
                }
        }
    }


    private fun getData() {
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userList = ArrayList<User>() // Tạo một danh sách mới
                if (snapshot.exists()) {
                    for (data in snapshot.children) {
                        val userData = data.getValue(User::class.java)
                        userData?.let {
                            userList.add(it)
                        }
                    }
                }
                dsLiveData.value = userList // Gán giá trị mới cho dsLiveData
            }

            override fun onCancelled(error: DatabaseError) {
                // Xử lý khi có lỗi truy vấn dữ liệu
            }
        })
    }

    // Hàm kiểm tra mật khẩu
    fun isValidMatKhauMoi(matKhauMoi: String): Boolean {
        return matKhauMoi.length in 6..32 && !matKhauMoi.contains(" ")
    }

    // Hàm kiểm tra xác nhận mật khẩu
    fun isMatchingMatKhauMoi(matKhauMoi: String, xacNhanMatKhauMoi: String): Boolean {
        return matKhauMoi == xacNhanMatKhauMoi
    }

}