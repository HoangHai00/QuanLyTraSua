package com.example.trsahonghi.ui.home.admin.setting

import com.example.trsahonghi.R
import com.example.trsahonghi.base.BaseDataBindFragment
import com.example.trsahonghi.databinding.FragmentSettingBinding

class SettingFragment : BaseDataBindFragment<FragmentSettingBinding, SettingContract.Presenter>(),
    SettingContract.View {
    companion object {
        fun newInstance() = SettingFragment()
    }

    override fun getLayoutId() = R.layout.fragment_setting

    override fun initView() {
        mBinding.apply { }
    }

    override fun initData() {
        mPresenter = SettingPresenter(this)
    }
}