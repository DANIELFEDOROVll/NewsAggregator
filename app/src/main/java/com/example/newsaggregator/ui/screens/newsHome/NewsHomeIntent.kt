package com.example.newsaggregator.ui.screens.newsHome

sealed class NewsHomeIntent{
    data object GetNews: NewsHomeIntent()
}