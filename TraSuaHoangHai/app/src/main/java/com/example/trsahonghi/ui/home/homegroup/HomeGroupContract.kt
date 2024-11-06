package com.example.trsahonghi.ui.home.homegroup

import com.example.trsahonghi.base.AppBehaviorOnServiceError
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView

interface HomeGroupContract {
    interface View : BaseView,AppBehaviorOnServiceError {

    }

    interface Presenter : BasePresenter {

    }
}