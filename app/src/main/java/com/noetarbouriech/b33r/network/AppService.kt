package com.noetarbouriech.b33r.network

import retrofit2.Retrofit
import retrofit2.http.GET
import kotlinx.serialization.json.*
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType


// TODO use env var
private const val BASE_URL = "http://162.38.112.116:3000/api/v1/beer/"

// Configure the JSON parser with ignoreUnknownKeys
val json = Json { ignoreUnknownKeys = true }

private val retrofit = Retrofit.Builder()
    .addConverterFactory(
        json.asConverterFactory(
            "application/json; charset=UTF8".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface MyApiService {
    @GET("search?query=*")
    suspend fun getData(): ApiResponse
}

object MyApi {
    val retrofitService: MyApiService by lazy {
        retrofit.create(MyApiService::class.java)
    }
}