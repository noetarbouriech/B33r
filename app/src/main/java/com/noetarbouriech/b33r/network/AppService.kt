package com.noetarbouriech.b33r.network

import retrofit2.Retrofit
import retrofit2.http.GET
import kotlinx.serialization.json.*
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType


private const val BASE_URL = "https://google.com"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(
        Json.asConverterFactory(
            "application/json; charset=UTF8".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface MyApiService {
    @GET("test")
    suspend fun getData(): List<Beer>
}

object MyApi {
    val retrofitService: MyApiService by lazy {
        retrofit.create(MyApiService::class.java)
    }
}