package com.example.yevydpochynok.presentation.home.location.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable

@Composable
fun SingleSelectionList(options: List<SelectionOption>, onOptionClicked: (List<SelectionOption>, SelectionOption) -> Unit) {
    LazyColumn {
        items(options) { option -> SingleSelectionCard(options, option, onOptionClicked) }
    }
}