package com.example.newsaggregator.ui.screens.aboutNews

sealed class AboutNewsIntent{
    data class GetNewsByGuid(val guid: String): AboutNewsIntent()
}