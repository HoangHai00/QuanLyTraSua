package com.example.trsahonghi.ui.register.user

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.base.AppBehaviorOnServiceError
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView

class RegisterContract {
    interface View : BaseView, AppBehaviorOnServiceError {
        fun registerSuccessful()
        fun openFragment(fragment: Fragment)
        fun showDiaLogInValid(message: String)
        fun getStringRes(id: Int): String
        fun onBackClicked()

    }

    interface Presenter : BasePresenter {
        fun register()
        fun fullName(): MutableLiveData<String>
        fun account(): MutableLiveData<String>
        fun password(): MutableLiveData<String>
        fun rePassword(): MutableLiveData<String>
    }
}