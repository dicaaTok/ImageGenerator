package com.dica.imagegenerator.model.service

import android.graphics.Bitmap
import android.provider.ContactsContract.CommonDataKinds.Photo
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ImageService {
    @FormUrlEncoded
    @POST("generateimage.php")
    suspend fun generateImage(
        @Field("prompt") prompt: String ,
        @Query("image_type") photo: String = "photo",
        @Query("pretty") pretty: Boolean = true,
        @Field("width") width: Int = 1020,
        @Field("height") height: Int = 1020,
        @Field("seed") seed: Int = 918449,
        @Field("model") model: String = "flux",
        @Header("x-rapidapi-key") apiKey : String = "e39279ff1fmshd9ae35a6118c807p14886djsn17e3489baca5",
        @Header("x-rapidapi-host") host : String = "ai-text-to-image-generator-flux-free-api.p.rapidapi.com",
        @Header("Content-Type") contentType : String = "application/x-www-form-urlencoded"
    ):Response<ResponseBody>
}