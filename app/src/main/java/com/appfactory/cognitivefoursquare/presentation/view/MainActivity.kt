package com.appfactory.cognitivefoursquare.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.appfactory.cognitivefoursquare.R
import com.appfactory.cognitivefoursquare.domain.entity.VenueEntity
import com.appfactory.cognitivefoursquare.presentation.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val model = ViewModelProviders.of(this)[MainActivityViewModel::class.java]

        model.getVenues()

        model.venusObservableResourse.observe(this, Observer {
            setSuccessLayout(it)
        })

        model.errorObservableResourse.observe(this, Observer {
            Log.e("TAG", it)
        })
    }


    private fun setSuccessLayout(it: List<VenueEntity>) {
        val venuesAdapter = VenuesAdapter()
        rvVenues.adapter = venuesAdapter
        rvVenues.layoutManager = LinearLayoutManager(this)
        venuesAdapter.setVenues(it)
    }
}
