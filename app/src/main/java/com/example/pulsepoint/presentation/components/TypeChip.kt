package com.example.pulsepoint.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pulsepoint.style.BgBrand02
import com.example.pulsepoint.style.Text01
import com.example.pulsepoint.style.styleBody3Medium


@Composable
fun TypeChip(text: String){
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = BgBrand02
        )
    ) {
        Text(
            text = text,
            style = styleBody3Medium,
            color = Text01,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp)
        )
    }
}