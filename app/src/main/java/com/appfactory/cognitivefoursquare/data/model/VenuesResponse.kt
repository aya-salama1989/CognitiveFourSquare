package com.appfactory.cognitivefoursquare.data.model

data class VenuesResponse(
    val meta: Meta,
    val response: Response
)

{

data class Meta(
    val code: Int,
    val requestId: String
)

data class Response(
    val geocode: Geocode,
    val groups: List<Group>,
    val headerFullLocation: String,
    val headerLocation: String,
    val headerLocationGranularity: String,
    val suggestedBounds: SuggestedBounds,
    val suggestedFilters: SuggestedFilters,
    val totalResults: Int
)


data class Geocode(
    val cc: String,
    val center: Center,
    val displayString: String,
    val geometry: Geometry,
    val longId: String,
    val slug: String,
    val what: String,
    val `where`: String
) {
    data class Center(
        val lat: Double,
        val lng: Double
    )

    data class Geometry(
        val bounds: Bounds
    ) {
        data class Bounds(
            val ne: Ne,
            val sw: Sw
        ) {
            data class Ne(
                val lat: Double,
                val lng: Double
            )

            data class Sw(
                val lat: Double,
                val lng: Double
            )
        }
    }
}

data class Group(
    val items: List<Item>,
    val name: String,
    val type: String
) {
    data class Item(
        val flags: Flags,
        val reasons: Reasons,
        val referralId: String,
        val venue: Venue
    ) {
        data class Flags(
            val outsideRadius: Boolean
        )

        data class Reasons(
            val count: Int,
            val items: List<Item>
        ) {
            data class Item(
                val reasonName: String,
                val summary: String,
                val type: String
            )
        }

        data class Venue(
            val categories: List<Category>,
            val id: String,
            val location: Location,
            val name: String,
            val photos: Photos
        ) {
            data class Category(
                val icon: Icon,
                val id: String,
                val name: String,
                val pluralName: String,
                val primary: Boolean,
                val shortName: String
            ) {
                data class Icon(
                    val prefix: String,
                    val suffix: String
                )
            }

            data class Location(
                val address: String,
                val cc: String,
                val city: String,
                val country: String,
                val crossStreet: String,
                val formattedAddress: List<String>,
                val labeledLatLngs: List<LabeledLatLng>,
                val lat: Double,
                val lng: Double,
                val state: String
            ) {
                data class LabeledLatLng(
                    val label: String,
                    val lat: Double,
                    val lng: Double
                )
            }

            data class Photos(
                val count: Int,
                val groups: List<Any>
            )
        }
    }
}

data class SuggestedBounds(
    val ne: Ne,
    val sw: Sw
) {
    data class Ne(
        val lat: Double,
        val lng: Double
    )

    data class Sw(
        val lat: Double,
        val lng: Double
    )
}

data class SuggestedFilters(
    val filters: List<Filter>,
    val header: String
) {
    data class Filter(
        val key: String,
        val name: String
    )
}

}