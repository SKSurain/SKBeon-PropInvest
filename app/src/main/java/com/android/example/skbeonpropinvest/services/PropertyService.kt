package com.android.example.skbeonpropinvest.services

import com.android.example.skbeonpropinvest.models.Property
import com.android.example.skbeonpropinvest.models.User

import retrofit2.Call
import retrofit2.http.*

interface PropertyService {

    @GET("property")
    fun getPropertyList(@QueryMap filter: HashMap<String, String>): Call<List<Property>>

    @GET("property/{id}")
    fun getProperty(@Path("id") id: Int): Call<Property>

    @POST("property")
    fun addProperty(@Body newProperty: Property): Call<Property>

    @FormUrlEncoded
    @PUT("property/{id}")
    fun updateProperty(
        @Path("id") id: Int,
        @Field("city") city: String,
        @Field("description") desc: String,
        @Field("country") country: String
    ): Call<Property>

    @DELETE("property/{id}")
    fun deleteProperty(@Path("id") id: Int): Call<Unit>
}