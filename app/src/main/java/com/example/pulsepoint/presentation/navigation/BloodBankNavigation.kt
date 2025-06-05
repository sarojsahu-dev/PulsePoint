package com.example.pulsepoint.presentation.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pulsepoint.presentation.ui.HopeNearbyInputContainer
import com.example.pulsepoint.presentation.ui.HopeNearbySuccessContainer
import com.example.pulsepoint.presentation.viewmodels.BloodBankViewModel

object Routes {
    const val BLOOD_BANK_INPUT = "blood_bank_input"
    const val BLOOD_BANK_SUCCESS = "blood_bank_success/{state}/{district}/{bloodGroup}/{bloodType}"

    fun createBloodBankSuccessRoute(
        state: String,
        district: String,
        bloodGroup: String,
        bloodType: String
    ): String {
        return "blood_bank_success/${Uri.encode(state)}/${Uri.encode(district)}/${Uri.encode(bloodGroup)}/${Uri.encode(bloodType)}"
    }
}

@Composable
fun BloodBankNavigation(
    viewModel: BloodBankViewModel,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.BLOOD_BANK_INPUT
    ) {
        composable(Routes.BLOOD_BANK_INPUT) {
            HopeNearbyInputContainer(
                viewModel = viewModel,
                onSearchClick = {
                    val route = Routes.createBloodBankSuccessRoute(
                        state = "state",
                        district = "district",
                        bloodGroup = "bloodGroup",
                        bloodType = "bloodType"
                    )
                    navController.navigate(route)
                }
            )
        }

        composable(Routes.BLOOD_BANK_SUCCESS) { backStackEntry ->
            val state = backStackEntry.arguments?.getString("state") ?: ""
            val district = backStackEntry.arguments?.getString("district") ?: ""
            val bloodGroup = backStackEntry.arguments?.getString("bloodGroup") ?: ""
            val bloodType = backStackEntry.arguments?.getString("bloodType") ?: ""

            HopeNearbySuccessContainer(
                viewModel = viewModel,
                searchParams = BloodBankSuccess(
                    state = Uri.decode(state),
                    district = Uri.decode(district),
                    bloodGroup = Uri.decode(bloodGroup),
                    bloodType = Uri.decode(bloodType)
                ),
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

// Keep the data class for parameters
data class BloodBankSuccess(
    val state: String,
    val district: String,
    val bloodGroup: String,
    val bloodType: String
)
