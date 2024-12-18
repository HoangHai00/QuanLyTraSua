package com.example.trsahonghi.ui.home.location

import com.example.trsahonghi.base.CommonPresenter

class LocationPresenter(
    private val view: LocationContract.View
) : CommonPresenter(view, view), LocationContract.Presenter {
}