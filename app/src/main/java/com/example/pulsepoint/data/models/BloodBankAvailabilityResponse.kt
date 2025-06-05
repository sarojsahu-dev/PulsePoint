package com.example.pulsepoint.data.models

import androidx.annotation.Keep

// Data Classes
@Keep
data class BloodBankResponse(
    val data: List<List<String>>
)

data class BloodBank(
    val id: String,
    val name: String,
    val address: String,
    val phone: String,
    val fax: String,
    val email: String,
    val category: String,
    val availability: String,
    val isAvailable: Boolean,
    val bloodTypes: Map<String, Int>,
    val lastUpdated: String,
    val type: String
)

// Parser Class
object BloodBankParser {

    fun parseBloodBankData(response: BloodBankResponse): List<BloodBank> {
        return response.data.map { item ->
            val id = item[0]
            val details = parseDetails(item[1])
            val category = item[2]
            val availability = item[3]
            val lastUpdated = item[4]
            val type = item[5]

            val (isAvailable, bloodTypes) = parseAvailability(availability)

            BloodBank(
                id = id,
                name = details.name,
                address = details.address,
                phone = details.phone,
                fax = details.fax,
                email = details.email,
                category = category,
                availability = availability,
                isAvailable = isAvailable,
                bloodTypes = bloodTypes,
                lastUpdated = lastUpdated,
                type = type
            )
        }
    }

    private data class BloodBankDetails(
        val name: String,
        val address: String,
        val phone: String,
        val fax: String,
        val email: String
    )

    private fun parseDetails(detailsHtml: String): BloodBankDetails {
        // Remove HTML tags and split by <br/>
        val cleanText = detailsHtml.replace("<br/>", "\n").replace(Regex("<[^>]*>"), "")
        val lines = cleanText.split("\n")

        val name = lines.getOrNull(0)?.trim() ?: ""
        val address = lines.getOrNull(1)?.trim() ?: ""

        // Parse contact info from the last line
        val contactLine = lines.getOrNull(2) ?: ""
        val phone = extractPhone(contactLine)
        val fax = extractFax(contactLine)
        val email = extractEmail(contactLine)

        return BloodBankDetails(name, address, phone, fax, email)
    }

    private fun extractPhone(contactLine: String): String {
        val phoneRegex = Regex("Phone:\\s*([^,]+)")
        return phoneRegex.find(contactLine)?.groupValues?.get(1)?.trim() ?: ""
    }

    private fun extractFax(contactLine: String): String {
        val faxRegex = Regex("Fax:\\s*([^,]+)")
        return faxRegex.find(contactLine)?.groupValues?.get(1)?.trim() ?: ""
    }

    private fun extractEmail(contactLine: String): String {
        val emailRegex = Regex("Email:\\s*([^\\s,]+)")
        return emailRegex.find(contactLine)?.groupValues?.get(1)?.trim() ?: ""
    }

    private fun parseAvailability(availability: String): Pair<Boolean, Map<String, Int>> {
        val isAvailable = !availability.contains("Not Available", ignoreCase = true)
        val bloodTypes = mutableMapOf<String, Int>()

        if (isAvailable) {
            // Extract blood types and quantities
            // Pattern: B+Ve:46, AB+Ve:7, A+Ve:30, etc.
            val bloodTypeRegex = Regex("([ABO][+-]?Ve?):(\\d+)")
            bloodTypeRegex.findAll(availability).forEach { match ->
                val type = match.groupValues[1]
                val quantity = match.groupValues[2].toIntOrNull() ?: 0
                bloodTypes[type] = quantity
            }
        }

        return Pair(isAvailable, bloodTypes)
    }
}

// Extension functions for easier access
fun BloodBank.hasBloodType(bloodType: String): Boolean {
    return bloodTypes.containsKey(bloodType) && (bloodTypes[bloodType] ?: 0) > 0
}

fun BloodBank.getBloodTypeQuantity(bloodType: String): Int {
    return bloodTypes[bloodType] ?: 0
}

fun BloodBank.getTotalUnits(): Int {
    return bloodTypes.values.sum()
}

// Utility class for filtering
class BloodBankFilter {

    fun filterByBloodType(banks: List<BloodBank>, bloodType: String): List<BloodBank> {
        return banks.filter { it.hasBloodType(bloodType) }
    }

    fun filterByAvailability(banks: List<BloodBank>): List<BloodBank> {
        return banks.filter { it.isAvailable }
    }

    fun filterByCategory(banks: List<BloodBank>, category: String): List<BloodBank> {
        return banks.filter { it.category.equals(category, ignoreCase = true) }
    }

    fun sortByLastUpdated(banks: List<BloodBank>): List<BloodBank> {
        return banks.sortedByDescending { it.lastUpdated }
    }
}