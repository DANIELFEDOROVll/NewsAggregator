package com.example.newsaggregator.data.rss.mappers

import com.example.newsaggregator.data.rss.dto.ItemDto
import com.example.newsaggregator.domain.Item

fun ItemDto.toDomain(): Item{
    return Item(
        title = title,
        link = link,
        description = description,
        categories = categories.map { it.toDomain() },
        pubDate = pubDate,
        guid = guid,
        contents = contents.map { it.toDomain() },
        dcCreator = dcCreator,
        dcDate = dcDate
    )
}