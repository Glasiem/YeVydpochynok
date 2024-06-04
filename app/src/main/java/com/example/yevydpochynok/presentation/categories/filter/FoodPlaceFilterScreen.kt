package com.example.yevydpochynok.presentation.categories.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.yevydpochynok.models.enum.FoodPlaceFoodCountries
import com.example.yevydpochynok.models.enum.FoodPlaceFoodTypes
import com.example.yevydpochynok.models.enum.FoodPlaceServices
import com.example.yevydpochynok.models.enum.FoodPlaceType

@Composable
fun FoodPlaceFilterScreen(viewModel: FoodPlaceFilterVM, dismiss: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        header(viewModel, dismiss = dismiss)

        Divider()

        Text(
            text = "Фільтри",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Column (modifier = Modifier.fillMaxWidth()
            .navigationBarsPadding()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .fillMaxHeight(1.0f)
        ){

            Text(text =  "Типи ресторанів")
            placeTypesFilter(viewModel = viewModel)

            Text("Країни їжі")
            foodCountriesFilter(viewModel = viewModel)

            Text("Типи їжі")
            foodTypesFilter(viewModel = viewModel)

            Text("Сервіси")
            servicesFilter(viewModel = viewModel)
            Divider()

            accessibilityFilters(viewModel = viewModel)
            applyFiltersButton(viewModel = viewModel, dismiss = dismiss)
            Spacer(modifier = Modifier.height(20.dp))
        }

    }
}

@Composable
private fun accessibilityFilters(viewModel: FoodPlaceFilterVM) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Row {
            Switch(
                checked = viewModel.finePlaced.value,
                onCheckedChange = { isToggled ->
                    viewModel.send(Event.OnToggleFinePlaced(isToggled))
                }
            )
            Text("Зручне розсташування",
                modifier = Modifier.align(Alignment.CenterVertically).padding(start = 4.dp))
        }

        Row {
            Switch(
                checked = viewModel.parking.value,
                onCheckedChange = { isToggled ->
                    viewModel.send(Event.OnToggleParking(isToggled))
                }
            )
            Text("Зручне паркування",
                modifier = Modifier.align(Alignment.CenterVertically).padding(start = 4.dp))
        }

        Row {
            Switch(
                checked = viewModel.animalsAllowed.value,
                onCheckedChange = { isToggled ->
                    viewModel.send(Event.OnToggleAnimalsAllowed(isToggled))
                }
            )
            Text("Відвідування з тваринами",
                modifier = Modifier.align(Alignment.CenterVertically).padding(start = 4.dp))
        }

        Row {
            Switch(
                checked = viewModel.handicapAccessibility.value,
                onCheckedChange = { isToggled ->
                    viewModel.send(Event.OnToggleHandicapAccessibility(isToggled))
                }
            )
            Text("Пристосовано для інвалідів",
                modifier = Modifier.align(Alignment.CenterVertically).padding(start = 4.dp))
        }
    }
}

@Composable
private fun placeTypesFilter(viewModel: FoodPlaceFilterVM) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        FoodPlaceType.getFoodPlaceTypes().forEach { place ->
            Row {
                Checkbox(
                    checked = viewModel.placeTypes.any { it == place.internalValue },
                    onCheckedChange = { isToggled ->
                        viewModel.send(Event.OnTogglePlaceTypes(isToggled, place.internalValue))
                    }
                )
                Text(place.displayName,
                    modifier = Modifier.align(Alignment.CenterVertically))
            }
        }
    }
}

@Composable
private fun foodCountriesFilter(viewModel: FoodPlaceFilterVM) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        FoodPlaceFoodCountries.getFoodPlaceFoodCountries().forEach { country ->
            Row {
                Checkbox(
                    checked = viewModel.foodCountries.any { it == country.internalValue },
                    onCheckedChange = { isToggled ->
                        viewModel.send(
                            Event.OnTogglePlaceCountries(
                                isToggled,
                                country.internalValue
                            )
                        )
                    }
                )
                Text(country.displayName,
                    modifier = Modifier.align(Alignment.CenterVertically))
            }
        }
    }
}

@Composable
private fun foodTypesFilter(viewModel: FoodPlaceFilterVM) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        FoodPlaceFoodTypes.getFoodPlaceFoodTypes().forEach { type ->
            Row {
                Checkbox(
                    checked = viewModel.foodTypes.any { it == type.internalValue },
                    onCheckedChange = { isToggled ->
                        viewModel.send(Event.OnTogglePlaceFoodTypes(isToggled, type.internalValue))
                    }
                )
                Text(type.displayName,
                    modifier = Modifier.align(Alignment.CenterVertically))
            }
        }
    }
}

@Composable
private fun servicesFilter(viewModel: FoodPlaceFilterVM) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        FoodPlaceServices.getFoodPlaceServices().forEach { service ->
            Row {
                Checkbox(
                    checked = viewModel.services.any { it == service.internalValue },
                    onCheckedChange = { isToggled ->
                        viewModel.send(
                            Event.OnTogglePlaceServices(
                                isToggled,
                                service.internalValue
                            )
                        )
                    }
                )
                Text(service.displayName,
                    modifier = Modifier.align(Alignment.CenterVertically))
            }
        }
    }
}

@Composable
private fun header(viewModel: FoodPlaceFilterVM, dismiss: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {
                viewModel.send(Event.OnFiltersClear)
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
private fun applyFiltersButton(viewModel: FoodPlaceFilterVM, dismiss: () -> Unit) {
    Button(
        onClick = {
            viewModel.send(Event.OnFiltersSubmit)
            dismiss()
        },
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF00B22D))
    ) {
        Text(
            text = "Застосувати",
            style = MaterialTheme.typography.h6,
            color = Color.White
        )
    }
}
