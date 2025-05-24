package com.example.newsaggregator.ui.screens.newsHome

import com.example.newsaggregator.domain.Item

data class NewsHomeState(
    val isLoading: Boolean? = true,
    val error: String? = null,
    val items: List<Item> = emptyList()
)
