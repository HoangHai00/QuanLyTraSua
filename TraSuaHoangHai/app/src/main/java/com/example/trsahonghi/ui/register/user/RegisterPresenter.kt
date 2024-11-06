package com.example.trsahonghi.ui.register.user

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.R
import com.example.trsahonghi.api.model.User
import com.example.trsahonghi.base.CommonPresenter
import com.example.trsahonghi.util.StringUtils
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RegisterPresenter(
    private val view: RegisterContract.View
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
        checkAccountExist()
    }

    private fun updateFirebase() {
        view.showLoading()
        val userId = database.push().key
        val user = User(
            userId,
            _fullName.value.orEmpty(),
            _account.value.orEmpty(),
            _passWord.value.orEmpty()
        )

        userId?.let {
            database.child(it).setValue(user)
                .addOnCompleteListener {
                    view.hideLoading()
                    view.registerSuccessful()
                }
                .addOnFailureListener {
                    view.hideLoading()
                    view.showDiaLogInValid(view.getStringRes(R.string.error_internet))
                }
        }
    }

    private fun checkAccountExist() {
        view.showLoading()
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                view.hideLoading()
                if (snapshot.exists()) {
                    val userList = snapshot.children.mapNotNull { it.getValue(User::class.java) }
                    if (userList.any { it.account == _account.value }) {
                        view.showDiaLogInValid(view.getStringRes(R.string.account_already_registered))
                    } else {
                        updateFirebase()
                    }
                } else {
                    updateFirebase()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                view.hideLoading()
                view.showDiaLogInValid(view.getStringRes(R.string.error_firebase))
            }
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
