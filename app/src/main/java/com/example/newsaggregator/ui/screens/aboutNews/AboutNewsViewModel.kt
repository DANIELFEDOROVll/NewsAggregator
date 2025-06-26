package com.example.newsaggregator.ui.screens.aboutNews

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsaggregator.domain.usecases.GetNewsByGuidUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AboutNewsViewModel @Inject constructor(
    private val getNewsById: GetNewsByGuidUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _aboutNewsState = MutableStateFlow(AboutNewsState())
    val aboutNewsState: StateFlow<AboutNewsState> get() = _aboutNewsState

    init{
        val guid = savedStateHandle.get<String>("url") ?: ""
        intents(AboutNewsIntent.GetNewsByGuid(guid))
    }

    fun intents(intent: AboutNewsIntent){
        when(intent){
            is AboutNewsIntent.GetNewsByGuid -> loadNewsByGuid(intent.guid)
        }
    }

    private fun loadNewsByGuid(guid: String){
        viewModelScope.launch {
            val news = getNewsById(guid)

            news.onSuccess { value ->
                _aboutNewsState.value = _aboutNewsState.value.copy(
                    item = value,
                    isLoading = false
                )
            }
            news.onFailure { error ->
                _aboutNewsState.value = _aboutNewsState.value.copy(
                    error = error.message,
                    isLoading = false
                )
            }
        }
    }
}