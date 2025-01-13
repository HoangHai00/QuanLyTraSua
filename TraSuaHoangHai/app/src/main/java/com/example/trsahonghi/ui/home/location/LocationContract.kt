package com.example.trsahonghi.ui.home.location

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import com.example.trsahonghi.base.AppBehaviorOnServiceError
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView

class LocationContract {
    interface View : BaseView, AppBehaviorOnServiceError {
        fun onBackClicked()
        fun showError(message: String)
        fun requestLocationPermission()
        fun getStringRes(id: Int, message: String): String
        fun getViewContext(): Context?

    }

    interface Presenter : BasePresenter {
        fun address(): LiveData<String>
        fun setAddress(address: String)
        fun getCurrentLocationAndAddress()
        fun onPermissionGranted()
    }


}