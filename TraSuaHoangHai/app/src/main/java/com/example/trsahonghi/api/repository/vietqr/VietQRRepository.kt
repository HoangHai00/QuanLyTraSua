package com.example.trsahonghi.api.repository.vietqr

import retrofit2.Call
import com.example.trsahonghi.api.model.request.QRRequest
import com.example.trsahonghi.api.model.response.QRResponse

interface VietQRRepository {

    fun generateQRCode(
        accountNo: String,
        accountName: String,
        acqId: Int,
        amount: String,
        addInfo: String
    ): Call<QRResponse>
}