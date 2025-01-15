package com.example.trsahonghi.ui.home.admin.orderlist

import com.example.trsahonghi.R
import com.example.trsahonghi.base.BaseDataBindFragment
import com.example.trsahonghi.databinding.FragmentOrderListBinding

class OrderListFragment :
    BaseDataBindFragment<FragmentOrderListBinding, OrderListContract.Presenter>(),
    OrderListContract.View {
    override fun getLayoutId() = R.layout.fragment_order_list
    override fun initView() {
        mBinding.apply {

        }
    }

    override fun initData() {
        TODO("Not yet implemented")
    }
}