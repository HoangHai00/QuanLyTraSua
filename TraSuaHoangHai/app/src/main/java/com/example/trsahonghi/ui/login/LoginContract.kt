package com.example.trsahonghi.ui.login

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.base.AppBehaviorOnServiceError
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView

interface LoginContract {
    interface View : BaseView, AppBehaviorOnServiceError {
        fun loginSuccessful()
        fun loginFailed()
        fun openFragment(fragment: Fragment)
        fun errorFirebase()
    }

    interface Presenter : BasePresenter {
        fun login()

        fun account(): MutableLiveData<String>
        fun password(): MutableLiveData<String>
    }

}