package com.syousa1982.catbeer.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syousa1982.catbeer.data.repository.IChatBotRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception

class HomeViewModel(private val repository: IChatBotRepository) : ViewModel() {

    sealed class ViewState {
        object Progress : ViewState()
        object Completed : ViewState()
        object Error : ViewState()
    }

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = _viewState

    private val _text = MutableLiveData<String>().apply { value = "This is home Fragment" }
    val text: LiveData<String> = _text

    fun start() = viewModelScope.launch {
        try {
            _viewState.value = ViewState.Progress
            val hello = repository.getHello()
            _text.value = hello.body
            _viewState.value = ViewState.Completed
        } catch (e: Exception) {
            _viewState.value = ViewState.Error
        }
    }
}