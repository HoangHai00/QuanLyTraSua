package com.example.trsahonghi.ui.payment.bottomsheet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.api.model.Coupon
import com.example.trsahonghi.api.model.Selectable
import com.example.trsahonghi.base.CommonPresenter

class VoucherPresenter(
    private val view: VoucherContract.View,
    list: List<Coupon>?
) : CommonPresenter(), VoucherContract.Presenter {
    private val _coupon = MutableLiveData<Coupon>()
    private val _listCoupon = MutableLiveData<MutableList<Selectable<Coupon>>>().apply {
        value = list?.mapIndexed { index, item ->
            Selectable(item).apply {
                selected = index == -1
            }
        }?.toMutableList()
    }

    private var _prevPosition = 0
    private var _selectedPosition = 0

    override fun changeInterest(pos: Int) {
        _prevPosition = _selectedPosition
        _selectedPosition = pos
        _listCoupon.value?.forEachIndexed { index, interest ->
            interest.selected = pos == index
        }
    }

    override fun getPrevPosition() = _prevPosition
    override fun listCoupon() = _listCoupon
    override fun coupon() = _coupon

    override fun setCoupon(value: Coupon) {
        _coupon.value = value
    }


}