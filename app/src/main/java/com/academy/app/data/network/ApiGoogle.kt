package com.academy.app.data.network

import com.academy.app.data.ResponseWrapper
import retrofit2.http.GET

interface ApiGoogle {

    @GET("drive/v3/files")
    suspend fun getFiles(): ResponseWrapper<Any>
}