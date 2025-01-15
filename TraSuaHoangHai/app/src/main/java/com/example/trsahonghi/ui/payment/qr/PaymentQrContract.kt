package com.example.trsahonghi.ui.payment.qr

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import com.example.trsahonghi.base.AppBehaviorOnServiceError
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView

interface PaymentQrContract {
    interface View : BaseView, AppBehaviorOnServiceError {
        fun setImageQr(bitmap: Bitmap)
        fun showError(message: String)
        fun getStringRes(id: Int): String
    }

    interface Presenter : BasePresenter {
        fun getQrCode()
        fun price(): LiveData<String>
        fun message(): LiveData<String>
        fun submitOrder()
    }
}