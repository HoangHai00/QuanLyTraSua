package com.example.trsahonghi.ui.payment.qr

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import com.example.trsahonghi.api.model.BubbleTea
import com.example.trsahonghi.base.AppBehaviorOnServiceError
import com.example.trsahonghi.base.BasePresenter
import com.example.trsahonghi.base.BaseView

interface PaymentQrContract {
    interface View : BaseView, AppBehaviorOnServiceError {
        fun showDiaLog(message: String)
        fun setImageQr(bitmap: Bitmap)
        fun showError(message: String)
        fun getStringRes(id: Int): String
        fun sendListFoodBroadcast(list: List<BubbleTea>)

    }

    interface Presenter : BasePresenter {
        fun getQrCode()
        fun price(): LiveData<String>
        fun message(): LiveData<String>
        fun submitOrder()
    }
}