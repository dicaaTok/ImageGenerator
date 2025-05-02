package com.dica.imagegenerator.model.core

import android.provider.MediaStore.Images
import com.dica.imagegenerator.model.service.ImageService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitClient {

    private const val BASE_USL =
        "https://ai-text-to-image-generator-flux-free-api.p.rapidapi.com/aaaaaaaaaaaaaaaaaiimagegenerator/fluximagegenerate/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val  httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @Singleton
    @get:Provides
    val retrofitService: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_USL) //
            .client(httpClient)
            .addConverterFactory(json.asConverterFactory("application/x-www-form-urlencoded".toMediaType())) // исправлено
            .build()
    }
    @Singleton
    @get:Provides
    val imageService: ImageService by lazy {
        retrofitService.create(ImageService::class.java)
    }
}