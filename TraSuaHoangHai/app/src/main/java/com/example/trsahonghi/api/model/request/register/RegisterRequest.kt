package com.example.trsahonghi.api.model.request.register

data class RegisterRequest(
    val email: String,
    val password: String,
    val fullName: String,
    val phoneNumber: String,
    val address: String,
)
