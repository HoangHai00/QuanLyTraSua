package com.example.trsahonghi.ui.app.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.ui.manage.ActivityAdmin
import com.example.trsahonghi.ui.app.ActivityTrungTam
import com.example.trsahonghi.base.BaseDataBindActivity
import com.example.trsahonghi.databinding.ActivityMainBinding
import com.example.trsahonghi.api.model.User
import com.google.firebase.database.*
import com.example.trsahonghi.R
import com.example.trsahonghi.ui.login.LoginFragment


class MainActivity : BaseDataBindActivity<ActivityMainBinding, MainContract.Presenter>(),
    MainContract.View {

    companion object {
        private lateinit var instance: MainActivity

        fun self(): MainActivity {
            return instance
        }
    }



    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initView() {

    }

    override fun initData() {
        replaceFragment(LoginFragment.newInstance(),R.id.fl_main)
    }


}



