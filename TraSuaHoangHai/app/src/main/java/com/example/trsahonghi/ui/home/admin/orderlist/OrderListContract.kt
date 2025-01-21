package com.example.trsahonghi.ui.home.admin.orderlist

import androidx.lifecycle.LiveData
import com.example.trsahonghi.api.model.response.BillAdminResponse
import com.example.trsahonghi.base.AppBehaviorOnServiceError
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView

interface OrderListContract {
    interface View : BaseView, AppBehaviorOnServiceError {

    }

    interface Presenter : BasePresenter {
        fun getListOrderAdmin(status: Int)
        fun listOrder(): LiveData<List<BillAdminResponse>>
    }
}