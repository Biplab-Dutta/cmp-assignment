package com.zoroxnekko.palebluecmpassignment.pixa_images_overview.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zoroxnekko.palebluecmpassignment.pixa_images_overview.domain.PixaImage
import com.zoroxnekko.palebluecmpassignment.pixa_images_overview.presentation.components.GridItem
import com.zoroxnekko.palebluecmpassignment.pixa_images_overview.presentation.components.LazyScrollableGrid
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PixaImageOverviewPageRoot(
    modifier: Modifier = Modifier,
    viewModel: PixaImageViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    PixaImageOverviewPage(
        state = state,
        onLoadMore = viewModel::fetchMoreImages
    )
}

@Composable
private fun PixaImageOverviewPage(
    state: PixaImagesState,
    onLoadMore: () -> Unit,
) {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            LazyScrollableGrid<PixaImage>(
                items = state.results,
                itemKey = { result ->
                    result.id
                },
                itemContent = { result ->
                    GridItem(result)
                },
                onLoadMore = onLoadMore,
                isLoadingMore = state.isLoadingMore,
                loadingMoreIndicator = {
                    LinearProgressIndicator()
                },
                crossAxisCount = 2,
            )
        }
    }
}
