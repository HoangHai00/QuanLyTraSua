package com.example.trsahonghi.ui.home.search

import com.example.trsahonghi.base.CommonPresenter

class SearchPresenter(
    private val view: SearchContract.View
) : CommonPresenter(view, view), SearchContract.Presenter {
}