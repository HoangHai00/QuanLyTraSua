package com.example.trsahonghi.api

object Config {
    const val sectionTimeout = 300000 // 5 minute
    const val urlImage = "https://appbanhang.somee.com/uploads/id.jpg"

    const val BANK_ID = 970415
    const val ACCOUNT_NUMBER = "103870442864"
    const val ACCOUNT_NAME = "PHAM HOANG HAI"
    const val DESCRIPTION = "Thanh toan tien tra sua"

    fun getImageUrl(id: String): String {
        return urlImage.replace("id", id)
    }

}