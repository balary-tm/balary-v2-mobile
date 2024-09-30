package tm.com.balary.features.auth.presentation.state

import tm.com.balary.features.auth.domain.model.AuthModel
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class VerifyOtpState @OptIn(ExperimentalUuidApi::class) constructor(
    val loading: Boolean = false,
    val error: String? = null,
    val data: AuthModel? = null,
    val sentKey: String = Uuid.random().toHexString()
)
