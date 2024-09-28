package tm.com.balary.locale

import androidx.compose.runtime.Composable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject
import tm.com.balary.state.LocalAppState


@Composable
inline fun <reified T> translateValue(instance: T, property: String, prefix: String = "_"): String {
    val appSettingsState = LocalAppState.current
    val element = Json.encodeToJsonElement(instance)
    val find = element.jsonObject.entries.find {
        it.key == "$property$prefix${appSettingsState.value.language}"
    }
    return find?.value.toString().drop(1).dropLast(1)
}