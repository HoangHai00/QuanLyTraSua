package com.example.trsahonghi.ui.home.location.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.base.AppBehaviorOnServiceError
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView

interface AddressContract {
    interface View : BaseView, AppBehaviorOnServiceError {
        fun onBackClick()
        fun getStringRes(id: Int): String

    }

    interface Presenter : BasePresenter {
        fun streetName(): MutableLiveData<String>
        fun chooseLocation(): MutableLiveData<String>
        fun address(): LiveData<String>
        fun setStreetName(value: String)
        fun updateAddress()
        fun setChooseLocation(value: String)

    }
}