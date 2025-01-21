package com.example.trsahonghi.ui.home.admin

import com.example.trsahonghi.base.AppBehaviorOnServiceError
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView

interface HomeAdminContract {
    interface View: BaseView, AppBehaviorOnServiceError {

    }
    interface Presenter: BasePresenter {

    }
}