package com.example.pulsepoint.data.remote.api

import com.example.pulsepoint.data.models.BloodBankDataResponse
import com.example.pulsepoint.data.models.BloodBankDistrictList
import com.example.pulsepoint.data.models.BloodBankResponse
import com.example.pulsepoint.data.models.BloodBankStateList
import com.example.pulsepoint.data.models.BloodCampsRawResponse
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface BloodService {
    @GET
    suspend fun getBloodBankData(
        @Url url: String,
        @HeaderMap headers: Map<String, String> = emptyMap(),
    ): NetworkResponse<BloodBankDataResponse, BloodBankDataResponse>

    @GET("BLDAHIMS/bloodbank/nearbyBB.cnt")
    suspend fun getBloodAvailability(
        @QueryMap params: Map<String, String> = emptyMap(),
        @HeaderMap headers: Map<String, String> = emptyMap(),
    ): NetworkResponse<BloodBankResponse, BloodBankResponse>

    @GET("BLDAHIMS/bloodbank/nearbyBB.cnt")
    suspend fun getStateList(
        @QueryMap params: Map<String, String> = emptyMap(),
        @HeaderMap headers: Map<String, String> = emptyMap(),
    ): NetworkResponse<BloodBankStateList, BloodBankStateList>


    @GET("BLDAHIMS/bloodbank/nearbyBB.cnt")
    suspend fun getDistrictList(
        @QueryMap params: Map<String, String> = emptyMap(),
        @HeaderMap headers: Map<String, String> = emptyMap(),
    ): NetworkResponse<BloodBankDistrictList, BloodBankDistrictList>

//    curl -X 'GET' \
//    'https://api.data.gov.in/resource/fced6df9-a360-4e08-8ca0-f283fc74ce15?api-key=579b464db66ec23bdd000001369b1e4ed5af4e497a8294797b2e0768&format=json&limit=1000' \
//    -H 'accept: application/xml'

    @GET("BLDAHIMS/bloodbank/nearbyBB.cnt")
    suspend fun getBloodDonationCamps(
        @QueryMap params: Map<String, String> = emptyMap(),
        @HeaderMap headers: Map<String, String> = emptyMap(),
    ): NetworkResponse<BloodCampsRawResponse, BloodCampsRawResponse>
}