package com.example.pulsepoint.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pulsepoint.style.BgBrand01
import com.example.pulsepoint.style.BgBrand03
import com.example.pulsepoint.style.Text03
import com.example.pulsepoint.style.styleBody2Medium

@Composable
fun LocationSearchField(
    location: String = "Bengaluru, karnataka (India)",
    onLocationChange: (String) -> Unit = {},
    onLocationSearchClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = BgBrand03)
    ) {
        OutlinedTextField(
            value = location,
            onValueChange = onLocationChange,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
            textStyle = styleBody2Medium,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
            trailingIcon = {
                IconButton(onClick = onLocationSearchClick) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Location",
                        tint = BgBrand01
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFE8E6FF)
@Composable
fun LocationSearchFieldPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE8E6FF))
            .padding(16.dp)
    ) {
        LocationSearchField()
    }
}