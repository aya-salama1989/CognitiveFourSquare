package com.appfactory.cognitivefoursquare.network

import com.appfactory.cognitivefoursquare.BuildConfig
import com.appfactory.cognitivefoursquare.data.model.VenueImageResponse
import com.appfactory.cognitivefoursquare.data.model.VenuesResponse
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitBuilder {

    private fun getRetrofitInstance(): Retrofit {
        val httpClient = OkHttpClient.Builder()
        if(BuildConfig.DEBUG){
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build()).build()
    }

    fun getVenues(url: String): Single<VenuesResponse> {
        val venusService = getRetrofitInstance().create(GetVenuesAPIInterface::class.java)
        return venusService.get(url)
    }

    fun getVenuesImage(url: String): Single<VenueImageResponse> {
        val venusService = getRetrofitInstance().create(GetVenuesImageAPIInterface::class.java)
        return venusService.get(url)
    }


    companion object{
       private const val BASE_URL = "https://api.foursquare.com/"
    }
}