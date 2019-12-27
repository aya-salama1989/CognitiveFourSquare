package com.appfactory.cognitivefoursquare.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appfactory.cognitivefoursquare.R
import com.appfactory.cognitivefoursquare.domain.entity.VenueEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_venue.view.*

class VenuesAdapter : RecyclerView.Adapter<VenuesAdapter.VenueViewHolder>() {

    private var venues: List<VenueEntity> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.item_venue, parent, false)
        return VenueViewHolder(v)
    }

    override fun getItemCount(): Int {
        return venues.size
    }

    override fun onBindViewHolder(holder: VenueViewHolder, position: Int) {
        holder.setVenueData(venues[position])
    }

    fun setVenues(venues: List<VenueEntity>) {
        this.venues = venues
        notifyDataSetChanged()
    }

    inner class VenueViewHolder(private val rootView: View) : RecyclerView.ViewHolder(rootView) {
        fun setVenueData(venueEntity: VenueEntity) {
            if(!venueEntity.image.isNullOrEmpty()){
                Picasso.get().load(venueEntity.image).placeholder(R.drawable.ic_launcher_background).into(rootView.ivVenue)
            }
            rootView.tvVenueName.text = venueEntity.name
            rootView.tvVenueAddress.text = venueEntity.address
        }
    }
}