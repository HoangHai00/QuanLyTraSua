package com.example.trsahonghi.ui.home.location

import com.example.trsahonghi.base.AppBehaviorOnServiceError
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView

class LocationContract {
    interface View: BaseView, AppBehaviorOnServiceError {
        fun onBackClicked()

    }
    interface Presenter: BasePresenter {

    }
}