package com.zoroxnekko.palebluecmpassignment.pixa_images_overview.data.network

import com.zoroxnekko.palebluecmpassignment.core.data.safeCall
import com.zoroxnekko.palebluecmpassignment.core.domain.DataError
import com.zoroxnekko.palebluecmpassignment.core.domain.Result
import com.zoroxnekko.palebluecmpassignment.pixa_images_overview.data.dto.PixaImageHitsDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

private const val BASE_URL = "https://pixabay.com/api/"

class PixaImageRemoteDataSource(
    private val httpClient: HttpClient
) {
    suspend fun fetchImages(
        q: String,
        imageType: String
    ): Result<PixaImageHitsDto, DataError.Remote> {
        return safeCall<PixaImageHitsDto> {
            httpClient.get(
                urlString = BASE_URL
            ) {
                parameter("key", "22577733-edb14e0d0f3f9c1a039c57e48")
                parameter("q", q)
                parameter("image_type", imageType)
            }
        }
    }
}
