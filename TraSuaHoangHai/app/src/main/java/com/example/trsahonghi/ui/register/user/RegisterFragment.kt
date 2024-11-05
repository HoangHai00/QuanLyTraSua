package com.example.trsahonghi.ui.register.user

import androidx.fragment.app.Fragment
import com.example.trsahonghi.R
import com.example.trsahonghi.base.BaseDataBindFragment
import com.example.trsahonghi.databinding.FragmentRegisterBinding
import com.example.trsahonghi.util.CommonToast

class RegisterFragment :
    BaseDataBindFragment<FragmentRegisterBinding, RegisterContract.Presenter>(),
    RegisterContract.View {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_register

    override fun initView() {
        mBinding?.apply {
            toolbar.setOnBackClickListener {
                onBackClicked()
            }
        }

    }

    override fun initData() {
        mPresenter = RegisterPresenter(this).apply {
            mBinding?.presenter = this
        }
    }


    override fun registerSuccessful() {
        CommonToast.showToast(
            requireContext(),
            getString(R.string.register_success),
            R.drawable.ic_checked_green
        )
        onBackClicked()
    }


    override fun openFragment(fragment: Fragment) {
        getBaseActivity().replaceFragment(fragment, R.id.fl_main)
    }

    override fun showDiaLogInValid(message: String) {
        getBaseActivity().showAlertDialogNew(
            null,
            getString(R.string.app_notify_title),
            message,
            getString(R.string.common_close),
        )
    }

    override fun getStringRes(id: Int): String {
        return getString(id)
    }

    override fun onBackClicked() {
        getBaseActivity().onBackFragment()
    }
}