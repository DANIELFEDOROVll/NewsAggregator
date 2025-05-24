package com.example.newsaggregator.di

import com.example.newsaggregator.data.rss.RssFeedRepositoryImpl
import com.example.newsaggregator.domain.RssFeedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun getRssFeedRepository(repo: RssFeedRepositoryImpl): RssFeedRepository
}