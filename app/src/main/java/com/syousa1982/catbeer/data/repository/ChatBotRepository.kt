package com.syousa1982.catbeer.data.repository

import com.syousa1982.catbeer.data.api.ChatBotApi
import com.syousa1982.catbeer.data.api.HelloResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface IChatBotRepository {
    suspend fun getHello(): HelloResponse
}

class ChatBotRepository(private val api: ChatBotApi) : IChatBotRepository {

    override suspend fun getHello(): HelloResponse =
        withContext(Dispatchers.IO) { api.fetchHelloWorld() }
}