package com.appfactory.cognitivefoursquare.network

import com.appfactory.cognitivefoursquare.data.model.VenuesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface GetVenuesAPIInterface {

    @GET
    fun get(@Url url: String): Single<VenuesResponse>
}