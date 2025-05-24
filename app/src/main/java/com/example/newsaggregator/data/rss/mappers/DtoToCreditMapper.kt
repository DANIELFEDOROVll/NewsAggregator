package com.example.newsaggregator.data.rss.mappers

import com.example.newsaggregator.data.rss.dto.CreditDto
import com.example.newsaggregator.domain.Credit

fun CreditDto.toDomain() = Credit(
    scheme = scheme,
    value = value
)