package com.example.pulsepoint.data.remote.repositories

import com.example.pulsepoint.data.models.BloodBankDataResponse
import com.example.pulsepoint.data.models.BloodBankDistrictList
import com.example.pulsepoint.data.models.BloodBankResponse
import com.example.pulsepoint.data.models.BloodBankStateList
import com.example.pulsepoint.data.remote.api.BloodService
import com.example.pulsepoint.networking.Networking
import com.example.pulsepoint.utils.Response
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BloodBankRepository @Inject constructor() {
    val bloodAvailabilityService =
        Networking.create(BloodService::class.java, baseUrl = "https://eraktkosh.mohfw.gov.in/")

    suspend fun getBloodBankData(params: Map<String, String>): Response<BloodBankDataResponse> {
        return withContext(Dispatchers.IO) {
            try {
                var url = "https://api.data.gov.in/resource/fced6df9-a360-4e08-8ca0-f283fc74ce15?"
                params.forEach { key, value ->
                    url += "$key=$value&"
                }
                url = url.dropLast(1)
                val response = bloodAvailabilityService.getBloodBankData(url = url)
                if (response is com.haroldadmin.cnradapter.NetworkResponse.Success) {
                    Response.Success(response.body)
                } else {
                    Response.Error("Something went wrong")
                }
            } catch (e: Exception) {
                Response.Error("Something went wrong : ${e.message}")
            }
        }
    }

    suspend fun getBloodAvailability(params: Map<String, String> = emptyMap()): Response<BloodBankResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val response = bloodAvailabilityService.getBloodAvailability(params = params)
                if (response is com.haroldadmin.cnradapter.NetworkResponse.Success) {
                    Response.Success(response.body)
                } else {
                    Response.Error("Something went wrong")
                }
            } catch (e: Exception) {
                Response.Error("Something went wrong : ${e.message}")
            }
        }
    }

    suspend fun getStateList(params: Map<String, String> = emptyMap()): Response<BloodBankStateList> {
        return withContext(Dispatchers.IO) {
            try {
                val response = bloodAvailabilityService.getStateList(params = params)
                if (response is com.haroldadmin.cnradapter.NetworkResponse.Success) {
                    Response.Success(response.body)
                } else {
                    Response.Error("Something went wrong")
                }
            } catch (e: Exception) {
                Response.Error("Something went wrong : ${e.message}")
            }
        }
    }

    suspend fun getDistrictList(params: Map<String, String> = emptyMap()): Response<BloodBankDistrictList> {
        return withContext(Dispatchers.IO) {
            try {
                val response = bloodAvailabilityService.getDistrictList(params = params)
                if (response is com.haroldadmin.cnradapter.NetworkResponse.Success) {
                    Response.Success(response.body)
                } else {
                    Response.Error("Something went wrong")
                }
            } catch (e: Exception) {
                Response.Error("Something went wrong : ${e.message}")
            }
        }
    }
}