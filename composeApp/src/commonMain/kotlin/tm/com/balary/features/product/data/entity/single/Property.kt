package tm.com.balary.features.product.data.entity.single

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject

val json = Json { ignoreUnknownKeys = true }

@Serializable
data class Property(
    val `property`: PropertyX,
    val `val`: JsonElement?
) {
    fun toRealObject(): Pair<PropertyX?, String?> {
        try {
            val value = `val`?.let { json.decodeFromJsonElement(PropertyX.serializer(), it.jsonObject) }
            if (value?.tm!=null || value?.ru!=null)
                return Pair(value, null)
            return Pair(null, `val`.toString())
        } catch (_: Exception) {
            return Pair(null, `val`.toString())
        }
    }
}