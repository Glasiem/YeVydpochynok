package com.example.yevydpochynok.presentation.home.location

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.yevydpochynok.data.Cities
import com.example.yevydpochynok.data.UserData
import com.example.yevydpochynok.presentation.home.location.components.LocationTopBar
import com.example.yevydpochynok.presentation.home.location.components.SelectionOption
import com.example.yevydpochynok.presentation.home.location.components.SingleSelectionList

@Composable
fun LocationScreen (
    navigateUp: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        LocationTopBar (onBackClick = navigateUp)
        val listOptions = listOf(
            SelectionOption("Kyiv", UserData.getInstance().getCity() == "Kyiv"),
            SelectionOption("Lviv", UserData.getInstance().getCity() == "Lviv")
        ).toMutableStateList()
        SingleSelectionList(listOptions,::selectCard)
    }
}

fun selectCard (listOptions: List<SelectionOption>, selectedOption: SelectionOption){
    listOptions.forEach { it.selected = false }
    listOptions.find { it.option == selectedOption.option }?.selected = true
    UserData.getInstance().setCity(selectedOption.option)
}