package com.example.pulsepoint.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pulsepoint.R
import com.example.pulsepoint.model.BloodBankData
import com.example.pulsepoint.model.BloodType
import com.example.pulsepoint.presentation.components.ActionIconButton
import com.example.pulsepoint.presentation.components.BloodBankCard
import com.example.pulsepoint.presentation.components.BloodBankDetailsSheet
import com.example.pulsepoint.presentation.components.FilterDropdown
import com.example.pulsepoint.presentation.components.SuccessTopBar
import com.example.pulsepoint.presentation.navigation.BloodBankSuccess
import com.example.pulsepoint.style.Background
import com.example.pulsepoint.style.Text04
import com.example.pulsepoint.style.styleBody3Semibold


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HopeNearbySuccessContainer(
    searchParams: BloodBankSuccess,
    onBackClick: () -> Unit
) {

    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedBloodBank by remember { mutableStateOf<BloodBankData?>(null) }

    val sampleBloodBanks = listOf(
        BloodBankData(
            name = "Command Hospital Blood Centre, Bangalore South",
            type = "Government",
            distance = "0.35 km",
            lastUpdated = "18 Dec, 2024",
            bloodTypes = listOf(
                BloodType("AB+Ve", 1),
                BloodType("AB-Ve", 3),
                BloodType("A-Ve", 6),
                BloodType("A+Ve", 2)
            )
        ),
        BloodBankData(
            name = "CMR Institute of Medical Sciences and hospitals",
            type = "Private",
            distance = "0.4 km",
            lastUpdated = "19 Dec, 2024",
            bloodTypes = listOf(
                BloodType("AB+Ve", 1),
                BloodType("A-Ve", 6),
                BloodType("A+Ve", 2)
            )
        )
    )

    Scaffold(
        topBar = {
            SuccessTopBar(
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 24.dp, horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                FilterRow(
                    selectedFilter = "All Blood",
                    onFilterChange = {},
                    onSearchClick = {},
                    onLocationClick = {},
                    onFilterClick = {},
                    resultCount = sampleBloodBanks.size,
                )

                sampleBloodBanks.forEach { bloodBank ->
                    BloodBankCard(
                        bloodBank = bloodBank,
                        onCardClick = {
                            selectedBloodBank = bloodBank
                            showBottomSheet = true
                        }
                    )
                }
            }
        }
    }

    if (showBottomSheet && selectedBloodBank != null) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
                selectedBloodBank = null
            },
            containerColor = Color.White,
            dragHandle = null,
        ) {
            BloodBankDetailsSheet(
                bloodBank = selectedBloodBank!!,
                address = "${selectedBloodBank!!.name}, Bangalore Urban, Karnataka",
                phoneNumber = "9972399007",
                onCloseClick = {
                    showBottomSheet = false
                    selectedBloodBank = null
                },
                onViewInMaps = {
                    // Handle view in maps action
                    // You can add intent to open maps here
                },
                onCallNow = {
                    // Handle call now action
                    // You can add intent to make a call here
                }
            )
        }
    }
}

@Composable
fun FilterRow(
    selectedFilter: String,
    onFilterChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    onLocationClick: () -> Unit,
    onFilterClick: () -> Unit,
    resultCount: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FilterDropdown(
                selectedFilter = selectedFilter,
                onFilterChange = onFilterChange
            )

            ActionIconButton(
                icon = R.drawable.ic_search,
                contentDescription = "Search",
                onClick = onSearchClick
            )

            ActionIconButton(
                icon = R.drawable.ic_location,
                contentDescription = "Location",
                onClick = onLocationClick
            )


            ActionIconButton(
                icon = R.drawable.ic_filterlist,
                contentDescription = "Filter",
                onClick = onFilterClick
            )
        }

        Text(
            text = "$resultCount results",
            style = styleBody3Semibold,
            color = Text04
        )
    }
}