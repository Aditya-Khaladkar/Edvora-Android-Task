package com.example.edvoratask

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.edvoratask.adapter.RideAdapter
import com.example.edvoratask.model.RideModel
import com.google.android.material.chip.Chip
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var home_userName: TextView
    lateinit var home_userProfile: CircleImageView
    lateinit var rideList: ArrayList<RideModel>
    lateinit var homeMenu: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getUserDetails()
        getRideDetails()

        // spinner
        homeMenu = findViewById(R.id.homeMenu)
        val adapter = ArrayAdapter.createFromResource(
            this, R.array.states,
            R.layout.empty_text
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        homeMenu.adapter = adapter
        homeMenu.onItemSelectedListener = this

        findViewById<Chip>(R.id.ride_nearest).setOnClickListener {
            getNearestRide()
        }
        findViewById<Chip>(R.id.ride_upcoming).setOnClickListener {
            getUpcomingRide()
        }
        findViewById<Chip>(R.id.ride_past).setOnClickListener {
            getPastRide()
        }

    }

    private fun getPastRide() {

    }

    private fun getUpcomingRide() {

    }

    private fun getNearestRide() {

    }

    private fun getRideDetails() {
        rideList = ArrayList()
        val ride_recyclerView = findViewById<RecyclerView>(R.id.ride_recyclerView)
        ride_recyclerView.layoutManager = LinearLayoutManager(this)
        ride_recyclerView.setHasFixedSize(true)

        val queue = Volley.newRequestQueue(this)
        val url = "https://assessment.api.vweb.app/rides"

        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
            { response ->
                for (i in 0 until response.length()) {
                    val jsonObjectRequest = response.getJSONObject(i)

                    val cityName = jsonObjectRequest.getString("city")
                    val rideId = jsonObjectRequest.getInt("id")
                    val originStation = jsonObjectRequest.getInt("origin_station_code")
                    val stationPath = jsonObjectRequest.getJSONArray("station_path")
                    val date = jsonObjectRequest.getString("date")
                    val stateName = jsonObjectRequest.getString("state")

                    rideList.add(
                        RideModel(
                            cityName = cityName,
                            rideId = rideId,
                            originStation = originStation.toString(),
                            stationPath = stationPath.toString(),
                            date = date,
                            distance = 0,
                            stateName = stateName
                        )
                    )
                    val rideAdapter = RideAdapter(rideList = rideList)
                    ride_recyclerView.adapter = rideAdapter
                }
            },
            {
                Toast.makeText(this, "couldn't fetch data", Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonArrayRequest)
    }

    private fun getUserDetails() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://assessment.api.vweb.app/user"
        home_userName = findViewById(R.id.home_userName)
        home_userProfile = findViewById(R.id.home_userProfile)

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                val name = response.getString("name")
                val image = response.getString("url")
                home_userName.text = name
                Glide.with(this).load(image).into(home_userProfile)
            },
            {
                Toast.makeText(this, "check internet", Toast.LENGTH_LONG).show()
            }
        )
        queue.add(jsonObjectRequest)
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val states = resources.getStringArray(R.array.states)
        Toast.makeText(this, states[p2].toString(), Toast.LENGTH_LONG).show()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}