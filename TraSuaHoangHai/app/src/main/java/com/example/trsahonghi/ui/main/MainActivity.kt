package com.example.trsahonghi.ui.main

import com.example.trsahonghi.base.BaseDataBindActivity
import com.example.trsahonghi.databinding.ActivityMainBinding
import com.example.trsahonghi.R
import com.example.trsahonghi.ui.home.homegroup.HomeGroupFragment
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
        replaceFragment(HomeGroupFragment.newInstance(),R.id.flMain,false)
    }

    override fun initData() {

    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

}



