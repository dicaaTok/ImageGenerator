package com.dica.imagegenerator.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.dica.imagegenerator.model.core.Either
import com.dica.imagegenerator.model.service.ImageService
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val service: ImageService
) {
    suspend fun generateImage(prompt: String): Either<String, Bitmap> {
        return try {
            val response = service.generateImage(prompt)
            if (response.isSuccessful) {
                val inputStream = response.body()?.byteStream()
                val bitmap = BitmapFactory.decodeStream(inputStream)
                if (bitmap != null) {
                    Either.Success(bitmap)
                } else {
                    Either.Error("Не удалось декодировать изображение")
                }
            } else {
                Either.Error("Ошибка от сервера: ${response.code()}")
            }
        } catch (e: Exception) {
            Either.Error(e.message ?: "Неизвестная ошибка")
        }
    }
}