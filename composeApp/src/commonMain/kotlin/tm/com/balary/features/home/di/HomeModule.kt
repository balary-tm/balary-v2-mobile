package tm.com.balary.features.home.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import tm.com.balary.features.home.data.repository.HomeRepositoryImpl
import tm.com.balary.features.home.domain.repository.HomeRepository
import tm.com.balary.features.home.domain.usecase.HomeUseCase
import tm.com.balary.features.home.presentation.viewmodel.HomeToolbarViewModel
import tm.com.balary.features.home.presentation.viewmodel.HomeViewModel

val homeModule = module {
    viewModel { HomeToolbarViewModel() }
    single<HomeRepository> { HomeRepositoryImpl(get(), get()) }
    single { HomeUseCase(get()) }
    viewModel { HomeViewModel(get()) }
}