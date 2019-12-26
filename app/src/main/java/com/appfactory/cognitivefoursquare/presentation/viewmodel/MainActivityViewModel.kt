package com.appfactory.cognitivefoursquare.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appfactory.cognitivefoursquare.domain.entity.VenueEntity
import com.appfactory.cognitivefoursquare.domain.interactor.GetVenusUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel : ViewModel() {


    val venusObservableResourse = MutableLiveData<List<VenueEntity>>()
    val errorObservableResourse = MutableLiveData<String>()

    fun getVenues() {
        val venusUseCase = GetVenusUseCase()
        venusUseCase.buildUseCase().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                venusObservableResourse.value = it
            }
            .doOnError {
                errorObservableResourse.value = "adlkd"
                Log.e("TAG", it.localizedMessage) }
    }

}