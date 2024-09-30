package tm.com.balary.features.favorite.di

import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import tm.com.balary.features.favorite.data.repository.FavoriteRepositoryImpl
import tm.com.balary.features.favorite.domain.repository.FavoriteRepository
import tm.com.balary.features.favorite.domain.usecase.FavoriteUseCase
import tm.com.balary.features.favorite.presentation.viewmodel.FavoriteViewModel

val favoriteModule = module {
    single<FavoriteRepository> { FavoriteRepositoryImpl(get(), get()) }
    singleOf(::FavoriteUseCase)
    viewModelOf(::FavoriteViewModel)
}