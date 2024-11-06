package com.example.trsahonghi.ui.home.bestselling

import com.example.trsahonghi.R
import com.example.trsahonghi.base.BaseDataBindFragment
import com.example.trsahonghi.databinding.FragmentBestSellingBinding

class BestSellingFragment :
    BaseDataBindFragment<FragmentBestSellingBinding, BestSellingContract.Presenter>(),
    BestSellingContract.View {
        companion object{
            fun newInstance() = BestSellingFragment()
        }
    override fun getLayoutId(): Int = R.layout.fragment_best_selling

    override fun initView() {

    }

    override fun initData() {

    }


}