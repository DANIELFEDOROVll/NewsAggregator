package com.example.newsaggregator.data.rss

import android.util.Log
import com.example.newsaggregator.data.rss.mappers.toDomain
import com.example.newsaggregator.domain.Item
import com.example.newsaggregator.domain.RssFeedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class RssFeedRepositoryImpl @Inject constructor(
    private val service: RssFeed
): RssFeedRepository {
    private var cache: List<Item> = emptyList()

    override suspend fun getNews(): List<Item> {
        return withContext(Dispatchers.IO) {
            try {
                val response = service.getRss()
                cache = response.channel.items.map { it.toDomain() }
                cache
            } catch (e: Exception) {
                Log.e("!!!error", "Ошибка getNews() : ${e.message}")
                emptyList()
            }
        }
    }

    override suspend fun getNewsByGuid(guid: String): Item? {
        return withContext(Dispatchers.IO) {
            try {
                cache.find { it.guid == guid }
            } catch (e: Exception){
                Log.e("!!!error", "Ошибка getNewsByGuid() : ${e.message}")
                null
            }
        }
    }
}