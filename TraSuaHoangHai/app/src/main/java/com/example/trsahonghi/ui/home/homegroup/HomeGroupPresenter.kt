package com.example.trsahonghi.ui.home.homegroup

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.base.CommonPresenter
import com.example.trsahonghi.ui.home.bestselling.BestSellingFragment
import com.example.trsahonghi.ui.home.listfood.ListFoodFragment
import com.example.trsahonghi.ui.home.location.LocationFragment
import com.example.trsahonghi.ui.home.promotion.PromotionFragment
import com.example.trsahonghi.ui.home.search.SearchFragment

class HomeGroupPresenter(
    private val view: HomeGroupContract.View
) : CommonPresenter(view, view), HomeGroupContract.Presenter {
    private val _listFragment = MutableLiveData<List<Fragment>>()
    private val _isCartEmpty = MutableLiveData<Boolean>(false)

    override fun listFragment() = _listFragment
    override fun isCartEmpty() = _isCartEmpty

    override fun setIsCartEmpty(boolean: Boolean) {
        _isCartEmpty.value = boolean
    }

    override fun getListFragmentHome() {
        _listFragment.value = listOf(
            LocationFragment.newInstance(),
            SearchFragment.newInstance(),
            BestSellingFragment.newInstance(),
            PromotionFragment.newInstance(),
            ListFoodFragment.newInstance()
        )
    }


}