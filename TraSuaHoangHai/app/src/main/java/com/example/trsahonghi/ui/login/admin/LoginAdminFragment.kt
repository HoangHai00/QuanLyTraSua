package com.example.trsahonghi.ui.login.admin

import com.example.trsahonghi.R
import com.example.trsahonghi.base.BaseDataBindFragment
import com.example.trsahonghi.databinding.FragmentHomeAdminBinding
import com.example.trsahonghi.databinding.FragmentLoginAdminBinding

class LoginAdminFragment :
    BaseDataBindFragment<FragmentLoginAdminBinding, LoginAdminContract.Presenter>(),
    LoginAdminContract.View {
    override fun getLayoutId() = R.layout.fragment_login_admin

    override fun initView() {
      mBinding.apply {

      }
    }

    override fun initData() {
        TODO("Not yet implemented")
    }
}