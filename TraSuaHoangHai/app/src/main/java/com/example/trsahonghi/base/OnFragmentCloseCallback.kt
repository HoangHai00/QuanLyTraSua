package com.example.trsahonghi.base

import android.content.Intent

interface OnFragmentCloseCallback {
    fun onClose(resultCode: Int, data: Intent?)
}