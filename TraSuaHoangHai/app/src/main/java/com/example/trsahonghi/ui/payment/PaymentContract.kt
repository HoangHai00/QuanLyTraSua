package com.example.trsahonghi.ui.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.api.model.BubbleTea
import com.example.trsahonghi.base.AppBehaviorOnServiceError
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView

interface PaymentContract {
    interface View : BaseView, AppBehaviorOnServiceError {
        fun getStringRes(id: Int): String
        fun sendListFoodBroadcast(list: List<BubbleTea>)

    }

    interface Presenter : BasePresenter {
        fun listFood(): LiveData<List<BubbleTea>>
        fun isQrSelected(): MutableLiveData<Boolean>
        fun setIsQrSelected(value: Boolean)
        fun removeBubbleTea(position: Int)
        fun submitOrder()

        fun address(): LiveData<String>
        fun placeOrder(): LiveData<String>
        fun setPlaceOrder(value: String)
        fun info(): LiveData<String>
        fun getInfo()
        fun totalAmount(): LiveData<String>
        fun shippingFee(): LiveData<String>
        fun discountFee(): LiveData<String>
        fun totalItemAmount(): LiveData<String>
        fun setTotalItemAmount()

    }
}