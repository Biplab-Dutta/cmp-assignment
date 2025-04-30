package com.zoroxnekko.palebluecmpassignment.pixa_images_overview.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zoroxnekko.palebluecmpassignment.pixa_images_overview.domain.PixaImage
import com.zoroxnekko.palebluecmpassignment.pixa_images_overview.presentation.components.GridItem
import com.zoroxnekko.palebluecmpassignment.pixa_images_overview.presentation.components.LazyScrollableGrid
import com.zoroxnekko.palebluecmpassignment.pixa_images_overview.presentation.components.PixaSearchBar
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PixaImageOverviewPageRoot(
    viewModel: PixaImageViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    PixaImageOverviewPage(
        state = state,
        onLoadMore = viewModel::fetchMoreImages,
        onSearchQueryChange = viewModel::onSearchQueryChanged,
    )
}

@Composable
private fun PixaImageOverviewPage(
    state: PixaImagesState,
    onLoadMore: () -> Unit,
    onSearchQueryChange: (String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            PixaSearchBar(
                modifier = Modifier
                    .widthIn(max = 400.dp)
                    .fillMaxWidth()
                    .padding(16.dp),
                searchQuery = state.searchQuery,
                onSearchQueryChange = onSearchQueryChange,
                onImeSearch = {
                    keyboardController?.hide()
                },
            )

            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    when {
                        state.errorMessage != null -> {
                            Text(
                                state.errorMessage.asString(),
                                textAlign = TextAlign.Center,
                            )
                        }

                        state.searchResults.isEmpty() -> {
                            Text(
                                "Search to get more result",
                                textAlign = TextAlign.Center,
                            )
                        }

                        else -> {
                            LazyScrollableGrid<PixaImage>(
                                items = state.searchResults,
//                                itemKey = { result ->
//                                    result.id
//                                },
                                itemContent = { result ->
                                    GridItem(result)
                                },
                                onLoadMore = onLoadMore,
                                isLoadingMore = state.isLoadingMore,
                                loadingMoreIndicator = {
                                    LinearProgressIndicator(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(
                                                horizontal = 16.dp,
                                                vertical = 8.dp
                                            )
                                    )
                                },
                                crossAxisCount = 2,
                            )
                        }
                    }
                }
            }
        }
    }
}
