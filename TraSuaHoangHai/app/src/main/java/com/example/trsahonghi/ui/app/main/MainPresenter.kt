package com.example.trsahonghi.ui.app.main

import com.example.trsahonghi.base.CommonPresenter

class MainPresenter(
    private val view: MainContract.View
) : CommonPresenter(view, view), MainContract.Presenter {
}