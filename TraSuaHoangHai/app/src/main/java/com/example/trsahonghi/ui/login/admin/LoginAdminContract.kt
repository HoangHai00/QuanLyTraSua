package com.example.trsahonghi.ui.login.admin

import com.example.trsahonghi.base.AppBehaviorOnServiceError
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView

interface LoginAdminContract {
    interface View: BaseView, AppBehaviorOnServiceError {

    }
    interface Presenter: BasePresenter {

    }
}