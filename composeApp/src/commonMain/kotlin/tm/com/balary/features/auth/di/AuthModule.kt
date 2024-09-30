package tm.com.balary.features.auth.di

import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import tm.com.balary.features.auth.data.repository.AuthRepositoryImpl
import tm.com.balary.features.auth.data.settings.AuthSettings
import tm.com.balary.features.auth.domain.repository.AuthRepository
import tm.com.balary.features.auth.domain.usecase.AuthUseCase
import tm.com.balary.features.auth.presentation.viewmodel.AuthViewModel

val authModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    singleOf(::AuthUseCase)
    viewModelOf(::AuthViewModel)
    singleOf(::AuthSettings)
}