package com.example.pulsepoint.presentation.viewmodels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pulsepoint.data.models.BloodBank
import com.example.pulsepoint.data.models.BloodBankDataResponse
import com.example.pulsepoint.data.models.BloodBankDistrictList
import com.example.pulsepoint.data.models.BloodBankParser
import com.example.pulsepoint.data.models.BloodBankStateList
import com.example.pulsepoint.data.models.BloodDonationCampsResponse
import com.example.pulsepoint.data.remote.repositories.BloodBankRepository
import com.example.pulsepoint.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class BloodBankViewModel @Inject constructor(
    val app: Application,
    val repository: BloodBankRepository
) : AndroidViewModel(application = app) {
    private val _stateList = MutableStateFlow<Response<BloodBankStateList>>(Response.Loading())
    val stateList = _stateList.asStateFlow()

    private val _districtList =
        MutableStateFlow<Response<BloodBankDistrictList>>(Response.Loading())
    val districtList = _districtList.asStateFlow()

    private val _bloodBankAvailability =
        MutableStateFlow<Response<List<BloodBank>>>(Response.Loading())
    val bloodBankAvailability = _bloodBankAvailability.asStateFlow()

    private val _bloodBankList =
        MutableStateFlow<Response<BloodBankDataResponse>>(Response.Loading())
    val bloodBankList = _bloodBankList.asStateFlow()

    private val _bloodDonationCamps =
        MutableStateFlow<Response<BloodDonationCampsResponse>>(Response.Loading())
    val bloodDonationCamps = _bloodDonationCamps.asStateFlow()

    var selectedState: BloodBankStateList.BloodBankStateListItem? = null
    var selectedDistrict: BloodBankDistrictList.Record? = null

    fun getStateList() {
        viewModelScope.launch {
            val params: Map<String, String> = mapOf<String, String>(
                "hmode" to "GETSTATELIST",
                "statetype" to "2",
                "lang" to "0"
            )
            val response = repository.getStateList(params = params)
            _stateList.value = response
            when (response) {
                is Response.Loading -> {
                    Log.d("BloodBankViewModel", "Loading district list...")
                }

                is Response.Success -> {
                    Log.d("BloodBankViewModel", "Successfully fetched State list: ${response.data}")
                    response.data?.let { stateList ->
                        // Handle the state list data as needed
                        Log.d("BloodBankViewModel", "State List: $stateList")
                        getDistrictList(stateList[0].value.toString())
                    }
                }

                is Response.Error -> {
                    Log.d("BloodBankViewModel", "Error fetching district list: ${response.message}")
                }
            }
        }
    }

    fun getDistrictList(stateId: String) {
        viewModelScope.launch {
            val params: Map<String, String> = mapOf<String, String>(
                "hmode" to "GETDISTRICTLIST",
                "selectedStateCode" to stateId
            )
            val response = repository.getDistrictList(params = params)
            _districtList.value = response
            when (response) {
                is Response.Loading -> {
                    Log.d("BloodBankViewModel", "Loading district list...")
                }

                is Response.Success -> {
                    Log.d(
                        "BloodBankViewModel",
                        "Successfully fetched district list: ${response.data}"
                    )
                }

                is Response.Error -> {
                    Log.d("BloodBankViewModel", "Error fetching district list: ${response.message}")
                }
            }
        }
    }

    fun getBloodBankData() {
        viewModelScope.launch {
            val params: Map<String, String> = mapOf<String, String>(
                "api-key" to "579b464db66ec23bdd000001369b1e4ed5af4e497a8294797b2e0768",
                "format" to "json",
                "limit" to "3000"
            )
            val response = repository.getBloodBankData(params = params)
            _bloodBankList.value = response
            when (response) {
                is Response.Loading -> {
                    Log.d("BloodBankViewModel", "Loading blood bank data...")
                }

                is Response.Success -> {
                    Log.d(
                        "BloodBankViewModel",
                        "Successfully fetched blood bank data: ${response.data}"
                    )
                }

                is Response.Error -> {
                    Log.d(
                        "BloodBankViewModel",
                        "Error fetching blood bank data: ${response.message}"
                    )
                }
            }
        }
    }

    fun getBloodAvailability() {
        viewModelScope.launch {
//            hmode=GETNEARBYSTOCKDETAILS&stateCode=24&districtCode=474&bloodGroup=all&bloodComponent=11&lang=0
            val params: Map<String, String> = mapOf<String, String>(
                "hmode" to "GETNEARBYSTOCKDETAILS",
                "stateCode" to selectedState?.value.toString(),
                "districtCode" to selectedDistrict?.value.toString(),
                "bloodGroup" to "all",
                "bloodComponent" to "11",
                "lang" to "0",
                "_" to System.currentTimeMillis().toString()
            )
            val response = repository.getBloodAvailability(params = params)
            when (response) {
                is Response.Loading -> {
                    Log.d("BloodBankViewModel", "Loading blood availability data...")
                }

                is Response.Success -> {
                    response.data?.let {
                        _bloodBankAvailability.value =
                            Response.Success(BloodBankParser.parseBloodBankData(response = response.data))
                    }
                    Log.d(
                        "BloodBankViewModel",
                        "Successfully fetched blood availability data: ${response.data}"
                    )
                }

                is Response.Error -> {
                    Response.Error(message = response.message.toString(), data = null)
                    Log.d(
                        "BloodBankViewModel",
                        "Error fetching blood availability data: ${response.message}"
                    )
                }
            }
        }
    }

    fun getBloodDonationCamps(
        stateCode: String? = null,
        districtCode: String? = null,
        campDate: String? = null
    ) {
        viewModelScope.launch {
            _bloodDonationCamps.value = Response.Loading()

            val finalStateCode = stateCode ?: selectedState?.value?.toString() ?: "24"
            val finalDistrictCode = districtCode ?: selectedDistrict?.value?.toString() ?: "474"
            val finalCampDate = campDate ?: getCurrentDateFormatted()

            val response = repository.getBloodDonationCamps(
                stateCode = finalStateCode,
                districtCode = finalDistrictCode,
                campDate = finalCampDate
            )
            _bloodDonationCamps.value = response

            when (response) {
                is Response.Loading -> {
                    Log.d("BloodBankViewModel", "Loading blood donation camps...")
                }

                is Response.Success -> {
                    Log.d(
                        "BloodBankViewModel",
                        "Successfully fetched blood donation camps: ${response.data}"
                    )
                    response.data?.let { campsResponse ->
                        Log.d("BloodBankViewModel", "Total camps found: ${campsResponse.totalCamps}")
                        campsResponse.camps.forEach { camp ->
                            Log.d("BloodBankViewModel", "Camp: ${camp.campName} at ${camp.location}")
                        }
                    }
                }

                is Response.Error -> {
                    Log.d(
                        "BloodBankViewModel",
                        "Error fetching blood donation camps: ${response.message}"
                    )
                }
            }
        }
    }

    private fun getCurrentDateFormatted(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date())
    }


    fun showToast(message: String) {
        viewModelScope.launch(Dispatchers.Main) {
            Toast.makeText(app.applicationContext, message, Toast.LENGTH_SHORT).show()
        }
    }
}