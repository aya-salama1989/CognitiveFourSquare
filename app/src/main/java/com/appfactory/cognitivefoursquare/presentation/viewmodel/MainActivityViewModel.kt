package com.appfactory.cognitivefoursquare.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appfactory.cognitivefoursquare.domain.entity.VenueEntity
import com.appfactory.cognitivefoursquare.domain.interactor.GetVenusUseCase
import com.appfactory.cognitivefoursquare.presentation.model.LocationLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {


    val venusObservable = MutableLiveData<List<VenueEntity>>()
    val errorObservable = MutableLiveData<Boolean>()
    private val locationData = LocationLiveData(application)


    fun getVenues(longLat:String) {
        val venusUseCase = GetVenusUseCase()
        venusUseCase.buildUseCase(longLat).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { venusObservable.value = it }
                , {
                    errorObservable.value = true
                    Log.e("TAG", it.localizedMessage)
                })


    }

    fun getLocationData()= locationData

}