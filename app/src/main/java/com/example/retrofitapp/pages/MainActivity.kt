package com.example.retrofitapp.pages

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.retrofitapp.adapters.RecyclerAdapter
import com.example.retrofitapp.modals.Modal
import com.example.retrofitapp.services.RetrofitApi
import com.example.retrofitapp.utils.Constants.BASE_URL
import com.example.retrofitapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var dogAdapter: RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        makeRetrofitCall()
    }
    private fun makeRetrofitCall() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val instance = retrofit.create(RetrofitApi::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            instance.getDogData().enqueue(object : Callback<Modal> {
                override fun onFailure(call: Call<Modal>, t: Throwable) {
                    makeLog(t.message)
                }

                override fun onResponse(call: Call<Modal>, response: Response<Modal>) {
                    setupRecyclerView(response.body())
                }
            })
        }
    }
    private fun setupRecyclerView(dogList: Modal?) {
        dogList?.let {
            dogAdapter = RecyclerAdapter(dogList.message)
        }

        val dogLayoutManager = GridLayoutManager(
            this,
            1,
            GridLayoutManager.VERTICAL,
            false
        )

        dogRecyclerViewContainer.apply {
            adapter = dogAdapter
            layoutManager = dogLayoutManager
        }
    }
    private fun makeLog(message: String?) {
        message?.let {
            Log.e("successful call", message)
        }
    }
}
