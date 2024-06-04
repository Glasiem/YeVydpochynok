package com.example.yevydpochynok.presentation.categories.filter

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.yevydpochynok.data.UserData
import com.example.yevydpochynok.models.MuseumPlaceFilters
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MuseumPlaceFilterVM @Inject constructor() : ViewModel() {
    val thematic = mutableStateOf<String?>(null)
    var finePlaced = mutableStateOf(false)
    var parking = mutableStateOf(false)
    var animalsAllowed = mutableStateOf(false)
    var handicapAccessibility = mutableStateOf(false)

    init {
        thematic.value = UserData.getInstance().getMuseumPlaceFilter().thematic
        finePlaced.value = UserData.getInstance().getMuseumPlaceFilter().finePlaced
        parking.value = UserData.getInstance().getMuseumPlaceFilter().parking
        animalsAllowed.value = UserData.getInstance().getMuseumPlaceFilter().animalsAllowed
        handicapAccessibility.value = UserData.getInstance().getMuseumPlaceFilter().handicapAccessibility
    }

    fun send(event: Event) {
        when (event) {
            Event.OnAppear -> println("FilterView Appear Event")
            Event.OnFiltersClear -> clearFilters()
            Event.OnFiltersSubmit -> submitFilters()
            is Event.OnSelectThematic -> onSelectThematic(event.value)
            is Event.OnToggleFinePlaced -> onToggleFinePlaced(event.toggle)
            is Event.OnToggleParking -> onToggleParking(event.toggle)
            is Event.OnToggleAnimalsAllowed -> onToggleAnimalsAllowed(event.toggle)
            is Event.OnToggleHandicapAccessibility -> onToggleHandicapAccessibility(event.toggle)
        }
    }

    private fun onSelectThematic(value: String) {
        if (!thematic.equals(value)) {
            thematic.value = value
        }
    }

    private fun onToggleFinePlaced(toggle: Boolean) {
        finePlaced.value = toggle
    }

    private fun onToggleParking(toggle: Boolean) {
        parking.value = toggle
    }

    private fun onToggleAnimalsAllowed(toggle: Boolean) {
        animalsAllowed.value = toggle
    }

    private fun onToggleHandicapAccessibility(toggle: Boolean) {
        handicapAccessibility.value = toggle
    }

    fun submitFilters() {
        println("thematics:")
        println(thematic)
        val museumPlaceFilters = MuseumPlaceFilters(
            thematic = thematic.value,
            finePlaced = finePlaced.value,
            parking = parking.value,
            animalsAllowed = animalsAllowed.value,
            handicapAccessibility = handicapAccessibility.value,
            city = UserData.getInstance().getCity() ?: "Kyiv"
        )
        UserData.getInstance().setMuseumPlaceFilter(museumPlaceFilters)
    }

    fun clearFilters() {
        println("invoke 1")
        UserData.getInstance().setMuseumPlaceFilter(MuseumPlaceFilters.getDefault(UserData.getInstance().getCity()))
        thematic.value = UserData.getInstance().getMuseumPlaceFilter().thematic
        finePlaced.value = UserData.getInstance().getMuseumPlaceFilter().finePlaced
        parking.value = UserData.getInstance().getMuseumPlaceFilter().parking
        animalsAllowed.value = UserData.getInstance().getMuseumPlaceFilter().animalsAllowed
        handicapAccessibility.value = UserData.getInstance().getMuseumPlaceFilter().handicapAccessibility
    }

    sealed class Event {
        object OnAppear : Event()
        object OnFiltersClear : Event()
        object OnFiltersSubmit : Event()
        data class OnSelectThematic(val value: String) : Event()
        data class OnToggleFinePlaced(val toggle: Boolean) : Event()
        data class OnToggleParking(val toggle: Boolean) : Event()
        data class OnToggleAnimalsAllowed(val toggle: Boolean) : Event()
        data class OnToggleHandicapAccessibility(val toggle: Boolean) : Event()
    }
}

