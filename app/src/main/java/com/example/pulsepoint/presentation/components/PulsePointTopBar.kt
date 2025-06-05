package com.example.pulsepoint.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pulsepoint.style.BgBrand01
import com.example.pulsepoint.style.Icon01
import com.example.pulsepoint.style.Purple100
import com.example.pulsepoint.style.Text01
import com.example.pulsepoint.style.Text03
import com.example.pulsepoint.style.styleBody1Semibold
import com.example.pulsepoint.style.styleBody2Medium
import com.example.pulsepoint.style.styleBody2Regular
import com.example.pulsepoint.R


@Preview
@Composable
fun PulsePointTopBar(
    title: String = "Select blood type"
    ) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Purple100,
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            )
    ) {
        Text(
            modifier = Modifier
                .padding(24.dp)
                .align(Alignment.Center),
            text = title,
            style = styleBody1Semibold,
            color = Text01
        )
    }
}


@Preview
@Composable
fun SuccessTopBar(
    title: String = "Search Blood Banks",
    onBackClick: () -> Unit = {},
    location: String = "Bengaluru, karnataka (India)"
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Purple100,
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            )
    ) {
        // Top Row with Back Button and Title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Back",
                    tint = Icon01
                )
            }

            Text(
                text = title,
                style = styleBody1Semibold,
                color = Text01
            )
        }

        // Location Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = location,
                    style = styleBody2Medium,
                    color = Text03,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    painter = painterResource(R.drawable.ic_mark),
                    contentDescription = "Location Target",
                    tint = BgBrand01,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}