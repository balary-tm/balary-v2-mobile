package tm.com.balary.features.home.presentation.state

import tm.com.balary.features.home.domain.model.HomeAdsModel

data class AdsState(
    val loading: Boolean = true,
    val error: String? = null,
    val ads: Pair<HomeAdsModel?, HomeAdsModel?>? = null
)
