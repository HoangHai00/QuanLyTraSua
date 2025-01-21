package com.example.trsahonghi.ui.home.admin.orderlist.detail

import androidx.lifecycle.LiveData
import com.example.trsahonghi.api.model.BubbleTea
import com.example.trsahonghi.base.AppBehaviorOnServiceError
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView

interface OrderDetailContract {
    interface View : BaseView, AppBehaviorOnServiceError {
        fun confirmSuccess(message: String)
        fun getStringRes(id: Int): String
    }

    interface Presenter : BasePresenter {
        fun address(): LiveData<String>
        fun phoneNumber(): LiveData<String>
        fun totalAmount(): LiveData<String>
        fun confirmOrder(id: String)
        fun cancelOrder(id: String)
        fun listFood(): LiveData<List<BubbleTea>>
    }

}