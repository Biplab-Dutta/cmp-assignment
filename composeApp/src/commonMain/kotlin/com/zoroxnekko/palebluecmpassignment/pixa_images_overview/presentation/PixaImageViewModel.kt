package com.zoroxnekko.palebluecmpassignment.pixa_images_overview.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoroxnekko.palebluecmpassignment.core.domain.onError
import com.zoroxnekko.palebluecmpassignment.core.domain.onSuccess
import com.zoroxnekko.palebluecmpassignment.core.presentation.toUiText
import com.zoroxnekko.palebluecmpassignment.pixa_images_overview.domain.PixaImageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PixaImageViewModel(
    private val repository: PixaImageRepository
) : ViewModel() {
    private val _state = MutableStateFlow(PixaImagesState())
    val state = _state
        .onStart {
            fetchImages(query = "ball", imageType = "photo")
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            PixaImagesState()
        )

    private fun fetchImages(query: String, imageType: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            repository.fetchImages(q = query, imageType = imageType)
                .onSuccess { images ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            results = images,
                            errorMessage = null,
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            results = emptyList(),
                            errorMessage = error.toUiText(),
                        )
                    }
                }
        }
    }
}