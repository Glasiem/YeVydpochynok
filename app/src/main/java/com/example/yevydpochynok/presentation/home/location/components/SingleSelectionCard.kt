package com.example.yevydpochynok.presentation.home.location.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SingleSelectionCard(listOptions: List<SelectionOption>, selectionOption: SelectionOption, onOptionClicked: (List<SelectionOption>,SelectionOption) -> Unit) {
    Surface(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 8.dp, vertical = 4.dp)) {
        Surface(
            modifier = Modifier
                .border(1.dp, Color(0xFF00B22D), RoundedCornerShape(16.dp))
                .clickable(true, onClick = { onOptionClicked(listOptions,selectionOption) }),
            color = if (selectionOption.selected) { Color(0xFF00B22D) } else { MaterialTheme.colors.background },
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = selectionOption.option,
                    style = MaterialTheme.typography.body1,
                    color = if (selectionOption.selected) {Color.White} else {Color.Black}
                )
            }
        }
    }
}