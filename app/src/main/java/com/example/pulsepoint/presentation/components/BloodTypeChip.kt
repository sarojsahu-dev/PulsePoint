package com.example.pulsepoint.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pulsepoint.model.BloodType
import com.example.pulsepoint.style.BgError03
import com.example.pulsepoint.style.TextError
import com.example.pulsepoint.style.styleBody3Medium


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BloodTypeChip(
    key: String,
    quantity: String,
) {
    Surface(
        modifier = Modifier.wrapContentSize(),
        shape = RoundedCornerShape(8.dp),
        color = BgError03,
        border = BorderStroke(
            width = 1.dp,
            color = TextError
        )
    ) {
        Text(
            text = "${key} (${quantity})",
            style = styleBody3Medium,
            color = TextError,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BloodTypeChipPreview() {
    val bloodTypes = listOf(
        BloodType("AB+Ve", 1),
        BloodType("AB-Ve", 3),
        BloodType("A-Ve", 6),
        BloodType("A+Ve", 2),
        BloodType("B+Ve", 5)
    )

    LazyRow(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(bloodTypes) { bloodType ->
//            BloodTypeChip(bloodType = bloodType)
        }
    }
}