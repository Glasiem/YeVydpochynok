package com.example.yevydpochynok.presentation.categories.filter

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.yevydpochynok.data.UserData
import com.example.yevydpochynok.models.FoodPlaceFilters
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FoodPlaceFilterVM @Inject constructor(
) : ViewModel() {
        val placeTypes = mutableStateListOf<String>()
        val foodCountries = mutableStateListOf<String>()
        val foodTypes = mutableStateListOf<String>()
        val services = mutableStateListOf<String>()
        var alcohol = false
        var vip = false
        var parking = mutableStateOf(false)
        var finePlaced = mutableStateOf(false)
        var animalsAllowed = mutableStateOf(false)
        var handicapAccessibility = mutableStateOf(false)

        init {
            placeTypes.addAll(UserData.getInstance().getFoodPlaceFilter().placeTypes)
            foodCountries.addAll(UserData.getInstance().getFoodPlaceFilter().foodCountries)
            foodTypes.addAll(UserData.getInstance().getFoodPlaceFilter().foodTypes)
            services.addAll(UserData.getInstance().getFoodPlaceFilter().services)
            alcohol = UserData.getInstance().getFoodPlaceFilter().alcohol
            vip = UserData.getInstance().getFoodPlaceFilter().vip
            parking.value = UserData.getInstance().getFoodPlaceFilter().parking
            finePlaced.value = UserData.getInstance().getFoodPlaceFilter().finePlaced
            animalsAllowed.value = UserData.getInstance().getFoodPlaceFilter().animalsAllowed
            handicapAccessibility.value = UserData.getInstance().getFoodPlaceFilter().handicapAccessibility
        }

        fun send(event: Event) {
            when (event) {
                Event.OnAppear -> println("FilterView Appear Event")
                Event.OnFiltersClear -> clearFilters()
                Event.OnFiltersSubmit -> submitFilters()
                is Event.OnTogglePlaceTypes -> onTogglePlaceTypes(event.toggle, event.value)
                is Event.OnTogglePlaceCountries -> onTogglePlaceCountries(event.toggle, event.value)
                is Event.OnTogglePlaceServices -> onTogglePlaceServices(event.toggle, event.value)
                is Event.OnTogglePlaceFoodTypes -> onTogglePlaceFoodTypes(event.toggle, event.value)
                is Event.OnToggleFinePlaced -> OnToggleFinePlaced(event.toggle)
                is Event.OnToggleParking -> OnToggleParking(event.toggle)
                is Event.OnToggleAnimalsAllowed -> OnToggleAnimalsAllowed(event.toggle)
                is Event.OnToggleHandicapAccessibility -> OnToggleHandicapAccessibility(event.toggle)
            }
        }

        private fun onTogglePlaceTypes(toggle: Boolean, value: String) {
            if (toggle) placeTypes.add(value) else placeTypes.remove(value)
        }

        private fun onTogglePlaceServices(toggle: Boolean, value: String) {
            if (toggle) services.add(value) else services.remove(value)
        }

        private fun onTogglePlaceFoodTypes(toggle: Boolean, value: String) {
            if (toggle) foodTypes.add(value) else foodTypes.remove(value)
        }

        private fun onTogglePlaceCountries(toggle: Boolean, value: String) {
            if (toggle) foodCountries.add(value) else foodCountries.remove(value)
        }

        private fun OnToggleFinePlaced(toggle: Boolean) {
            finePlaced.value = toggle;
        }

        private fun OnToggleParking(toggle: Boolean) {
            parking.value = toggle;
        }

        private fun OnToggleAnimalsAllowed(toggle: Boolean) {
            animalsAllowed.value = toggle;
        }

        private fun OnToggleHandicapAccessibility(toggle: Boolean) {
            handicapAccessibility.value = toggle;
        }

        fun submitFilters() {
            println("place types:")
            println(placeTypes)
            var foodPlaceFilters = FoodPlaceFilters(
                placeTypes = placeTypes.toList(),
                foodCountries = foodCountries.toList(),
                foodTypes = foodTypes.toList(),
                services = services.toList(),
                alcohol = alcohol,
                vip = vip,
                parking = parking.value,
                finePlaced = finePlaced.value,
                animalsAllowed = animalsAllowed.value,
                handicapAccessibility = handicapAccessibility.value,
                city = UserData.getInstance().getCity() ?: "Kyiv"
            )
            UserData.getInstance().setFoodPlaceFilter(foodPlaceFilters)
        }

        fun clearFilters() {
            println("invoke 1")
            UserData.getInstance().setFoodPlaceFilter(FoodPlaceFilters.getDefault(UserData.getInstance().getCity()))
            placeTypes.addAll(UserData.getInstance().getFoodPlaceFilter().placeTypes)
            foodCountries.addAll(UserData.getInstance().getFoodPlaceFilter().foodCountries)
            foodTypes.addAll(UserData.getInstance().getFoodPlaceFilter().foodTypes)
            services.addAll(UserData.getInstance().getFoodPlaceFilter().services)
            alcohol = UserData.getInstance().getFoodPlaceFilter().alcohol
            vip = UserData.getInstance().getFoodPlaceFilter().vip
            parking.value = UserData.getInstance().getFoodPlaceFilter().parking
            finePlaced.value = UserData.getInstance().getFoodPlaceFilter().finePlaced
            animalsAllowed.value = UserData.getInstance().getFoodPlaceFilter().animalsAllowed
            handicapAccessibility.value = UserData.getInstance().getFoodPlaceFilter().handicapAccessibility
        }
    }

    sealed class Event {
        object OnAppear : Event()
        object OnFiltersClear : Event()
        object OnFiltersSubmit : Event()
        data class OnTogglePlaceTypes(val toggle: Boolean, val value: String) : Event()
        data class OnTogglePlaceCountries(val toggle: Boolean, val value: String) : Event()
        data class OnTogglePlaceServices(val toggle: Boolean, val value: String) : Event()
        data class OnTogglePlaceFoodTypes(val toggle: Boolean, val value: String) : Event()
        data class OnToggleFinePlaced(val toggle: Boolean) : Event()
        data class OnToggleParking(val toggle: Boolean) : Event()
        data class OnToggleAnimalsAllowed(val toggle: Boolean) : Event()
        data class OnToggleHandicapAccessibility(val toggle: Boolean) : Event()
    }