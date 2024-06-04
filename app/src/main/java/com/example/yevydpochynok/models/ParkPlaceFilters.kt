package com.example.yevydpochynok.models

import kotlinx.serialization.Serializable

@Serializable
class ParkPlaceFilters (
    val playground: Boolean,
    val picnic: Boolean,
    val hiking: Boolean,
    val sport: Boolean,
    val cycling: Boolean,
    val city: String,
    val finePlaced: Boolean,
    val parking: Boolean,
    val animalsAllowed: Boolean,
    val handicapAccessibility: Boolean
) {
    companion object {
        fun getDefault(city: String? = null): ParkPlaceFilters {
            return ParkPlaceFilters(
                playground = false,
                picnic = false,
                hiking = false,
                sport = false,
                cycling = false,
                city = city ?: "Kyiv",
                finePlaced = false,
                parking = false,
                animalsAllowed = false,
                handicapAccessibility = false
            )
        }
    }
}
