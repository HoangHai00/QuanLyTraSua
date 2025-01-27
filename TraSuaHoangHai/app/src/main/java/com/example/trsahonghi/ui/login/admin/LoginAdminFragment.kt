package com.example.trsahonghi.ui.login.admin

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.trsahonghi.R
import com.example.trsahonghi.api.repository.account.AccountRepositoryImpl
import com.example.trsahonghi.base.BaseDataBindFragment
import com.example.trsahonghi.databinding.FragmentHomeAdminBinding
import com.example.trsahonghi.databinding.FragmentLoginAdminBinding
import com.example.trsahonghi.ui.home.admin.HomeAdminFragment
import com.example.trsahonghi.ui.home.homegroup.HomeGroupFragment
import com.example.trsahonghi.util.CommonToast

class LoginAdminFragment :
    BaseDataBindFragment<FragmentLoginAdminBinding, LoginAdminContract.Presenter>(),
    LoginAdminContract.View {
    companion object {
        fun newInstance() = LoginAdminFragment()
    }

    override fun getLayoutId() = R.layout.fragment_login_admin

    override fun initView() {
        mBinding?.apply {
            btnConfirm.setOnClickListener {
                mPresenter?.loginAdmin()
            }
            toolbar.setOnBackClickListener{
                getBaseActivity().onBackFragment()
            }

        }
    }

    override fun initData() {
        mPresenter = LoginAdminPresenter(
            this,
            AccountRepositoryImpl()
        ).apply {
            mBinding?.presenter = this
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

    override fun loginAdminSuccessful() {
        getBaseActivity().replaceFragment(HomeAdminFragment.newInstance(), R.id.flMain)
        CommonToast.showToast(
            requireContext(),
            getString(R.string.hello_admin),
            R.drawable.ic_checked_green
        )
    }

}