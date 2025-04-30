package com.zoroxnekko.palebluecmpassignment.pixa_images_overview.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoroxnekko.palebluecmpassignment.core.domain.onError
import com.zoroxnekko.palebluecmpassignment.core.domain.onSuccess
import com.zoroxnekko.palebluecmpassignment.core.presentation.toUiText
import com.zoroxnekko.palebluecmpassignment.pixa_images_overview.domain.PixaImageRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PixaImageViewModel(
    private val repository: PixaImageRepository
) : ViewModel() {
    var pageNumber = 1

    private var searchJob: Job? = null

    private val _state = MutableStateFlow(PixaImagesState())
    val state = _state
        .onStart { observeSearchQuery() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            PixaImagesState()
        )

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        state
            .map { it.searchQuery }
            .distinctUntilChanged()
            .debounce { 500L }
            .onEach { query ->
                when {
                    query.isBlank() -> {
                        pageNumber = 1
                        _state.update {
                            it.copy(errorMessage = null)
                        }
                    }

                    query.length >= 2 -> {
                        searchJob?.cancel()
                        searchJob = fetchImages(query)
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun fetchImages(query: String): Job = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        repository.fetchImages(q = query, page = pageNumber)
            .onSuccess { images ->
                pageNumber++
                _state.update {
                    it.copy(
                        isLoading = false,
                        searchResults = images,
                        errorMessage = null,
                    )
                }
            }
            .onError { error ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        searchResults = emptyList(),
                        errorMessage = error.toUiText(),
                    )
                }
            }
    }

    fun onSearchQueryChanged(query: String) {
        _state.update { it.copy(searchQuery = query) }
    }

    fun fetchMoreImages() {
        viewModelScope.launch {
            _state.update { it.copy(isLoadingMore = true) }
            repository.fetchImages(q = _state.value.searchQuery, page = pageNumber)
                .onSuccess { images ->
                    pageNumber++
                    _state.update {
                        it.copy(
                            isLoadingMore = false,
                            searchResults = it.searchResults + images,
                            errorMessage = null,
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            isLoadingMore = false,
                            errorMessage = error.toUiText(),
                        )
                    }
                }
        }
    }
}