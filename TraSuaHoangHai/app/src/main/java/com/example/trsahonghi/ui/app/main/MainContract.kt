package com.example.trsahonghi.ui.app.main

import com.example.trsahonghi.base.AppBehaviorOnServiceError
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView

interface MainContract {
    interface View: BaseView,AppBehaviorOnServiceError{

    }
    interface Presenter:BasePresenter{

    }
}