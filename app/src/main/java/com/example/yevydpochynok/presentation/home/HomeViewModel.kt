package com.example.yevydpochynok.presentation.home

import androidx.lifecycle.ViewModel
import com.example.yevydpochynok.domain.usecases.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases
) : ViewModel(){

    fun onEvent(event: HomeEvent){
//        when(event){
//            is HomeEvent.UpdateScrollValue ->
//        }
    }
}