package com.example.newsaggregator.ui.screens.newsHome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsaggregator.domain.usecases.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsHomeViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
): ViewModel() {

    private val _newsHomeState = MutableStateFlow(NewsHomeState())
    val newsHomeState: StateFlow<NewsHomeState> get() = _newsHomeState

    init {
        intents(NewsHomeIntent.GetNews)
    }

    fun intents(intent: NewsHomeIntent){
        when(intent){
            is NewsHomeIntent.GetNews -> loadNews()
        }
    }

    private fun loadNews(){
        viewModelScope.launch{
            val news = getNewsUseCase()
            _newsHomeState.value = _newsHomeState.value.copy(
                items = news,
                isLoading = false
            )
        }
    }
}