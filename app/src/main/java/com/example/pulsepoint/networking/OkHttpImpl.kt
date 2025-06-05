package com.example.pulsepoint.networking

class OkHttpImpl : IOkHttpSetup {
    override fun getDefaultHeaders(url: String): Map<String, String> {
        val headers = HashMap<String, String>()
        return headers
    }

    override fun onSessionExpire() {
    }

    override fun refreshAuthToken(url: String): Map<String, String>? {
        return null
    }
}