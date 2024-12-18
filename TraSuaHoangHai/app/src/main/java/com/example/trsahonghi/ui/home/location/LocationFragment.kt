package com.example.trsahonghi.ui.home.location

import com.example.trsahonghi.R
import com.example.trsahonghi.base.BaseDataBindFragment
import com.example.trsahonghi.databinding.FragmentLocationBinding

class LocationFragment :
    BaseDataBindFragment<FragmentLocationBinding, LocationContract.Presenter>(),
    LocationContract.View {
    companion object {
        fun newInstance() = LocationFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_location

    override fun initView() {
        mBinding.apply {

        }

    }

    override fun initData() {

    }

    override fun onBackClicked() {
        getBaseActivity().onBackFragment()
    }
}