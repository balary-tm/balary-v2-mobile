package tm.com.balary.features.contact.di

import org.koin.dsl.module
import tm.com.balary.features.contact.data.repository.ChatRepositoryImpl
import tm.com.balary.features.contact.domain.repository.ChatRepository
import tm.com.balary.features.contact.domain.usecase.ChatUseCase
import tm.com.balary.features.contact.presentation.viewmodel.ChatViewModel

val chatModule = module {
    single<ChatRepository> { ChatRepositoryImpl() }
    single { ChatUseCase(get()) }
    factory { ChatViewModel(get()) }
}