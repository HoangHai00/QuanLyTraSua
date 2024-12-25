package com.example.trsahonghi.ui.register.user

import TokenManager
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.R
import com.example.trsahonghi.api.repository.account.AccountRepository
import com.example.trsahonghi.base.CommonPresenter
import com.example.trsahonghi.util.StringUtils
import com.google.firebase.database.FirebaseDatabase

class RegisterPresenter(
    private val view: RegisterContract.View,
    private val accountRepository: AccountRepository
) : CommonPresenter(view, view), RegisterContract.Presenter {

    private var _fullName = MutableLiveData("")
    private var _account = MutableLiveData("")
    private var _passWord = MutableLiveData("")
    private var _date = MutableLiveData("")

    override fun fullName() = _fullName
    override fun account() = _account
    override fun password() = _passWord
    override fun date() = _date
    override fun setDate(date: String) {
        _date.value = date
    }

    override fun register() {
        val validationResult = validateInputs()
        if (validationResult != null) {
            view.showDiaLogInValid(validationResult)
            return
        }

        baseCallApi(accountRepository.register(
            _account.value,
            _fullName.value,
            _passWord.value,
            _date.value,
            "User"
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
                view.registerSuccessful()
            },
            onError = {

            })
    }

    private fun validateInputs(): String? {
        return when {
            !StringUtils.isValidLength(_fullName.value.orEmpty()) -> view.getStringRes(R.string.in_valid_full_name)
            !StringUtils.isValidPhoneNumber(_account.value.orEmpty()) -> view.getStringRes(R.string.in_valid_account)
            !StringUtils.isValidLength(_passWord.value.orEmpty()) -> view.getStringRes(R.string.in_valid_pass_word)
            _date.value.isNullOrEmpty() -> view.getStringRes(R.string.in_valid_date)
            else -> null
        }
    }
}
