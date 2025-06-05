package com.example.pulsepoint.presentation.ui

import androidx.compose.runtime.Composable
import com.example.pulsepoint.presentation.navigation.BloodBankNavigation
import com.example.pulsepoint.presentation.viewmodels.BloodBankViewModel

@Composable
fun LaunchScreen(viewModel: BloodBankViewModel) {
    BloodBankNavigation(viewModel = viewModel)
}