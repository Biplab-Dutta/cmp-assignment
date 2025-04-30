package com.zoroxnekko.palebluecmpassignment.pixa_images_overview.data.mappers

import com.zoroxnekko.palebluecmpassignment.pixa_images_overview.data.dto.PixaImageDto
import com.zoroxnekko.palebluecmpassignment.pixa_images_overview.domain.PixaImage

fun PixaImageDto.toDomainModel(): PixaImage {
    return PixaImage(
        id = id,
        userName = userName,
        imageUrl = imageUrl,
    )
}
