package com.example.trsahonghi.app.homegroup

import com.example.trsahonghi.R
import com.example.trsahonghi.base.BaseDataBindFragment
import com.example.trsahonghi.databinding.FragmentHomeBinding

class HomeGroupFragment :
    BaseDataBindFragment<FragmentHomeBinding, HomeGroupContract.presenter>(),
    HomeGroupContract.view {
        companion object{

        }
    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun initView() {

    }

    override fun initData() {
        mPresenter = HomeGroupPresenter(this)
    }
}