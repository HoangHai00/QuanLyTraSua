package com.example.trsahonghi.ui.login

import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.base.CommonPresenter
import com.example.trsahonghi.api.model.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginPresenter(
    private val view: LoginContract.View
) : CommonPresenter(view, view), LoginContract.Presenter {
    private var _account = MutableLiveData<String>("")
    private var _passWord = MutableLiveData<String>("")
    private val database = FirebaseDatabase.getInstance().getReference("AccountDatabase")


    override fun account() = _account
    override fun password() = _passWord

    override fun login() {
        view.showLoading()

        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                view.hideLoading()
                if (snapshot.exists()) {
                    val userList = snapshot.children.mapNotNull { it.getValue(User::class.java) }
                    if (userList.any { it.account == _account.value && it.password == _passWord.value }) {
                        view.loginSuccessful()
                    } else {
                        view.loginFailed()
                    }

                } else {
                    // Hiển thị thông báo khi dữ liệu không tồn tại
                    view.hideLoading()
                    view.errorFirebase()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                view.hideLoading()
                view.errorFirebase()

            }
        })
    }


}