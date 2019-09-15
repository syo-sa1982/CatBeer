package com.syousa1982.catbeer.data.repository

import com.syousa1982.catbeer.data.api.ChatBotApi
import com.syousa1982.catbeer.data.api.HelloResponse

interface IChatBotRepository {
    suspend fun getHello(): HelloResponse
}

class ChatBotRepository(private val api: ChatBotApi) : IChatBotRepository {
    override suspend fun getHello(): HelloResponse = api.fetchHelloWorld()
}