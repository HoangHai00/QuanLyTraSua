package com.example.trsahonghi.ui.login.user

import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.R
import com.example.trsahonghi.api.Config
import com.example.trsahonghi.api.repository.account.AccountRepository
import com.example.trsahonghi.base.CommonPresenter
import com.example.trsahonghi.util.Constants
import com.example.trsahonghi.util.SharedPreferencesUtils

class LoginPresenter(
    private val view: LoginContract.View,
    private val accountRepository: AccountRepository
) : CommonPresenter(view, view), LoginContract.Presenter {
    private var _account = MutableLiveData<String>("")
    private var _passWord = MutableLiveData<String>("")

    override fun account() = _account
    override fun password() = _passWord

    // Đăng nhập bằng tài khoản
    override fun login() {
        val validationResult = validateInputs()
        if (validationResult != null) {
            view.showDiaLogInValid(validationResult)
            return
        }
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

                _account.value?.let {
                    SharedPreferencesUtils.put(
                        Constants.KEY.KEY_PHONE_NUMBER,
                        it
                    )
                }
                view.loginSuccessful()
            },
            onError = {

            }
        )
    }

    //Đăng nhập bằng token
    override fun loginToken() {
        val phoneNumber = SharedPreferencesUtils.get(Constants.KEY.KEY_PHONE_NUMBER, "")
        val token = view.getViewContext()?.let { TokenManager.getToken(it) }

        if (token.isNullOrEmpty()) return

        baseCallApi(
            accountRepository.loginToken(),
            onSuccess = { response ->
                view.getViewContext()?.let {
                    response?.data?.let { it1 ->
                        TokenManager.saveToken(
                            it,
                            it1
                        )
                    }
                }
                if (phoneNumber == Config.ACCOUNT_ADMIN) {
                    view.loginAdminSuccessful()
                } else {
                    view.loginSuccessful()
                }

            },
            onError = {

            }
        )
    }

    private fun validateInputs(): String? {
        return when {
            _account.value.isNullOrEmpty() -> view.getStringRes(R.string.login_account_null)
            _passWord.value.isNullOrEmpty() -> view.getStringRes(R.string.login_account_null)
            else -> null
        }
    }

}