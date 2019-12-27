package com.appfactory.cognitivefoursquare.data.repository

import com.appfactory.cognitivefoursquare.data.model.VenueImageResponse
import com.appfactory.cognitivefoursquare.data.model.toEntity
import com.appfactory.cognitivefoursquare.data.source.VenusRemoteDS
import com.appfactory.cognitivefoursquare.domain.entity.VenueEntity
import com.appfactory.cognitivefoursquare.domain.repository.VenuesRepository
import io.reactivex.Observable
import io.reactivex.Single

class VenuesRepositoryImp : VenuesRepository {
    override fun getVenues(longLat:String): Single<List<VenueEntity>> {
        val venusRs = VenusRemoteDS()
        return venusRs.getVenues(longLat= longLat)
            .map { venusRs -> venusRs.response.groups.first() }
            .flatMapObservable { group ->
                Observable.fromIterable(group.items.map { it.venue.toEntity("") })
            }.toList()
//            .flatMapSingle { item ->
//                venusRs.getVenuesPhoto(item.venue.id)
//                    .map {
//                        item.venue.toEntity(buildImageURL(it))
//                    }
//                    .onErrorReturn {
//                        item.venue.toEntity("")
//                    }
//            }.toList()
    }

    private fun buildImageURL(venueImageResponse: VenueImageResponse): String {
        val item = venueImageResponse.response.photos.items[0]
        return "${item.prefix}${item.width}x${item.height}${item.suffix}"
    }
}



