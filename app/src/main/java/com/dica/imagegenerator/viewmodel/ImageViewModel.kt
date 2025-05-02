package com.dica.imagegenerator.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dica.imagegenerator.model.core.Either
import com.dica.imagegenerator.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val repository: ImageRepository
 ): ViewModel(){
    private val _image = MutableLiveData<Bitmap>()
    val image: LiveData<Bitmap> = _image

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun generateImage(prompt: String) {
        viewModelScope.launch {
            when (val response = repository.generateImage(prompt)) {
                is Either.Success -> _image.value = response.data
                is Either.Error -> _error.value = response.error
            }
        }
    }
}