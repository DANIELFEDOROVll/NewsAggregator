package com.example.newsaggregator.domain

interface RssFeedRepository {
    suspend fun getNews(): Result<List<Item>>

    suspend fun getNewsByGuid(guid: String): Result<Item>
}