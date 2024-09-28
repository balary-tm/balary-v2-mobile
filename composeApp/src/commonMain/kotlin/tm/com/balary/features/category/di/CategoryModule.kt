package tm.com.balary.features.category.di

import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import tm.com.balary.features.category.data.repository.CategoryRepositoryImpl
import tm.com.balary.features.category.domain.repository.CategoryRepository
import tm.com.balary.features.category.domain.usecase.CategoryUseCase
import tm.com.balary.features.category.presentation.viewmodel.CategoryViewModel

val categoryModule = module {
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }
    singleOf(::CategoryUseCase)
    viewModelOf(::CategoryViewModel)
}