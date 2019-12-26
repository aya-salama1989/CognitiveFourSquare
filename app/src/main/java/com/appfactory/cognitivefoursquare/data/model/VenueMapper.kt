package com.appfactory.cognitivefoursquare.data.model

import com.appfactory.cognitivefoursquare.domain.entity.VenueEntity

fun VenuesResponse.Group.Item.Venue.toEntity(imageURL:String)
        = VenueEntity( name = name, address = "${location.address} ${location.formattedAddress}", image = imageURL)