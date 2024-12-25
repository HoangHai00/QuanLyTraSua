package com.example.trsahonghi.ui.myapplication

import TokenManager
import android.app.Application
import android.content.Context

class MyApplication : Application() {

    companion object {
        lateinit var context: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        context = this // Gán context của ứng dụng
        TokenManager.init(this) // Khởi tạo TokenManager
    }
}

