package com.example.pulsepoint.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pulsepoint.style.BgBrand01
import com.example.pulsepoint.style.BgWhite
import com.example.pulsepoint.style.Border03
import com.example.pulsepoint.style.TextInvert
import com.example.pulsepoint.style.styleBody2Semibold

@Composable
fun BloodBankActions(
    distance: String,
    onDistanceClick: () -> Unit,
    onDetailsClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            onClick = onDistanceClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = BgBrand01
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = TextInvert
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = distance,
                color = TextInvert,
                style = styleBody2Semibold
            )
        }

        OutlinedButton(
            onClick = onDetailsClick,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = BgWhite
            ),
            border = BorderStroke(1.dp, Border03)
        ) {
            Text(
                text = "Details",
                color = BgBrand01,
                style = styleBody2Semibold
            )
        }
    }
}