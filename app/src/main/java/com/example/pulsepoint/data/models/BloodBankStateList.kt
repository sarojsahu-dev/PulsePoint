package com.example.pulsepoint.data.models


import androidx.annotation.Keep
import com.example.pulsepoint.data.models.BloodBankStateList.BloodBankStateListItem
import com.google.gson.annotations.SerializedName

@Keep
class BloodBankStateList : ArrayList<BloodBankStateListItem>() {
    @Keep
    data class BloodBankStateListItem(
        @SerializedName("label")
        var label: String?,
        @SerializedName("value")
        var value: String?
    )
}