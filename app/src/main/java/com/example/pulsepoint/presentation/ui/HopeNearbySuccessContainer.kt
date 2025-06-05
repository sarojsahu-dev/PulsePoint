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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pulsepoint.R
import com.example.pulsepoint.model.BloodBankData
import com.example.pulsepoint.model.BloodType
import com.example.pulsepoint.presentation.components.ActionIconButton
import com.example.pulsepoint.presentation.components.BloodBankCard
import com.example.pulsepoint.presentation.components.FilterDropdown
import com.example.pulsepoint.presentation.components.SuccessTopBar
import com.example.pulsepoint.presentation.navigation.BloodBankSuccess
import com.example.pulsepoint.presentation.viewmodels.BloodBankViewModel
import com.example.pulsepoint.style.Background
import com.example.pulsepoint.style.Text04
import com.example.pulsepoint.style.styleBody3Semibold

@Composable
fun HopeNearbySuccessContainer(
    searchParams: BloodBankSuccess,
    onBackClick: () -> Unit,
    viewModel: BloodBankViewModel
) {
    val availabilityList by viewModel.bloodBankAvailability.collectAsState()
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
                onBackClick = onBackClick,
                location = viewModel.selectedDistrict?.id.toString() + " " + viewModel.selectedState?.label.toString() + " India"
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

                availabilityList.data?.forEach { bloodBank ->
                    BloodBankCard(bloodBank = bloodBank)
                }
            }
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