package com.example.yevydpochynok.models

import kotlinx.serialization.Serializable

@Serializable
class MuseumPlaceFilters (
    val thematic: String?,
    val city: String,
    val finePlaced: Boolean,
    val parking: Boolean,
    val animalsAllowed: Boolean,
    val handicapAccessibility: Boolean
){
    companion object {
        fun getDefault(city: String? = null): MuseumPlaceFilters {
            return MuseumPlaceFilters(
                thematic = null,
                city = city ?: "Kyiv",
                finePlaced = false,
                parking = false,
                animalsAllowed = false,
                handicapAccessibility = false
            )
        }
    }
}