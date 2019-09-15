package com.syousa1982.catbeer.data.api

import retrofit2.http.GET

interface ChatBotApi {

    @GET("/helloWorld")
    suspend fun fetchHelloWorld(): HelloResponse
}