package com.example.trsahonghi.api

object Config {
    const val sectionTimeout = 300000 // 5 minute
    const val urlImage = "https://appbanhang.somee.com/uploads/id.jpg"

    fun getImageUrl(id: String): String {
        return urlImage.replace("id", id)
    }

}