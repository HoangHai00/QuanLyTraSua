package com.example.trsahonghi.ui.home.homegroup

import com.example.trsahonghi.R
import com.example.trsahonghi.base.BaseDataBindFragment
import com.example.trsahonghi.databinding.FragmentHomeBinding
import com.example.trsahonghi.ui.home.homegroup.adapter.FragmentHomeAdapter

class HomeGroupFragment :
    BaseDataBindFragment<FragmentHomeBinding, HomeGroupContract.Presenter>(),
    HomeGroupContract.View {
    companion object {
        fun newInstance() = HomeGroupFragment()
    }
    private val adapter by lazy {
        FragmentHomeAdapter(getBaseActivity())
    }

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun initView() {
        mBinding?.apply {
            rvHomeGroup.adapter = adapter
        }

    }

    override fun initData() {
        mPresenter = HomeGroupPresenter(this)
    }
}