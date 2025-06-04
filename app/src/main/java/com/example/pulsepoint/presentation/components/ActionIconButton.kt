package com.example.pulsepoint.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pulsepoint.style.BgBrand01
import com.example.pulsepoint.style.BgWhite
import com.example.pulsepoint.style.Border03

@Composable
fun ActionIconButton(
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit = {},
    iconColor: Color = BgBrand01,
    backgroundColor: Color = BgWhite
) {
    Card(
        modifier = Modifier.size(36.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        border = BorderStroke(1.dp, Border03)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = onClick,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = contentDescription,
                    tint = iconColor,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F4FF)
@Composable
fun GenericIconButtonPreview() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F4FF))
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        ActionIconButton(
            icon = Icons.Default.Search,
            contentDescription = "Search"
        )

        ActionIconButton(
            icon = Icons.Default.LocationOn,
            contentDescription = "Location"
        )

    }
}