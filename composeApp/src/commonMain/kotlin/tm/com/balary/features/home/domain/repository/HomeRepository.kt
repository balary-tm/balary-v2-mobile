package tm.com.balary.features.home.domain.repository

import kotlinx.coroutines.flow.Flow
import tm.com.balary.core.Resource
import tm.com.balary.features.home.domain.model.BannerModel
import tm.com.balary.features.home.domain.model.HomeBrandModel
import tm.com.balary.features.home.domain.model.HomeCategory
import tm.com.balary.features.home.domain.model.HomeProductModel
import tm.com.balary.features.home.domain.model.SeasonModel
import tm.com.balary.features.home.domain.model.VersionCheckModel

interface HomeRepository {
    suspend fun getBanners(): Flow<Resource<List<BannerModel>>>
    suspend fun getSeasonImage(): Flow<Resource<SeasonModel>>
    suspend fun getHomeProducts(): Flow<Resource<HomeProductModel>>
    suspend fun getCategories(): Flow<Resource<List<HomeCategory>>>
    suspend fun getBrands(): Flow<Resource<List<HomeBrandModel>>>
    suspend fun checkVersion(device: String, currentVersion: String): Flow<Resource<List<VersionCheckModel>>>
}