package tm.com.balary.features.profile.data.setting

import com.russhwolf.settings.Settings
import tm.com.balary.features.profile.domain.model.AppState
import tm.com.balary.features.profile.domain.model.AppTheme
import tm.com.balary.locale.Locales

class AppSettings(
    private val settings: Settings
) {
    companion object {
        const val THEME_KEY = "theme"
        const val LANGUAGE_KEY = "language"
        const val IS_FIRST_KEY = "is_first"
    }
    fun saveTheme(theme: AppTheme) {
        settings.putString(THEME_KEY, theme.name)
    }
    fun getTheme(): AppTheme {
        return AppTheme.valueOf(settings.getString(THEME_KEY, AppTheme.SYSTEM.toString()))
    }
    fun saveLanguage(language: String) {
        settings.putString(LANGUAGE_KEY, language)
    }
    fun getLanguage(): String {
        return settings.getString(LANGUAGE_KEY, Locales.TURKMEN)
    }
    fun saveIsFirst(isFirst: Boolean) {
        settings.putBoolean(IS_FIRST_KEY, isFirst)
    }
    fun getIsFirst(): Boolean {
        return settings.getBoolean(IS_FIRST_KEY, true)
    }
    fun getAppSettings(): AppState {
        return AppState(
            theme = getTheme(),
            language = getLanguage(),
            isFirst = getIsFirst()
        )
    }
}