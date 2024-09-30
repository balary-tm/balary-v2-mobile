package tm.com.balary.features.auth.data.settings

import com.russhwolf.settings.Settings

class AuthSettings(
    private val settings: Settings
) {
    companion object {
        const val TOKEN_KEY = "token_key"
    }

    fun saveToken(token: String) {
        settings.putString(TOKEN_KEY, token)
    }

    fun getToken(): String? {
        return settings.getStringOrNull(TOKEN_KEY)
    }
}