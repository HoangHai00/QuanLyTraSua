package com.example.trsahonghi.ui.login

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.trsahonghi.R
import com.example.trsahonghi.util.CommonToast
import com.example.trsahonghi.base.BaseDataBindFragment
import com.example.trsahonghi.databinding.FragmentLoginBinding
import com.example.trsahonghi.ui.home.homegroup.HomeGroupFragment
import com.example.trsahonghi.ui.register.user.RegisterFragment

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
        }

    }

    override fun initData() {
        mPresenter = LoginPresenter(this).apply {
            mBinding?.presenter = this
        }
    }

    override fun loginSuccessful() {
        openFragment(HomeGroupFragment.newInstance())
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

    override fun openFragment(fragment: Fragment) {
        getBaseActivity().replaceFragment(fragment, R.id.flMain)
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