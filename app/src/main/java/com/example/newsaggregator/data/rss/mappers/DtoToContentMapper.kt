package com.example.newsaggregator.data.rss.mappers

import com.example.newsaggregator.data.rss.dto.ContentDto
import com.example.newsaggregator.domain.Content

fun ContentDto.toDomain() = Content(
    type = type,
    width = width,
    url = url,
    credit = credit?.toDomain()
)