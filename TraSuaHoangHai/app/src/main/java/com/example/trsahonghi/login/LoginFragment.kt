package com.example.trsahonghi.login

import android.content.Context
import com.example.trsahonghi.R
import com.example.trsahonghi.Util.CommonToast
import com.example.trsahonghi.base.BaseDataBindFragment
import com.example.trsahonghi.databinding.FragmentLoginBinding

class LoginFragment : BaseDataBindFragment<FragmentLoginBinding, LoginContract.Presenter>(),
    LoginContract.View {

        companion object{
            fun newInstance() = LoginFragment()
        }
    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun initView() {
        mBinding?.apply {
            btnLogin.setOnClickListener {
                mPresenter?.login()

            }
        }

    }

    override fun initData() {
        mPresenter = LoginPresenter(this).apply {
            mBinding?.presenter = this
        }
    }

    override fun getViewContext(): Context? {
        return context
    }

    override fun loginSuccessful() {
        CommonToast.showToast(
            requireContext(),
            getString(R.string.login_success),
            R.drawable.ic_checked_green
        )

    }

    override fun loginFailed() {
        getBaseActivity().showAlertDialogNew(
            null,
            getString(R.string.app_notify_title),
            getString(R.string.login_failed),
            getString(R.string.common_close),
        )
    }

    override fun errorFirebase() {
        getBaseActivity().showAlertDialogNew(
            null,
            getString(R.string.app_notify_title),
            getString(R.string.login_failed),
            getString(R.string.common_close),
        )
    }
}