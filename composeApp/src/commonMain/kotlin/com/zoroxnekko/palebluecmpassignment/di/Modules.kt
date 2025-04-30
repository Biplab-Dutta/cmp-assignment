package com.zoroxnekko.palebluecmpassignment.di

import com.zoroxnekko.palebluecmpassignment.core.data.HttpClientFactory
import com.zoroxnekko.palebluecmpassignment.pixa_images_overview.data.network.PixaImageRemoteDataSource
import com.zoroxnekko.palebluecmpassignment.pixa_images_overview.data.repository.PixaImageRepositoryImpl
import com.zoroxnekko.palebluecmpassignment.pixa_images_overview.domain.PixaImageRepository
import com.zoroxnekko.palebluecmpassignment.pixa_images_overview.presentation.PixaImageViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::PixaImageRemoteDataSource)
    singleOf(::PixaImageRepositoryImpl).bind<PixaImageRepository>()

    viewModelOf(::PixaImageViewModel)
}