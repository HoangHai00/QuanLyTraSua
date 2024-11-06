package com.example.trsahonghi.ui.home.search

import com.example.trsahonghi.R
import com.example.trsahonghi.base.BaseDataBindFragment
import com.example.trsahonghi.databinding.FragmentLocationBinding
import com.example.trsahonghi.databinding.FragmentSearchBinding

class SearchFragment :
    BaseDataBindFragment<FragmentSearchBinding, SearchContract.Presenter>(),
    SearchContract.View {
    companion object {
        fun newInstance() = SearchFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_search

    override fun initView() {
        mBinding.apply { }
    }

    override fun initData() {
        mPresenter = SearchPresenter(this)
    }

}