package com.example.trsahonghi.Admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trsahonghi.databinding.ActivityAdminBinding


class ActivityAdmin : AppCompatActivity() {
    private lateinit var binding: ActivityAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.lnDSDonHang.setOnClickListener {
            var intent = Intent(this, ActivityListDonHang::class.java)
            startActivity(intent)
        }
        binding.lnThongKe.setOnClickListener {
            var intent = Intent(this, ActivityThongKe::class.java)
            startActivity(intent)
        }
        binding.imgbtnBack.setOnClickListener {
            finish()
        }
    }
}