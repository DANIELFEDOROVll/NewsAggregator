package com.example.newsaggregator.domain

import com.example.newsaggregator.data.rss.dto.CreditDto

data class Content(
    val type: String?,
    val width: String?,
    val url: String,
    val credit: Credit?
)
