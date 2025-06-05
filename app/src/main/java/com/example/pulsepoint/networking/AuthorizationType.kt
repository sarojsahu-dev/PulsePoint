package com.example.pulsepoint.networking

import okhttp3.Request

enum class AuthorizationType {
    ACCESS_TOKEN,
    NONE;
}

fun Request.authTag(): AuthorizationType =
    tag(AuthorizationType::class.java) ?: AuthorizationType.ACCESS_TOKEN