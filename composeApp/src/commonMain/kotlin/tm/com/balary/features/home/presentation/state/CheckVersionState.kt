package tm.com.balary.features.home.presentation.state

import tm.com.balary.features.home.domain.model.VersionCheckModel

data class CheckVersionState(
    val loading: Boolean = true,
    val error: String? = null,
    val versions: List<VersionCheckModel>? = null
)
