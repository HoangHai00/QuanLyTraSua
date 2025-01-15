package com.example.trsahonghi.ui.home.admin.orderlist

import com.example.trsahonghi.base.AppBehaviorOnServiceError
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView

interface OrderListContract {
    interface View : BaseView, AppBehaviorOnServiceError {

    }

    interface Presenter : BasePresenter {

    }
}