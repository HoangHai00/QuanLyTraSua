package com.example.trsahonghi.ui.payment.qr

import android.graphics.BitmapFactory
import android.util.Base64
import androidx.lifecycle.MutableLiveData
import com.example.trsahonghi.R
import com.example.trsahonghi.api.Config
import com.example.trsahonghi.api.model.Item
import com.example.trsahonghi.api.repository.food.FoodRepository
import com.example.trsahonghi.api.repository.vietqr.VietQRRepository
import com.example.trsahonghi.base.CommonPresenter

class PaymentQrPresenter(
    private val view: PaymentQrContract.View,
    private val phoneNumber: String?,
    private val address: String?,
    private val idVoucher: String?,
    private val listItem: List<Item>?,
    price: String?,
    private val vietQRRepository: VietQRRepository,
    private val foodRepository: FoodRepository
) : CommonPresenter(view, view), PaymentQrContract.Presenter {
    private val _price = MutableLiveData<String>(price)
    private val _message = MutableLiveData<String>(view.getStringRes(R.string.payment_bubble_tea))

    override fun price() = _price

    override fun message() = _message

    override fun getQrCode() {
        // Gọi API thông qua repository
        baseCallApi(vietQRRepository.generateQRCode(
            Config.ACCOUNT_NUMBER,
            Config.ACCOUNT_NAME,
            Config.BANK_ID,
            _price.value ?: "0",
            Config.DESCRIPTION
        ), onSuccess = { response ->
            val qrCodeData = response?.data?.qrDataURL
            qrCodeData?.let {
                // Loại bỏ phần tiền tố "data:image/png;base64," (nếu có)
                val base64Image = it.removePrefix("data:image/png;base64,")

                // Giải mã chuỗi Base64 thành mảng byte
                val decodedBytes = Base64.decode(base64Image, Base64.DEFAULT)

                // Chuyển mảng byte thành Bitmap
                val bitmap =
                    BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)

                bitmap?.let {
                    view.setImageQr(it)
                }

            }
        }, onError = {
        })


    }

    override fun submitOrder() {
        baseCallApi(
            foodRepository.submitOrder(
                phoneNumber,
                1,
                address,
                idVoucher,
                listItem

            ), onSuccess = {
                view.sendListFoodBroadcast(emptyList())

            },
            onError = {

            }
        )
    }


}