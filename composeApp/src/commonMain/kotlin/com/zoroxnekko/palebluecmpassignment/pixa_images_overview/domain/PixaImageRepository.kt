package com.zoroxnekko.palebluecmpassignment.pixa_images_overview.domain

import com.zoroxnekko.palebluecmpassignment.core.domain.DataError
import com.zoroxnekko.palebluecmpassignment.core.domain.Result

interface PixaImageRepository {
    suspend fun fetchImages(q: String, imageType: String): Result<List<PixaImage>, DataError.Remote>
}
