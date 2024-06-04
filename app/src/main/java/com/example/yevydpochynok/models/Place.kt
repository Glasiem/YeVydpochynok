package com.example.yevydpochynok.models

import kotlinx.serialization.Serializable

@Serializable
data class Place (
    val id: String,
    val name: String,
    val description: String,
    val rating: Float,
    val website: String?,
    val address: String,
    val contact: String?,
    val parking: Boolean,
    val animalsAllowed: Boolean,
    val paidVisit: Boolean,
    val wc: Boolean,
    val finePlaced: Boolean,
    val handicapAccessibility: Boolean,
    val hours: Array<Day>?,
    val images: Array<String>,
    val location: String,
    val longitude: String,
    val latitude: String,
    val city: String,
    val subscriptionPlan: String,
    val active: Boolean
)