package com.example.newsaggregator.data.rss.mappers

import com.example.newsaggregator.data.rss.dto.CategoryDto
import com.example.newsaggregator.domain.Category

fun CategoryDto.toDomain() = Category(
    domain = domain,
    value = value
)

