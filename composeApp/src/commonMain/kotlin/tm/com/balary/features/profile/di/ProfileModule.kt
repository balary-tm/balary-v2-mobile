package tm.com.balary.features.profile.di

import org.koin.dsl.module
import tm.com.balary.features.profile.data.setting.AppSettings

val profileModule = module {
    single { AppSettings(get()) }
}