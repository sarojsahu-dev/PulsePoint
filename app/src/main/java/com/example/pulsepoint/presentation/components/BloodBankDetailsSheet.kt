package com.example.pulsepoint.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pulsepoint.data.models.BloodBank
import com.example.pulsepoint.style.BgBrand01
import com.example.pulsepoint.style.Text01
import com.example.pulsepoint.style.Text03
import com.example.pulsepoint.style.Text04
import com.example.pulsepoint.style.styleBody1Semibold
import com.example.pulsepoint.style.styleBody2Medium
import com.example.pulsepoint.style.styleBody2Semibold
import com.example.pulsepoint.style.styleBody3Semibold

@Composable
fun BloodBankDetailsSheet(
    hospitalName: String = "CMR Institute of Medical Sciences and hospitals",
    hospitalType: String = "Private",
    bloodBank: BloodBank,
    address: String = "CMR Institute of Medical Sciences and hospitals, Vinanapura, Bnagalore Urban, Karnataka",
    phoneNumber: String = "9972399007",
    onCloseClick: () -> Unit = {},
    onViewInMaps: () -> Unit = {},
    onCallNow: () -> Unit = {}
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Header with title and close button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Details",
                    style = styleBody2Semibold,
                    color = Text01
                )

                IconButton(
                    onClick = onCloseClick
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Spacer(Modifier.height(16.dp))
            TypeChip(text = hospitalType)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = hospitalName,
                style = styleBody1Semibold,
                color = Text01
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Blood Availability",
                style = styleBody3Semibold,
                color = Text04
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(bloodBank.bloodTypes.entries.toList()) { entry ->
                    BloodTypeChip(key = entry.key, quantity = entry.value.toString())
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Address section
            Text(
                text = "Address",
                style = styleBody3Semibold,
                color = Text01
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = address,
                style = styleBody2Medium,
                color = Text03
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.clickable { onViewInMaps() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "View in maps",
                    style = styleBody2Semibold,
                    color = BgBrand01
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location",
                    tint = BgBrand01,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Contact section
            Text(
                text = "Contact",
                style = styleBody3Semibold,
                color = Text01
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = phoneNumber,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Call now
            Row(
                modifier = Modifier.clickable { onCallNow() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Call now",
                    style = styleBody2Semibold,
                    color = BgBrand01
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = "Phone",
                    tint = BgBrand01,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(Modifier.height(48.dp))

            Button(
                onClick = onCloseClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BgBrand01
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Close",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(Modifier.height(24.dp))

        }
    }
}