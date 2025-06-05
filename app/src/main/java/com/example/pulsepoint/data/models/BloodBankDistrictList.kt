package com.example.pulsepoint.data.models


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class BloodBankDistrictList(
    @SerializedName("records")
    var records: List<Record?>?
) {
    @Keep
    data class Record(
        @SerializedName("id")
        var id: String?,
        @SerializedName("value")
        var value: String?
    )
}