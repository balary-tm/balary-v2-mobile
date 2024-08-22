package tm.com.balary.features.home.di

import org.koin.dsl.module
import tm.com.balary.features.home.presentation.viewmodel.HomeToolbarViewModel

val homeModule = module {
    factory { HomeToolbarViewModel() }
}