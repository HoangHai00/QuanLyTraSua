package com.example.trsahonghi.ui.home.promotion

import com.example.trsahonghi.R
import com.example.trsahonghi.api.repository.food.FoodRepositoryImpl
import com.example.trsahonghi.base.BaseDataBindFragment
import com.example.trsahonghi.databinding.FragmentDiscountBinding
import com.example.trsahonghi.ui.home.promotion.detail.DiscountDetailFragment

class DiscountFragment :
    BaseDataBindFragment<FragmentDiscountBinding, DiscountContract.Presenter>(),
    DiscountContract.View {
    companion object {
        fun newInstance() = DiscountFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_discount

    override fun initView() {
        mBinding?.apply {
            ctrlPromotion.setOnClickListener {
                getBaseActivity().addFragment(DiscountDetailFragment.newInstance(), R.id.flMain)

            }


        }
    }

    override fun initData() {
        mPresenter = DiscountPresenter(
            this,
            FoodRepositoryImpl()
        ).apply {
            mBinding?.presenter = this
            getListVoucher()
        }
    }

    override fun getStringRes(id: Int): String {
        return getString(id)
    }
}