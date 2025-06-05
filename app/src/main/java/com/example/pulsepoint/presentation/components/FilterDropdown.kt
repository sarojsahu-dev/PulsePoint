package com.example.pulsepoint.presentation.components

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pulsepoint.style.BgWhite
import com.example.pulsepoint.style.Border03
import com.example.pulsepoint.style.styleBody3Medium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterDropdown(
    selectedFilter: String,
    onFilterChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val filterOptions = listOf("All Blood", "A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-")

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selectedFilter,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor()
                .width(103.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Border03,
                unfocusedBorderColor = Border03,
                focusedContainerColor = BgWhite,
                unfocusedContainerColor = BgWhite
            ),
            shape = RoundedCornerShape(8.dp),
            textStyle = styleBody3Medium
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            filterOptions.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option,
                            style = styleBody3Medium
                        )
                    },
                    onClick = {
                        onFilterChange(option)
                        expanded = false
                    }
                )
            }
        }
    }
}