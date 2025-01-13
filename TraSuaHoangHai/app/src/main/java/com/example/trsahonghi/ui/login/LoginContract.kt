package com.example.trsahonghi.ui.login

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.base.AppBehaviorOnServiceError
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView

interface LoginContract {
    interface View : BaseView, AppBehaviorOnServiceError {
        fun showDiaLogInValid(message: String)
        fun getStringRes(id: Int): String
        fun getViewContext(): Context?
        fun loginSuccessful()
        fun openFragment(fragment: Fragment)
    }

    interface Presenter : BasePresenter {
        fun login()
        fun loginToken()
        fun account(): MutableLiveData<String>
        fun password(): MutableLiveData<String>
    }

}