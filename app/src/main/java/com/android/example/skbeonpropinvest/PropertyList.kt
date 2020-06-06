package com.android.example.skbeonpropinvest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.example.skbeonpropinvest.helpers.DestinationAdapter
import com.android.example.skbeonpropinvest.models.Property
import com.android.example.skbeonpropinvest.services.PropertyService
import com.smartherd.globofly.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_destiny_list.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback
import kotlin.math.log

private lateinit var propertyList: List<Property>

class PropertyList : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.activity_destiny_list, container, false)

        val rvPropertyList = view.findViewById(R.id.recycler_view) as? RecyclerView
        rvPropertyList?.layoutManager = LinearLayoutManager(this.context)
        loadPropertyList(rvPropertyList)

        return view
    }

    fun loadPropertyList(recyclerView: RecyclerView?) {
        val filter = HashMap<String, String>()
        val propertyService = ServiceBuilder.buildService(PropertyService::class.java)
        val requestCall = propertyService.getPropertyList(filter)
        requestCall.enqueue(object : retrofit2.Callback<List<Property>> {

            // If you receive a HTTP Response, then this method is executed
            // Your STATUS Code will decide if your Http Response is a Success or Error
            override fun onResponse(
                call: Call<List<Property>>,
                response: Response<List<Property>>
            ) {
                if (response.isSuccessful) {
                    // Your status code is in the range of 200's
                    val destinationList = response.body()!!
                    recyclerView?.adapter = DestinationAdapter(destinationList)

                } else if (response.code() == 401) {
                    Toast.makeText(
                        view?.context,
                        "Your session has expired. Please Login again.", Toast.LENGTH_LONG
                    ).show()
                } else { // Application-level failure
//                     Your status code is in the range of 300's, 400's and 500's
                    Toast.makeText(view?.context, "Failed to retrieve items", Toast.LENGTH_LONG)
                        .show()
                }
            }

            // Invoked in case of Network Error or Establishing connection with Server
            // or Error Creating Http Request or Error Processing Http Response
            override fun onFailure(call: Call<List<Property>>, t: Throwable) {

                Toast.makeText(view?.context, "Error Occurred" + t.toString(), Toast.LENGTH_LONG)
                    .show()
            }
        })
    }
}

