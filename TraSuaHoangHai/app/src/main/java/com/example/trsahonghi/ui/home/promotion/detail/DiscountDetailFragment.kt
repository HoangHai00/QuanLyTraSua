package com.example.trsahonghi.ui.home.promotion.detail

import androidx.activity.OnBackPressedCallback
import com.example.trsahonghi.R
import com.example.trsahonghi.api.repository.food.FoodRepositoryImpl
import com.example.trsahonghi.base.BaseDataBindFragment
import com.example.trsahonghi.databinding.FragmentDiscountDetailBinding
import com.example.trsahonghi.ui.home.promotion.adapter.DiscountAdapter

class DiscountDetailFragment :
    BaseDataBindFragment<FragmentDiscountDetailBinding, DiscountDetailContract.Presenter>(),
    DiscountDetailContract.View {
    companion object {
        fun newInstance() = DiscountDetailFragment()
    }

    private val adapter: DiscountAdapter by lazy {
        DiscountAdapter()
    }

    override fun getLayoutId() = R.layout.fragment_discount_detail

    override fun initView() {
        mBinding?.apply {
            rvVoucher.adapter = adapter
            toolbar.setOnBackClickListener {
                onBackClick()
            }

            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        onBackClick()
                    }
                })

        }
    }

    override fun initData() {
        mPresenter = DiscountDetailPresenter(
            this,
            FoodRepositoryImpl(),
        ).apply {
            mBinding?.presenter = this
            getListVoucher()

            listVoucher().observe(viewLifecycleOwner) {
                adapter.setData(it)
            }
        }
    }

    private fun onBackClick() {
        getBaseActivity().onBackFragment()
    }

    override fun getStringRes(id: Int): String {
        return getString(id)
    }
}