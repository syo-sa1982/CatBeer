package com.syousa1982.catbeer.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.syousa1982.catbeer.data.repository.ChatBotRepository
import com.syousa1982.catbeer.data.repository.IChatBotRepository
import kotlinx.coroutines.runBlocking

class HomeViewModel(val repository: IChatBotRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun start() = runBlocking {
        val response = repository.getHello()
        _text.value = response.body
    }
}