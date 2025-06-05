package com.example.pulsepoint.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pulsepoint.data.models.BloodBank
import com.example.pulsepoint.model.BloodBankData
import com.example.pulsepoint.model.BloodType
import com.example.pulsepoint.style.BgWhite
import com.example.pulsepoint.style.Purple200
import com.example.pulsepoint.style.Text01
import com.example.pulsepoint.style.Text04
import com.example.pulsepoint.style.styleBody1Semibold
import com.example.pulsepoint.style.styleBody2Regular


@Composable
fun BloodBankCard(
    bloodBank: BloodBank,
    onDistanceClick: () -> Unit = {},
    onCardClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCardClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = BgWhite
        ),
        border = BorderStroke(1.dp, Purple200)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            BloodBankHeader(
                type = bloodBank.category,
                lastUpdated = bloodBank.lastUpdated
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = bloodBank.name,
                style = styleBody1Semibold,
                color = Text01
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(bloodBank.bloodTypes.entries.toList()) { entry ->
                    BloodTypeChip(key = entry.key, quantity = entry.value.toString())
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            BloodBankActions(
                distance = "Navigate",
                onDistanceClick = onDistanceClick,
                onDetailsClick = onCardClick
            )
        }
    }
}

@Composable
fun BloodBankHeader(
    type: String,
    lastUpdated: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TypeChip(text = type)

        Text(
            text = "Updated: $lastUpdated",
            style = styleBody2Regular,
            color = Text04
        )
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFF5F4FF)
@Composable
fun BloodBankCardPreview() {
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

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        sampleBloodBanks.forEach { bloodBank ->
//            BloodBankCard(bloodBank = bloodBank)
        }
    }
}