package com.example.trsahonghi.ui.register.user

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
    private var _rePassWord = MutableLiveData("")
    private val database = FirebaseDatabase.getInstance().getReference("AccountDatabase")

    override fun fullName() = _fullName
    override fun account() = _account
    override fun password() = _passWord
    override fun rePassword() = _rePassWord

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
            "2001-01-01",
            "User"
        ),
            onSuccess = { response ->

                view.registerSuccessful()
            },
            onError = {
                Log.e("AAA","")
            })
    }

    private fun validateInputs(): String? {
        return when {
            !StringUtils.isValidLength(_fullName.value.orEmpty()) -> view.getStringRes(R.string.in_valid_full_name)
            !StringUtils.isValidPhoneNumber(_account.value.orEmpty()) -> view.getStringRes(R.string.in_valid_account)
            !StringUtils.isValidLength(_passWord.value.orEmpty()) -> view.getStringRes(R.string.in_valid_pass_word)
            _passWord.value != _rePassWord.value -> view.getStringRes(R.string.in_valid_re_pass_word)
            else -> null
        }
    }
}
