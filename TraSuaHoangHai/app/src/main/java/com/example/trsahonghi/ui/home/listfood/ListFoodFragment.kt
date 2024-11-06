package com.example.trsahonghi.ui.home.listfood

import com.example.trsahonghi.R
import com.example.trsahonghi.base.BaseDataBindFragment
import com.example.trsahonghi.databinding.FragmentListFoodBinding

class ListFoodFragment:
BaseDataBindFragment<FragmentListFoodBinding,ListFoodContract.Presenter>(),
ListFoodContract.View{
    companion object{
        fun newInstance() = ListFoodFragment()
    }
    override fun getLayoutId(): Int = R.layout.fragment_list_food

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun initData() {
        TODO("Not yet implemented")
    }

}