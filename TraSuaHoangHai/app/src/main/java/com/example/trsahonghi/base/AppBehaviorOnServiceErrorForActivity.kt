package com.example.trsahonghi.base

import android.os.Bundle
import com.example.trsahonghi.R
import com.example.trsahonghi.util.ResultCode

class AppBehaviorOnServiceErrorForActivity(private val activity: BaseActivity) :
    AppBehaviorOnServiceError {
    override fun handleServiceError(exception: BaseException) {
        when (exception.code.toString()) {
            ResultCode.ERROR_401 -> {
                activity.showAlertDialogNew(
                    icon = R.drawable.ic_unsuccessfull,
                    title = activity.getString(R.string.app_notify_title),
                    message = activity.getString(R.string.sever_error),
                    textTopButton = activity.getString(R.string.common_close),
                    autoCancel = false,
                )
            }

            ResultCode.ERROR_500 -> {
                activity.showAlertDialogNew(
                    icon = R.drawable.ic_unsuccessfull,
                    title = activity.getString(R.string.app_notify_title),
                    message = activity.getString(R.string.sever_error),
                    textTopButton = activity.getString(R.string.common_close),
                    autoCancel = false,
                )
            }

            else -> {
                exception.message?.let { activity.showMessage(it) }
            }
        }
    }
}
