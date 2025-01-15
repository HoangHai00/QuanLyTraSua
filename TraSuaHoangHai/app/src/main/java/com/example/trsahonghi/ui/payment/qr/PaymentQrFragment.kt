package com.example.trsahonghi.ui.payment.qr

import android.graphics.Bitmap
import android.os.Bundle
import com.example.trsahonghi.R
import com.example.trsahonghi.api.repository.vietqr.VietQRRepositoryImpl
import com.example.trsahonghi.base.BaseDataBindFragment
import com.example.trsahonghi.databinding.FragmentPaymentQrBinding

class PaymentQrFragment :
    BaseDataBindFragment<FragmentPaymentQrBinding, PaymentQrContract.Presenter>(),
    PaymentQrContract.View {
    companion object {
        private const val ARG_PRICE = "ARG_PRICE"
        fun newInstance(
            price: String?
        ): PaymentQrFragment {
            val args = Bundle()
            args.putString(ARG_PRICE, price)
            val fragment = PaymentQrFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun getLayoutId() = R.layout.fragment_payment_qr

    override fun initView() {
        mBinding?.apply {
            toolbar.setOnBackClickListener {
                backClick()
            }
            btnContinue.setOnClickListener {
                getBaseActivity().onBackAllFragments()
            }


        }

    }


    override fun initData() {
        val price = arguments?.getString(ARG_PRICE) ?: "0"
        mPresenter = PaymentQrPresenter(
            this,
            price,
            VietQRRepositoryImpl()
        ).apply {
            mBinding?.presenter = this
            getQrCode()
        }

    }

    override fun setImageQr(bitmap: Bitmap) {
        mBinding?.qrImageView?.setImageBitmap(bitmap)
    }

    override fun showError(message: String) {

    }

    fun backClick() {
        getBaseActivity().onBackFragment()
    }

    override fun getStringRes(id: Int): String {
        return getString(id)
    }
}