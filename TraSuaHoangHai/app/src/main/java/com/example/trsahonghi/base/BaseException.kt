package com.example.trsahonghi.base

class BaseException(
    val code: Int,
    override val message: String
) : RuntimeException(message)
