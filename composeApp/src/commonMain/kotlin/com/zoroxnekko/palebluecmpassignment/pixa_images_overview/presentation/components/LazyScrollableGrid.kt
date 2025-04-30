package com.zoroxnekko.palebluecmpassignment.pixa_images_overview.presentation.components

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun <T> LazyScrollableGrid(
    listState: LazyListState = rememberLazyListState(),
    items: List<T>,
    itemKey: ((T) -> Any)? = null,
    itemContent: @Composable (T) -> Unit,
    onLoadMore: () -> Unit,
    crossAxisCount: Int = 2,
    isLoadingMore: Boolean = false,
    loadingMoreIndicator: @Composable () -> Unit,
) {
    val reachedBottom: Boolean by remember {
        derivedStateOf { listState.reachedBottom() }
    }

    LaunchedEffect(reachedBottom) {
        if (reachedBottom) onLoadMore()
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(crossAxisCount)
    ) {
        items(
            items,
            key = itemKey?.let { key -> { item: T -> key(item) } },
        ) { result ->
            itemContent(result)
        }

        if (isLoadingMore) {
            item { loadingMoreIndicator }
        }
    }

}

private fun LazyListState.reachedBottom(buffer: Int = 1): Boolean {
    val lastVisibleItem = this.layoutInfo.visibleItemsInfo.lastOrNull()
    return lastVisibleItem?.index != 0 && lastVisibleItem?.index == this.layoutInfo.totalItemsCount - buffer
}