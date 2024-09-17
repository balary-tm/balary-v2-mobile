package tm.com.balary.features.profile.domain.model

import tm.com.balary.locale.Locales

enum class AppTheme(theme: String) {
    SYSTEM("system"),
    DARK("dark"),
    LIGHT("light"),
}

data class AppState(
    val theme: AppTheme = AppTheme.SYSTEM,
    val language: String = Locales.TURKMEN,
    val isFirst: Boolean = true,
)
