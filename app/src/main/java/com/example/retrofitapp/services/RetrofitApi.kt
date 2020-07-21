package com.example.retrofitapp.services

import com.example.retrofitapp.modals.Modal
import retrofit2.http.GET

interface RetrofitApi {
    @GET("api/breeds/image/random/10")
    fun getDogData(): retrofit2.Call<Modal>
}