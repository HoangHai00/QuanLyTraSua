package com.example.trsahonghi.ui.home.promotion

import com.example.trsahonghi.R
import com.example.trsahonghi.base.BaseDataBindFragment
import com.example.trsahonghi.databinding.FragmentPromotionBinding

class PromotionFragment :
    BaseDataBindFragment<FragmentPromotionBinding, PromotionContract.Presenter>(),
    PromotionContract.View {
    companion object {
        fun newInstance() = PromotionFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_promotion

    override fun initView() {
        mBinding.apply { }
    }

    override fun initData() {
        mPresenter = PromotionPresenter(this)
    }
}