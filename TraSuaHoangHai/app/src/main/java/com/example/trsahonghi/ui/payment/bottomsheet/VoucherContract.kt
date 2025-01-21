package com.example.trsahonghi.ui.payment.bottomsheet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.api.model.Coupon
import com.example.trsahonghi.api.model.Selectable
import com.example.trsahonghi.base.AppBehaviorOnServiceError
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView

interface VoucherContract {

    interface View : BaseView, AppBehaviorOnServiceError {

    }

    interface Presenter : BasePresenter {
        fun changeInterest(pos: Int)
        fun getPrevPosition(): Int
        fun listCoupon(): MutableLiveData<MutableList<Selectable<Coupon>>>
        fun coupon(): LiveData<Coupon>
        fun setCoupon(value: Coupon)
    }
}