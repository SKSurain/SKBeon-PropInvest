package com.android.example.skbeonpropinvest.services

import com.android.example.skbeonpropinvest.models.User

import retrofit2.Call
import retrofit2.http.*

interface UserService {

    /*This function does not perform authorization, it just gets User data based on
    * their ID. Currently, I am focusing on front end development*/
    @GET("user/{id}")
    fun getUserAuthorization(@Path("id") id: Int): Call<User>
}