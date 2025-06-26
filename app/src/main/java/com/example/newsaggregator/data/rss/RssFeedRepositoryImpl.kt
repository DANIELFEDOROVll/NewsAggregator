package com.example.newsaggregator.data.rss

import android.util.Log
import com.example.newsaggregator.data.rss.mappers.toDomain
import com.example.newsaggregator.domain.Item
import com.example.newsaggregator.domain.RssFeedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException
import javax.inject.Inject


class RssFeedRepositoryImpl @Inject constructor(
    private val service: RssFeed
): RssFeedRepository {
    private var cache: List<Item> = emptyList()

    override suspend fun getNews(): Result<List<Item>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = service.getRss()
                val body = response.body()

                if(response.isSuccessful && body != null){
                    cache = body.channel.items.map { it.toDomain() }
                    Result.success(cache)
                }
                else {
                    Result.failure(Exception("Ошибка сервера: ${response.code()}"))
                }
            } catch (e: IOException){
                Result.failure(IOException("Ошибка сети: ${e.message}"))
            } catch (e: Exception) {
                Result.failure(Exception("Неизвестная ошибка: ${e.message}"))
            }
        }
    }

    override suspend fun getNewsByGuid(guid: String): Result<Item> {
        return withContext(Dispatchers.IO) {
            try {
                val news = cache.find { it.guid == guid }
                if(news != null)
                    Result.success(news)
                else
                    Result.failure(Exception("news is null"))

            } catch (e: IOException){
                Result.failure(IOException("Ошибка сети: ${e.message}"))
            } catch (e: Exception){
                Result.failure(Exception("Неизвестная ошибка: ${e.message}"))
            }
        }
    }
}