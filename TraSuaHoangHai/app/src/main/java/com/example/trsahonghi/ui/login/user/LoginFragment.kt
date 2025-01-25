package com.example.trsahonghi.ui.login.user

import android.content.Context
import android.text.InputType
import androidx.fragment.app.Fragment
import com.example.trsahonghi.R
import com.example.trsahonghi.api.repository.account.AccountRepositoryImpl
import com.example.trsahonghi.base.BaseDataBindFragment
import com.example.trsahonghi.databinding.FragmentLoginBinding
import com.example.trsahonghi.ui.home.admin.HomeAdminFragment
import com.example.trsahonghi.ui.home.homegroup.HomeGroupFragment
import com.example.trsahonghi.ui.login.admin.LoginAdminFragment
import com.example.trsahonghi.ui.register.user.RegisterFragment
import com.example.trsahonghi.util.CommonToast

class LoginFragment : BaseDataBindFragment<FragmentLoginBinding, LoginContract.Presenter>(),
    LoginContract.View {

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun initView() {
        mBinding?.apply {
            btnLogin.setOnClickListener {
                mPresenter?.login()
            }
            btnRegister.setOnClickListener {
                openFragment(RegisterFragment.newInstance())
            }

            ivEye.setOnClickListener {
                ivEye.isChecked = !ivEye.isChecked
                edtPassword.inputType = if (ivEye.isChecked) {
                    InputType.TYPE_CLASS_TEXT
                } else {
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                }
                edtPassword.setSelection(edtPassword.text.length)
            }

            ivLoginAdmin.setOnClickListener {
                openFragment(LoginAdminFragment.newInstance())
            }


        }

    }

    override fun initData() {
        mPresenter = LoginPresenter(
            this,
            AccountRepositoryImpl()
        ).apply {
            mBinding?.presenter = this
            loginToken()
        }
    }

    override fun showDiaLogInValid(message: String) {
        getBaseActivity().showAlertDialogNew(
            icon = null,
            title = getString(R.string.app_notify_title),
            message = message,
            textTopButton = getString(R.string.common_close),
        )
    }

    override fun getStringRes(id: Int): String {
        return getString(id)
    }

    override fun getViewContext(): Context? {
        return context
    }

    override fun loginSuccessful() {
        openFragment(HomeGroupFragment.newInstance())
        CommonToast.showToast(
            requireContext(),
            getString(R.string.login_success),
            R.drawable.ic_checked_green
        )

    }

    override fun loginAdminSuccessful() {
        openFragment(HomeAdminFragment.newInstance())
        CommonToast.showToast(
            requireContext(),
            getString(R.string.hello_admin),
            R.drawable.ic_checked_green
        )
    }

    override fun openFragment(fragment: Fragment) {
        getBaseActivity().replaceFragment(fragment, R.id.flMain)
    }
}