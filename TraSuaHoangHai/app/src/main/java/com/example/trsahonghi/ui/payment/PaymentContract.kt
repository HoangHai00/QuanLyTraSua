package com.example.trsahonghi.ui.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.api.model.BubbleTea
import com.example.trsahonghi.base.AppBehaviorOnServiceError
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView

interface PaymentContract {
    interface View : BaseView, AppBehaviorOnServiceError {

    }

    interface Presenter : BasePresenter {
        fun listFood() : LiveData<List<BubbleTea>>
        fun isQrSelected(): MutableLiveData<Boolean>
        fun setIsQrSelected(value: Boolean)
        fun removeBubbleTea(position: Int)

    }
}