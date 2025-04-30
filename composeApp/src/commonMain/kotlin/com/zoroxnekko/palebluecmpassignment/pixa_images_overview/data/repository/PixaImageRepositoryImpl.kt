package com.zoroxnekko.palebluecmpassignment.pixa_images_overview.data.repository

import com.zoroxnekko.palebluecmpassignment.core.domain.DataError
import com.zoroxnekko.palebluecmpassignment.core.domain.Result
import com.zoroxnekko.palebluecmpassignment.core.domain.map
import com.zoroxnekko.palebluecmpassignment.pixa_images_overview.data.mappers.toDomainModel
import com.zoroxnekko.palebluecmpassignment.pixa_images_overview.data.network.PixaImageRemoteDataSource
import com.zoroxnekko.palebluecmpassignment.pixa_images_overview.domain.PixaImage
import com.zoroxnekko.palebluecmpassignment.pixa_images_overview.domain.PixaImageRepository

class PixaImageRepositoryImpl(
    private val remoteDataSource: PixaImageRemoteDataSource
) : PixaImageRepository {
    override suspend fun fetchImages(
        q: String,
    ): Result<List<PixaImage>, DataError.Remote> {
        return remoteDataSource.fetchImages(q).map { dto ->
            dto.hits.map { it.toDomainModel() }
        }
    }
}
