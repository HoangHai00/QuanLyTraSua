package com.example.trsahonghi.ui.home.promotion.detail

import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.R
import com.example.trsahonghi.api.model.Coupon
import com.example.trsahonghi.api.repository.food.FoodRepository
import com.example.trsahonghi.base.CommonPresenter

class DiscountDetailPresenter(
    private val view: DiscountDetailContract.View,
    private val foodRepository: FoodRepository
) : CommonPresenter(view, view), DiscountDetailContract.Presenter {

    private val _listVoucher = MutableLiveData<List<Coupon>>()
    private val _coupon = MutableLiveData<String>()
    override fun getListVoucher() {
        baseCallApi(foodRepository.getListVoucher(),
            onSuccess = { response ->
                _listVoucher.value = response
                _coupon.value = _listVoucher.value?.firstOrNull()?.name
                    ?: view.getStringRes(R.string.no_discount_code)

            },
            onError = {

            })
    }

    override fun listVoucher() = _listVoucher

    override fun coupon() = _coupon

}