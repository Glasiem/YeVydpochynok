package com.example.yevydpochynok.models

import kotlinx.serialization.Serializable

@Serializable
data class Day (
    val day: String,
    val opens: String?,
    val closes: String?
)