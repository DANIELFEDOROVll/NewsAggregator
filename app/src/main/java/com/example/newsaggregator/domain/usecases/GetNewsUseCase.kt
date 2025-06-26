package com.example.newsaggregator.domain.usecases

import com.example.newsaggregator.domain.Item
import com.example.newsaggregator.domain.RssFeedRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repo: RssFeedRepository
) {
    suspend operator fun invoke(): Result<List<Item>>{
        return repo.getNews()
    }
}