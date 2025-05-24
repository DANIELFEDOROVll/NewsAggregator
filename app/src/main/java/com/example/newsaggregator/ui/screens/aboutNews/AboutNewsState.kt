package com.example.newsaggregator.ui.screens.aboutNews

import com.example.newsaggregator.domain.Item

data class AboutNewsState(
    val isLoading: Boolean? = null,
    val error: Boolean? = null,
    val item: Item? = null
)