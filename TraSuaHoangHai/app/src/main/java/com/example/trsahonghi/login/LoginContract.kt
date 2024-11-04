package com.example.trsahonghi.login

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.base.AppBehaviorOnServiceError
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView

interface LoginContract {
    interface View : BaseView, AppBehaviorOnServiceError {
        fun getViewContext(): Context?
        fun loginSuccessful()
        fun loginFailed()
        fun errorFirebase()
    }

    interface Presenter : BasePresenter {
        fun login()

        fun account(): MutableLiveData<String>
        fun password(): MutableLiveData<String>
    }

}