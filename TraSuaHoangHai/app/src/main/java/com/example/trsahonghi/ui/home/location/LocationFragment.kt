package com.example.trsahonghi.ui.home.location

import TokenManager
import com.example.trsahonghi.R
import com.example.trsahonghi.base.BaseDataBindFragment
import com.example.trsahonghi.databinding.FragmentLocationBinding
import com.example.trsahonghi.widget.dialog.AlertDialogListener

class LocationFragment :
    BaseDataBindFragment<FragmentLocationBinding, LocationContract.Presenter>(),
    LocationContract.View {
    companion object {
        fun newInstance() = LocationFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_location

    override fun initView() {
        mBinding.apply {


        }
        mBinding?.view = this

    }

    override fun initData() {
        mPresenter = LocationPresenter(this).apply {
            mBinding?.presenter = this
        }

    }

    override fun onBackClicked() {
        getBaseActivity().showAlertDialogNew(
            icon = null,
            title = getString(R.string.app_notify_title),
            message = getString(R.string.log_out),
            textTopButton = getString(R.string.common_success),
            textBottomButton = getString(R.string.common_cancel),
            listener = object : AlertDialogListener {
                override fun onAccept() {
                    context?.let { TokenManager.saveToken(it, "") }
                    getBaseActivity().onBackFragment()
                }

                override fun onCancel() {
                }
            }
        )
    }
}