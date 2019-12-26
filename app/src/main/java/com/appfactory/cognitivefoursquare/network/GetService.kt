package com.appfactory.cognitivefoursquare.network

import com.appfactory.cognitivefoursquare.data.model.VenuesResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface GetService {


    @GET
    fun get(@Url url:String):Single<Any>


//    @GET("explore?ll={location}&near={place}&client_id={clientId}&client_secret={clientSecret}&v=20191224")
//    fun getVenues(@Path("location") location: String,@Path("place") place: String)
//            : Observable<VenuesResponse>
//
//    @GET("{venueId}/photos?client_id={clientId}&client_secret={clientSecret}&v=20191224")
//    fun getVenueImage(@Path("venueId") venueId:String ): Observable<VenueImageResponse>
}