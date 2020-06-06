package com.android.example.skbeonpropinvest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.example.skbeonpropinvest.models.User
import com.android.example.skbeonpropinvest.services.UserService
import com.smartherd.globofly.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception

private lateinit var btnView: Button

class WelcomeFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_welcome, container, false)
        btnView = view.findViewById(R.id.bvLogin)
        btnView.setOnClickListener {
            onSubmitted()
        }
        return view
    }

    private fun onSubmitted() {
        userLogin()
    }

    fun changeFragment() {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        val fragmentProperty = PropertyList()
        fragmentTransaction.replace(R.id.fragment, fragmentProperty).commit()
    }

    fun userLogin() {
        val filter = HashMap<String, String>()
        val propertyService = ServiceBuilder.buildService(UserService::class.java)


        val username = view?.findViewById(R.id.etPassword) as EditText
        val value: String = username.getText().toString()
        var finalValue: Int = 0
        if (value != "") {
            try {
                finalValue = value.toInt()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            val requestCall = propertyService.getUserAuthorization(finalValue)
            requestCall.enqueue(object : retrofit2.Callback<User> {

                // If you receive a HTTP Response, then this method is executed
                // Your STATUS Code will decide if your Http Response is a Success or Error
                override fun onResponse(
                    call: Call<User>,
                    response: Response<User>
                ) {
                    if (response.isSuccessful) {
                        // Your status code is in the range of 200's
                        val destinationList = response.body()!!
                        Log.v("WelcomeFragment", destinationList.name)
                        changeFragment()

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
                override fun onFailure(call: Call<User>, t: Throwable) {

                    Toast.makeText(
                        view?.context,
                        "Error Occurred" + t.toString(),
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            })

        } else {
            Toast.makeText(
                view?.context,
                "Please make sure your password and username are correct",
                Toast.LENGTH_LONG
            )
                .show()
        }

    }

}


