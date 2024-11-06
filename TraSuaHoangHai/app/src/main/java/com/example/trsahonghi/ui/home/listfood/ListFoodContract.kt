package com.example.trsahonghi.ui.home.listfood

import com.example.trsahonghi.base.AppBehaviorOnServiceError
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView

interface ListFoodContract {
    interface View : BaseView, AppBehaviorOnServiceError {

    }

    interface Presenter : BasePresenter {

    }
}
