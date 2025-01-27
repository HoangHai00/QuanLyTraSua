package com.example.trsahonghi.ui.login.admin

import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.R
import com.example.trsahonghi.api.Config
import com.example.trsahonghi.api.repository.account.AccountRepository
import com.example.trsahonghi.base.CommonPresenter
import com.example.trsahonghi.util.Constants
import com.example.trsahonghi.util.SharedPreferencesUtils

class LoginAdminPresenter(
    private val view: LoginAdminContract.View,
    private val accountRepository: AccountRepository
) : CommonPresenter(view, view), LoginAdminContract.Presenter {
    private val _account = MutableLiveData<String>("")
    private val _passWord = MutableLiveData<String>("")

    override fun account() = _account
    override fun passWord() = _passWord
    override fun loginAdmin() {
        val validationResult = validateInputs()
        if (validationResult != null) {
            view.showDiaLogInValid(validationResult)
            return
        }
        if (_account.value == Config.ACCOUNT_ADMIN) {
            baseCallApi(
                accountRepository.login(
                    _account.value,
                    _passWord.value
                ),
                onSuccess = { response ->
                    view.getViewContext()?.let {
                        response?.data?.let { it1 ->
                            TokenManager.saveToken(
                                it,
                                it1
                            )
                        }
                    }

                    SharedPreferencesUtils.put(Constants.KEY.KEY_PHONE_NUMBER, _account.value!!)
                    view.loginAdminSuccessful()
                },
                onError = {

                }
            )
        } else {
            view.showDiaLogInValid(
                view.getStringRes(
                    R.string.login_failed
                )
            )
        }


    }


    private fun validateInputs(): String? {
        return when {
            _account.value.isNullOrEmpty() -> view.getStringRes(R.string.login_account_null)
            _passWord.value.isNullOrEmpty() -> view.getStringRes(R.string.login_account_null)
            else -> null
        }
    }
}