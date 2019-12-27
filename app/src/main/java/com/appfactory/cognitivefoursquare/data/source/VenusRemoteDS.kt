package com.appfactory.cognitivefoursquare.data.source

import com.appfactory.cognitivefoursquare.data.model.VenueImageResponse
import com.appfactory.cognitivefoursquare.data.model.VenuesResponse
import com.appfactory.cognitivefoursquare.network.RetrofitBuilder
import io.reactivex.Single


class VenusRemoteDS {

    fun getVenues(cityName: String = "", longLat: String): Single<VenuesResponse> {
        val retrofitBuilder = RetrofitBuilder()
        return retrofitBuilder
            .getVenues(
                "v2/venues/explore?" +
                        getLongLat(longLat) +
                        getCity(cityName) +
                        "client_id=$CLIENT_ID&client_secret=$CLIENT_SECRET&v=$API_RELEASE_DATE"
            )

    }

    fun getVenuesPhoto(venueId: String): Single<VenueImageResponse> {
        val retrofitBuilder = RetrofitBuilder()
        return retrofitBuilder
            .getVenuesImage("v2/venues/${venueId}/photos?client_id=$CLIENT_ID" +
                    "&client_secret=$CLIENT_SECRET&v=$API_RELEASE_DATE")
    }

    private fun getLongLat(longLat: String): String {
        return if (!longLat.isNullOrEmpty()) {
            "ll=$longLat&"
        } else {
            ""
        }
    }

    private fun getCity(cityName: String): String {
        return if (!cityName.isNullOrEmpty()) {
            "near=$cityName&"
        } else {
            ""
        }
    }

    companion object {
        private const val CLIENT_ID = "4WYRFP3FW1BTAZAGJZA24ETRDUVNDELXULCUW0XPQXRSW30L"
        private const val CLIENT_SECRET = "VUP1M05EADCJEVKLD054LKGIJSXFV2IDDMCZXVFCMY3PG3DY"
        private const val API_RELEASE_DATE = "20191224"
    }
}