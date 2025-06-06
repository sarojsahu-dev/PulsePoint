package com.example.pulsepoint.data.remote.repositories

import com.example.pulsepoint.data.models.BloodBankDataResponse
import com.example.pulsepoint.data.models.BloodBankDistrictList
import com.example.pulsepoint.data.models.BloodBankResponse
import com.example.pulsepoint.data.models.BloodBankStateList
import com.example.pulsepoint.data.models.BloodCampsRawResponse
import com.example.pulsepoint.data.models.BloodDonationCamp
import com.example.pulsepoint.data.models.BloodDonationCampsResponse
import com.example.pulsepoint.data.remote.api.BloodService
import com.example.pulsepoint.networking.Networking
import com.example.pulsepoint.utils.Response
import com.haroldadmin.cnradapter.NetworkResponse
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

    suspend fun getBloodDonationCamps(
        stateCode: String,
        districtCode: String,
        campDate: String
    ): Response<BloodDonationCampsResponse> {
        return withContext(Dispatchers.IO) {
            try {
                val params = mapOf(
                    "hmode" to "GETNEARBYCAMPS",
                    "stateCode" to stateCode,
                    "districtCode" to districtCode,
                    "campDate" to campDate
                )

                val response = bloodAvailabilityService.getBloodDonationCamps(params = params)
                if (response is NetworkResponse.Success) {
                    val parsedResponse = parseBloodCampsResponse(response.body)
                    Response.Success(parsedResponse)
                } else {
                    Response.Error("Failed to fetch blood donation camps")
                }
            } catch (e: Exception) {
                Response.Error("Something went wrong: ${e.message}")
            }
        }
    }

    private fun parseBloodCampsResponse(rawResponse: BloodCampsRawResponse): BloodDonationCampsResponse {
        val camps = rawResponse.data.map { campData ->
            BloodDonationCamp(
                id = campData[0],
                date = cleanHtmlContent(campData[1]).split("<br/>")[0], // Extract just the date
                campName = cleanHtmlContent(campData[2]),
                location = cleanHtmlContent(campData[3]),
                state = cleanHtmlContent(campData[4]),
                city = cleanHtmlContent(campData[5]),
                contactNumber = cleanHtmlContent(campData[6]),
                bloodCentre = cleanHtmlContent(campData[7]),
                organizer = cleanHtmlContent(campData[8]),
                registrationUrl = extractUrlFromHtml(campData[9]),
                timing = cleanHtmlContent(campData[10])
            )
        }

        // Extract metadata from first camp (assuming all camps have same date/city/state)
        val firstCamp = camps.firstOrNull()

        return BloodDonationCampsResponse(
            camps = camps,
            totalCamps = camps.size,
            date = firstCamp?.date ?: "",
            city = firstCamp?.city ?: "",
            state = firstCamp?.state ?: ""
        )
    }

    private fun cleanHtmlContent(htmlContent: String): String {
        return htmlContent
            .replace(Regex("<[^>]*>"), "") // Remove all HTML tags
            .replace("&nbsp;", " ") // Replace HTML space entities
            .replace("&amp;", "&") // Replace HTML ampersand entities
            .replace("&lt;", "<") // Replace HTML less than entities
            .replace("&gt;", ">") // Replace HTML greater than entities
            .replace("&quot;", "\"") // Replace HTML quote entities
            .replace("&#39;", "'") // Replace HTML apostrophe entities
            .replace(Regex("\\s+"), " ") // Replace multiple spaces with single space
            .trim() // Remove leading and trailing whitespace
    }

    private fun extractUrlFromHtml(htmlContent: String): String {
        val regex = Regex("href='([^']*)'")
        val matchResult = regex.find(htmlContent)
        return matchResult?.groupValues?.get(1) ?: ""
    }
}