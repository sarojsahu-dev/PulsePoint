package com.example.pulsepoint.model

data class BloodBankData(
    val name: String,
    val type: String,
    val distance: String,
    val lastUpdated: String,
    val bloodTypes: List<BloodType>
)
