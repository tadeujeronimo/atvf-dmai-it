package com.tadeujeronimo.deliveryapp.data.models

data class UserLoginResponse(val email: String, val token: String, val admin: Boolean)
