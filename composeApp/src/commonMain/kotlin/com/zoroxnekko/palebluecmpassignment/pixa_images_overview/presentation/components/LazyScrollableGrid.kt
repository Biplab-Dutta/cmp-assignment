package com.zoroxnekko.palebluecmpassignment.pixa_images_overview.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun <T> LazyScrollableGrid(
    gridState: LazyGridState = rememberLazyGridState(),
    items: List<T>,
    itemKey: ((T) -> Any)? = null,
    itemContent: @Composable (T) -> Unit,
    onLoadMore: () -> Unit,
    crossAxisCount: Int = 2,
    isLoadingMore: Boolean = false,
    loadingMoreIndicator: @Composable () -> Unit,
) {
    val reachedBottom: Boolean by remember {
        derivedStateOf { gridState.reachedBottom() }
    }

    LaunchedEffect(reachedBottom) {
        if (reachedBottom) onLoadMore()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            modifier = Modifier.weight(1f),
            state = gridState,
            columns = GridCells.Fixed(crossAxisCount)
        ) {
            items(
                items,
                key = itemKey?.let { key -> { item: T -> key(item) } },
            ) { result ->
                itemContent(result)
            }
        }
        if (isLoadingMore) {
            loadingMoreIndicator()
        }
    }

}

private fun LazyGridState.reachedBottom(buffer: Int = 1): Boolean {
    val lastVisibleItem = this.layoutInfo.visibleItemsInfo.lastOrNull()
    return lastVisibleItem?.index != 0 && lastVisibleItem?.index == this.layoutInfo.totalItemsCount - buffer
}