package com.appfactory.cognitivefoursquare.domain.interactor

import com.appfactory.cognitivefoursquare.data.repository.VenuesRepositoryImp
import com.appfactory.cognitivefoursquare.domain.entity.VenueEntity
import io.reactivex.Single

class GetVenusUseCase{
    fun buildUseCase(longLat:String):Single<List<VenueEntity>>{
        val venusRepo = VenuesRepositoryImp()
        return venusRepo.getVenues(longLat)
    }
}