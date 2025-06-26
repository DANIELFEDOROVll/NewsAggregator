package com.example.newsaggregator.domain.usecases

import com.example.newsaggregator.domain.Item
import com.example.newsaggregator.domain.RssFeedRepository
import javax.inject.Inject

class GetNewsByGuidUseCase @Inject constructor(
    private val repo: RssFeedRepository
){
    suspend operator fun invoke(guid: String): Result<Item> {
        return repo.getNewsByGuid(guid)
    }
}