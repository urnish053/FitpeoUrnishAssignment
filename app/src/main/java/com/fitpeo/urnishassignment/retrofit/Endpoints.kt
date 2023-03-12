package com.fitpeo.urnishassignment.retrofit

import com.fitpeo.urnishassignment.model.ItemListModel
import retrofit2.Call
import retrofit2.http.GET

interface Endpoints {
    @GET("photos")
    fun getItemList(): Call<ArrayList<ItemListModel>>
}