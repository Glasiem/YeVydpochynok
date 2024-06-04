package com.example.yevydpochynok.presentation.categories.category

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.yevydpochynok.data.UserData
import com.example.yevydpochynok.models.FoodPlaceFilters
import com.example.yevydpochynok.models.MuseumPlaceFilters
import com.example.yevydpochynok.models.ParkPlaceFilters
import com.example.yevydpochynok.models.Place
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.serialization.gson.gson
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
) : ViewModel(){
    var page: Int = 0
    var isLoading: Boolean = false
    var places: List<Place> = emptyList()
    val client = HttpClient(Android) {
        install(ContentNegotiation) {
            gson()
        }
    }

    fun fetchFoodPlaces(filter: FoodPlaceFilters) : List<Place> {
        isLoading = true
        val stringURL = "https://hereisrest.onrender.com/getFilteredFoodModels?offset=" + page.toString()
        val gson = Gson()
        val httpBody = gson.toJson(filter)
        Log.d("AAAAAAAAAAAAAA1", httpBody)
        Log.d("AAAAAAAAAAAAAA1", places.count().toString())
        places = runBlocking{
            var result = client.request{
                method = HttpMethod.Post
                url(stringURL)
                contentType(ContentType.Application.Json)
                setBody(httpBody)
            }.body<List<Place>>()
            result
        }
        Log.d("AAAAAAAAAAAAAA3", places.count().toString())
        isLoading = false
        return places
    }

    fun fetchParkPlaces(filter: ParkPlaceFilters) : List<Place> {
        isLoading = true
        // foodPlaces = []
//        print(filter)
        val stringURL = "https://hereisrest.onrender.com/getFilteredParkModels?offset=" + page.toString()
        val gson = Gson()
        val httpBody = gson.toJson(filter)
        places = runBlocking{
            var result = client.request{
                method = HttpMethod.Post
                url(stringURL)
                contentType(ContentType.Application.Json)
                setBody(httpBody)
            }.body<List<Place>>()
            result
        }
        isLoading = false
        return places
    }

    fun fetchMuseumPlaces(filter: MuseumPlaceFilters) : List<Place> {
        isLoading = true
        // foodPlaces = []
//        print(filter)
        val stringURL = "https://hereisrest.onrender.com/getFilteredMuseumModels?offset=" + page.toString()
        val gson = Gson()
        val httpBody = gson.toJson(filter)
        places = runBlocking{
            var result = client.request{
                method = HttpMethod.Post
                url(stringURL)
                contentType(ContentType.Application.Json)
                setBody(httpBody)
            }.body<List<Place>>()
            result
        }
        isLoading = false
        return places
    }

    fun prevPage() {
        if (page > 0) {
            page -= 1
//            fetchFoodPlaces(UserData.getInstance().getFoodPlaceFilter())
        }
    }

    fun nextPage() {
        Log.d("OOOOOOOOOOOOO", page.toString())
        page += 1
//        fetchFoodPlaces(UserData.getInstance().getFoodPlaceFilter())
    }
}