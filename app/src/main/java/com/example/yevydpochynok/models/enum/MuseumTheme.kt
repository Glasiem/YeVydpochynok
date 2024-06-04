package com.example.yevydpochynok.models.enum

data class MuseumTheme(
    val internalValue: String,
    val displayName: String
) {
    companion object {
        fun getMuseumThematics(): List<MuseumTheme> {
            return listOf(
                MuseumTheme(internalValue = "HISTORICAL", displayName = "Історична"),
                MuseumTheme(internalValue = "ARCHAEOLOGICAL", displayName = "Ахреологічна"),
                MuseumTheme(internalValue = "LOCAL_HISTORY", displayName = "Локальної історії"),
                MuseumTheme(internalValue = "NATURAL", displayName = "Природнича"),
                MuseumTheme(internalValue = "LITERARY", displayName = "Літературна"),
                MuseumTheme(internalValue = "MEMORIAL", displayName = "Меморіальна"),
                MuseumTheme(internalValue = "ARTISTIC", displayName = "Артистична"),
                MuseumTheme(internalValue = "ETHNOGRAPHIC", displayName = "Етнографічна"),
                MuseumTheme(internalValue = "SCIENTIFIC", displayName = "Наукова")
            )
        }
    }
}