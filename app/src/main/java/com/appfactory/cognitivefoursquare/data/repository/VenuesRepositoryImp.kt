package com.appfactory.cognitivefoursquare.data.repository

import com.appfactory.cognitivefoursquare.data.model.VenueImageResponse
import com.appfactory.cognitivefoursquare.data.model.toEntity
import com.appfactory.cognitivefoursquare.data.source.VenusRemoteDS
import com.appfactory.cognitivefoursquare.domain.entity.VenueEntity
import com.appfactory.cognitivefoursquare.domain.repository.VenuesRepository
import io.reactivex.Observable
import io.reactivex.Single

class VenuesRepositoryImp : VenuesRepository {
    override fun getVenues(): Single<List<VenueEntity>> {
        val venusRs = VenusRemoteDS()
        return venusRs.getVenues()
            .map { venusRes -> venusRes.response.groups.first() }
            .flatMapObservable { gp -> Observable.fromIterable(gp.items
                .map { item -> item.venue.toEntity("") }) }.toList()

//            .flatMapSingle { item ->
//                venusRs.getVenuesPhoto(item.venue.id)
//                    .map { venusImagRs -> item.venue.toEntity(buildImageURL(venusImagRs)) }
//            }.toList()
    }

    private fun buildImageURL(venueImageResponse: VenueImageResponse): String {
        val item = venueImageResponse.response.photos.items[0]
        return "${item.prefix}${item.width}x${item.height}${item.suffix}"
    }
}



