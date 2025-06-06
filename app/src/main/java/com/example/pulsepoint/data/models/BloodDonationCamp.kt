package com.example.pulsepoint.data.models

// Data classes for blood donation camps
data class BloodDonationCamp(
    val id: String,
    val date: String,
    val campName: String,
    val location: String,
    val state: String,
    val city: String,
    val contactNumber: String,
    val bloodCentre: String,
    val organizer: String,
    val registrationUrl: String,
    val timing: String
)

data class BloodDonationCampsResponse(
    val camps: List<BloodDonationCamp>,
    val totalCamps: Int,
    val date: String,
    val city: String,
    val state: String
)

data class BloodCampsRawResponse(
    val data: List<List<String>>
)
