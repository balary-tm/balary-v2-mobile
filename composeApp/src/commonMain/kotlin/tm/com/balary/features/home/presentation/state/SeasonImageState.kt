package tm.com.balary.features.home.presentation.state

import tm.com.balary.features.home.domain.model.SeasonModel

data class SeasonImageState(
    val loading: Boolean = true,
    val error: String? = null,
    val seasonImage: SeasonModel? = null
)
