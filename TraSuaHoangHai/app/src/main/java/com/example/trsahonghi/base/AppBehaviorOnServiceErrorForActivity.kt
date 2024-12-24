package com.example.trsahonghi.base

class AppBehaviorOnServiceErrorForActivity(private val activity: BaseActivity) : AppBehaviorOnServiceError {
    override fun handleServiceError(exception: BaseException) {
        when (exception.code) {
            401 -> {
                // Xử lý lỗi Unauthorized (401)
//                activity.showToast("Unauthorized: Please login again.")
//                activity.navigateToLoginScreen()
            }
            500 -> {
                // Xử lý lỗi Server (500)
//                activity.showToast("Server error: Please try again later.")
            }
            else -> {
                // Xử lý lỗi khác
//                activity.showToast("Error: ${exception.message}")
            }
        }
    }
}
