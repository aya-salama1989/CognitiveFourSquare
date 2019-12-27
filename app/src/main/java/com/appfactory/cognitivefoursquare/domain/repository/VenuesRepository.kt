package com.appfactory.cognitivefoursquare.domain.repository

import com.appfactory.cognitivefoursquare.domain.entity.VenueEntity
import io.reactivex.Single

interface VenuesRepository {
    fun getVenues(longLat:String): Single<List<VenueEntity>>
}