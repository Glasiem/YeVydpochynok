package com.example.yevydpochynok.data

import com.example.yevydpochynok.models.FoodPlaceFilters
import com.example.yevydpochynok.models.MuseumPlaceFilters
import com.example.yevydpochynok.models.ParkPlaceFilters
import com.example.yevydpochynok.models.Place
import com.example.yevydpochynok.models.RestPlan
import com.example.yevydpochynok.models.RestPlanFilters

class UserData public constructor() {
    private var city: String = "Kyiv"
    private var foodPlaceFilters: FoodPlaceFilters = FoodPlaceFilters.getDefault(city)
    private var parkPlaceFilters: ParkPlaceFilters = ParkPlaceFilters.getDefault(city)
    private var museumPlaceFilters: MuseumPlaceFilters = MuseumPlaceFilters.getDefault(city)
    private var details: Place? = null
    private var questionIndex: Int = 0
    private var isQuizFinished: Boolean = false
    var planFilters: RestPlanFilters = RestPlanFilters.getDefault(city)
    var foodPlace: Place? = null
    var parkPlace: Place? = null
    var museumPlace: Place? = null
    var plan: RestPlan? = null

    companion object{
        @Volatile
        private var instance: UserData? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: UserData().also { instance = it }
            }

    }

    fun getCity():String{
        return city
    }

    fun setCity(city: String){
        this.city = city
    }

    fun getDetails(): Place? {
        return details
    }

    fun setDetails(details: Place){
        this.details = details
    }

    fun getFoodPlaceFilter():FoodPlaceFilters{
        return foodPlaceFilters
    }

    fun setFoodPlaceFilter(filter: FoodPlaceFilters){
        this.foodPlaceFilters = filter
    }

    fun getParkPlaceFilter():ParkPlaceFilters{
        return parkPlaceFilters
    }

    fun setParkPlaceFilter(filter: ParkPlaceFilters){
        this.parkPlaceFilters = filter
    }

    fun getMuseumPlaceFilter():MuseumPlaceFilters{
        return museumPlaceFilters
    }

    fun setMuseumPlaceFilter(filter: MuseumPlaceFilters){
        this.museumPlaceFilters = filter
    }

    fun getQuestionIndex():Int{
        return questionIndex
    }

    fun setQuestionIndex(questionIndex: Int){
        this.questionIndex = questionIndex
    }

    fun getIsQuizFinished():Boolean{
        return isQuizFinished
    }

    fun setIsQuizFinished(isQuizFinished: Boolean){
        this.isQuizFinished = isQuizFinished
    }

}