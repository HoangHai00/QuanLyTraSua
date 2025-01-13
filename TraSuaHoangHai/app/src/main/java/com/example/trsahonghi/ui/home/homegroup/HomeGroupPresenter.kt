package com.example.trsahonghi.ui.home.homegroup

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.api.model.BubbleTea
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
    private val _listFood = MutableLiveData<List<BubbleTea>>()
    private val _totalAmount = MutableLiveData<String>("")
    private val _quantity = MutableLiveData<String>("")
    override fun totalAmount() = _totalAmount

    override fun quantity() = _quantity
    override fun listFood() = _listFood

    override fun setListFood(listFood: List<BubbleTea>) {
        _listFood.value = listFood
        calculateTotalAmount()
        updateQuantity()
    }

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

    fun calculateTotalAmount() {
        _totalAmount.value = _listFood.value?.sumOf {
            (it.price?.toDoubleOrNull() ?: 0.0) * (it.ingredientType?.quantity?.toDoubleOrNull()
                ?: 0.0)
        }?.toString() ?: "0"
    }

    fun updateQuantity() {
        _quantity.value = _listFood.value?.size?.toString() ?: "0"
    }

}