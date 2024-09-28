package tm.com.balary.features.product.di

import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import tm.com.balary.features.product.data.repository.ProductRepositoryImpl
import tm.com.balary.features.product.domain.repository.ProductRepository
import tm.com.balary.features.product.domain.usecase.ProductUseCase
import tm.com.balary.features.product.presentation.viewmodel.ProductViewModel

val productModule = module {
    single<ProductRepository> { ProductRepositoryImpl(get()) }
    singleOf(::ProductUseCase)
    viewModelOf(::ProductViewModel)
}