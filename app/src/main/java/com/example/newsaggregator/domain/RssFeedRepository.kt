package com.example.newsaggregator.domain

interface RssFeedRepository {
    suspend fun getNews(): List<Item>

    suspend fun getNewsByGuid(guid: String): Item?
}