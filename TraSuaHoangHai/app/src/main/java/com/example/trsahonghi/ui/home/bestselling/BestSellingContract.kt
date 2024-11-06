package com.example.trsahonghi.ui.home.bestselling

import com.example.trsahonghi.base.AppBehaviorOnServiceError
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView

interface BestSellingContract {
    interface View: BaseView, AppBehaviorOnServiceError {

    }
    interface Presenter: BasePresenter {

    }
}