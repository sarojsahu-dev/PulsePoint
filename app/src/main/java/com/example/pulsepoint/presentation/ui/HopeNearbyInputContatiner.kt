package com.example.pulsepoint.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pulsepoint.data.models.BloodBankDistrictList
import com.example.pulsepoint.data.models.BloodBankStateList
import com.example.pulsepoint.presentation.components.PulsePointTopBar
import com.example.pulsepoint.presentation.viewmodels.BloodBankViewModel
import com.example.pulsepoint.style.Background
import com.example.pulsepoint.style.BgBrand01
import com.example.pulsepoint.style.BgWhite
import com.example.pulsepoint.style.Border03
import com.example.pulsepoint.style.Text01
import com.example.pulsepoint.style.styleBody1Semibold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HopeNearbyInputContainer(
    onSearchClick: () -> Unit,
    viewModel: BloodBankViewModel
) {
    var selectedState by remember { mutableStateOf<BloodBankStateList.BloodBankStateListItem?>(null) }
    var selectedDistrict by remember { mutableStateOf<BloodBankDistrictList.Record?>(null) }
    var selectedBloodType by remember { mutableStateOf("Whole Blood") }

    LaunchedEffect(Unit) {
        viewModel.getStateList()
    }
    val states by viewModel.stateList.collectAsState()
    val districts by viewModel.districtList.collectAsState()

    val bloodGroups = listOf("All Blood Groups", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-")
    val bloodTypes = listOf("Whole Blood", "Plasma", "Platelets", "Red Cells")

    Scaffold(
        topBar = {
            PulsePointTopBar(title = "Search for blood banks")
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Select location",
                style = styleBody1Semibold,
                color = Text01
            )

            // State Dropdown
            DropdownFieldState(
                state = selectedState,
                options = states.data,
                onSelectionChange = {
                    viewModel.selectedState = it
                    selectedState = it
                    viewModel.getDistrictList(it.value.toString())
                },
                isHighlighted = true
            )

            // District Dropdown
            DropdownFieldDistrict(
                district = selectedDistrict,
                options = districts.data?.records,
                onSelectionChange = {
                    viewModel.selectedDistrict = it
                    selectedDistrict = it
                },
                isHighlighted = true
            )

//            // Blood Group Dropdown
//            DropdownField(
//                label = selectedBloodGroup,
//                options = bloodGroups,
//                onSelectionChange = { selectedBloodGroup = it }
//            )

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Select Blood Component",
                style = styleBody1Semibold,
                color = Text01
            )

            // Blood Type Dropdown
            DropdownField(
                label = selectedBloodType,
                options = bloodTypes,
                onSelectionChange = { selectedBloodType = it },
                isHighlighted = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Hit Button
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                onClick = {
                    if (viewModel.selectedState != null && viewModel.selectedDistrict != null) {
                        viewModel.getBloodAvailability()
                        onSearchClick()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = BgBrand01
                ),
                shape = RoundedCornerShape(12.dp),
            ) {
                Text(
                    text = "Search Blood Banks",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownFieldState(
    state: BloodBankStateList.BloodBankStateListItem?,
    options: List<BloodBankStateList.BloodBankStateListItem>?,
    onSelectionChange: (BloodBankStateList.BloodBankStateListItem) -> Unit,
    isHighlighted: Boolean = false
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = state?.label ?: "Select State",
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = if (isHighlighted) BgBrand01 else Border03,
                unfocusedBorderColor = if (isHighlighted) BgBrand01 else Border03,
                focusedContainerColor = BgWhite,
                unfocusedContainerColor = BgWhite,
                focusedTextColor = Color(0xFF374151),
                unfocusedTextColor = Color(0xFF6B7280)
            ),
            shape = RoundedCornerShape(8.dp),
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color.White)
        ) {
            options?.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option.label.toString(),
                            fontSize = 14.sp,
                            color = Color(0xFF374151)
                        )
                    },
                    onClick = {
                        onSelectionChange(option)
                        expanded = false
                    },
                    modifier = Modifier.background(Color.White)
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownField(
    label: String,
    options: List<String>,
    onSelectionChange: (String) -> Unit,
    isHighlighted: Boolean = false
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = label,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = if (isHighlighted) BgBrand01 else Border03,
                unfocusedBorderColor = if (isHighlighted) BgBrand01 else Border03,
                focusedContainerColor = BgWhite,
                unfocusedContainerColor = BgWhite,
                focusedTextColor = Color(0xFF374151),
                unfocusedTextColor = Color(0xFF6B7280)
            ),
            shape = RoundedCornerShape(8.dp),
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color.White)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option,
                            fontSize = 14.sp,
                            color = Color(0xFF374151)
                        )
                    },
                    onClick = {
                        onSelectionChange(option)
                        expanded = false
                    },
                    modifier = Modifier.background(Color.White)
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownFieldDistrict(
    district: BloodBankDistrictList.Record?,
    options: List<BloodBankDistrictList.Record?>?,
    onSelectionChange: (BloodBankDistrictList.Record) -> Unit,
    isHighlighted: Boolean = false
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = district?.id ?: "Select District",
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = if (isHighlighted) BgBrand01 else Border03,
                unfocusedBorderColor = if (isHighlighted) BgBrand01 else Border03,
                focusedContainerColor = BgWhite,
                unfocusedContainerColor = BgWhite,
                focusedTextColor = Color(0xFF374151),
                unfocusedTextColor = Color(0xFF6B7280)
            ),
            shape = RoundedCornerShape(8.dp),
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color.White)
        ) {
            options?.forEach { option ->
                option?.let {
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = it.id.toString(),
                                fontSize = 14.sp,
                                color = Color(0xFF374151)
                            )
                        },
                        onClick = {
                            onSelectionChange(it)
                            expanded = false
                        },
                        modifier = Modifier.background(Color.White)
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownFieldBloodType(
    label: String,
    options: List<String>,
    onSelectionChange: (String) -> Unit,
    isHighlighted: Boolean = false
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = label,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = if (isHighlighted) BgBrand01 else Border03,
                unfocusedBorderColor = if (isHighlighted) BgBrand01 else Border03,
                focusedContainerColor = BgWhite,
                unfocusedContainerColor = BgWhite,
                focusedTextColor = Color(0xFF374151),
                unfocusedTextColor = Color(0xFF6B7280)
            ),
            shape = RoundedCornerShape(8.dp),
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color.White)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option,
                            fontSize = 14.sp,
                            color = Color(0xFF374151)
                        )
                    },
                    onClick = {
                        onSelectionChange(option)
                        expanded = false
                    },
                    modifier = Modifier.background(Color.White)
                )
            }
        }
    }
}