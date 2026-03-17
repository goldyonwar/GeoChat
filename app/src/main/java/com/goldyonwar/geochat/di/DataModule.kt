package com.goldyonwar.geochat.di

import com.goldyonwar.geochat.BuildConfig
import com.goldyonwar.geochat.data.repository.AuthRepositoryImpl
import com.goldyonwar.geochat.data.repository.ChatRepositoryImpl
import com.goldyonwar.geochat.data.repository.DirectionsRepositoryImpl
import com.goldyonwar.geochat.data.repository.UserRepositoryImpl
import com.goldyonwar.geochat.domain.repository.AuthRepository
import com.goldyonwar.geochat.domain.repository.ChatRepository
import com.goldyonwar.geochat.domain.repository.DirectionsRepository
import com.goldyonwar.geochat.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.maps.GeoApiContext
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirestore() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideGeoApiContext(): GeoApiContext = GeoApiContext.Builder()
        .apiKey(BuildConfig.MAPS_API_KEY)
        .build()

    @Provides
    fun provideAuthRepo(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideUserRepo(impl: UserRepositoryImpl): UserRepository = impl

    @Provides
    fun provideChatRepo(impl: ChatRepositoryImpl): ChatRepository = impl

    @Provides
    fun provideDirectionsRepo(impl: DirectionsRepositoryImpl): DirectionsRepository = impl
}