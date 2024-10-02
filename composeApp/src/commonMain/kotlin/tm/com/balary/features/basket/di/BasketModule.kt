package tm.com.balary.features.basket.di

import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import tm.com.balary.features.basket.data.repository.BasketRepositoryImpl
import tm.com.balary.features.basket.domain.repository.BasketRepository
import tm.com.balary.features.basket.domain.usecase.BasketUseCase
import tm.com.balary.features.basket.presentation.viewmodel.BasketViewModel
import tm.com.balary.features.basket.presentation.viewmodel.OrderFormViewModel

val basketModule = module {
    single<BasketRepository> { BasketRepositoryImpl(get(), get(), get()) }
    singleOf(::BasketUseCase)
    viewModelOf(::BasketViewModel)
    viewModelOf(::OrderFormViewModel)
}