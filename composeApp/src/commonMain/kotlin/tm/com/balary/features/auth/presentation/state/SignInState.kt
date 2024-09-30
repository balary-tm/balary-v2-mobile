package tm.com.balary.features.auth.presentation.state

import tm.com.balary.features.auth.domain.model.AuthModel

data class SignInState(
    val loading: Boolean = false,
    val error: String? = null,
    val data: AuthModel? = null
)
