package com.example.yevydpochynok.presentation.categories.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.yevydpochynok.models.enum.MuseumTheme

@Composable
fun MuseumPlaceFilterScreen(
    viewModel: MuseumPlaceFilterVM = hiltViewModel(),
    dismiss: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        header(viewModel, dismiss = dismiss)

        Text(
            text = "Фільтри",
            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(vertical = 16.dp)
        )

        ExpandableView(
            thumbnail = {
                Text("Тематика Музею", style = MaterialTheme.typography.h6)
            },
            expanded = {
                PlaceThematic(viewModel)
            }
        )

        Divider()

        FilterToggle(
            text = "Зручне розсташування",
            checked = viewModel.finePlaced.value,
            onCheckedChange = { viewModel.send(MuseumPlaceFilterVM.Event.OnToggleFinePlaced(it)) }
        )

        FilterToggle(
            text = "Зручне паркування",
            checked = viewModel.parking.value,
            onCheckedChange = { viewModel.send(MuseumPlaceFilterVM.Event.OnToggleParking(it)) }
        )

        FilterToggle(
            text = "Відвідування з тваринами",
            checked = viewModel.animalsAllowed.value,
            onCheckedChange = { viewModel.send(MuseumPlaceFilterVM.Event.OnToggleAnimalsAllowed(it)) }
        )

        FilterToggle(
            text = "Пристосовано для інвалідів",
            checked = viewModel.handicapAccessibility.value,
            onCheckedChange = { viewModel.send(MuseumPlaceFilterVM.Event.OnToggleHandicapAccessibility(it)) }
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                viewModel.send(MuseumPlaceFilterVM.Event.OnFiltersSubmit)
                dismiss()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50))
        ) {
            Text("Застосувати", color = Color.White, style = MaterialTheme.typography.h6)
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}


@Composable
private fun header(viewModel: MuseumPlaceFilterVM, dismiss: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {
                viewModel.send(MuseumPlaceFilterVM.Event.OnFiltersClear)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF00B22D))
        ) {
            Text(text = "Очистити",
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { dismiss() },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF00B22D))
        ) {
            Text(text = "Закрити",
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
fun ExpandableView(thumbnail: @Composable () -> Unit, expanded: @Composable () -> Unit) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .background(MaterialTheme.colors.surface, shape = MaterialTheme.shapes.small)
                .padding(16.dp)
        ) {
            thumbnail()
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background, shape = MaterialTheme.shapes.small)
                .padding(16.dp)
        ) {
            expanded()
        }
    }
}

@Composable
fun PlaceThematic(viewModel: MuseumPlaceFilterVM) {
    Column {
        MuseumTheme.getMuseumThematics().forEach { thematic ->
            Row {
                Checkbox(
                    checked = viewModel.thematic.value == thematic.internalValue,
                    onCheckedChange = {
                        viewModel.send(MuseumPlaceFilterVM.Event.OnSelectThematic(thematic.internalValue))
                    }
                )
                Text(thematic.displayName)
            }
        }
    }
}

@Composable
fun FilterToggle(text: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(MaterialTheme.colors.surface, shape = MaterialTheme.shapes.small)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text, style = MaterialTheme.typography.subtitle1)
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colors.primary
            )
        )
    }
}
