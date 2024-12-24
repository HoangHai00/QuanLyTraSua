package com.example.trsahonghi.ui.myapplication

import TokenManager
import android.app.Application

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Khởi tạo SecureStorage
        TokenManager.init(this)
    }
}
