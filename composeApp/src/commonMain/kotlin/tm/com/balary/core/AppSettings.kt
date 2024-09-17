package tm.com.balary.core

import com.russhwolf.settings.Settings
import org.koin.dsl.module

val provideAppSettings = module {
    single { Settings() }
}