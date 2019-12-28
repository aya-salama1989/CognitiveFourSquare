package com.appfactory.cognitivefoursquare.network

import com.appfactory.cognitivefoursquare.data.model.VenueImageResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface GetVenuesImageAPIInterface {

    @GET
    fun get(@Url url: String): Single<VenueImageResponse>
}