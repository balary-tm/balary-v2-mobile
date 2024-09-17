package tm.com.balary.features.home.domain.repository

import kotlinx.coroutines.flow.Flow
import tm.com.balary.core.Resource
import tm.com.balary.features.home.domain.model.BannerModel
import tm.com.balary.features.home.domain.model.SeasonModel

interface HomeRepository {
    suspend fun getBanners(): Flow<Resource<List<BannerModel>>>
    suspend fun getSeasonImage(): Flow<Resource<SeasonModel>>
}