package tm.com.balary.features.home.domain.usecase

import kotlinx.coroutines.flow.Flow
import tm.com.balary.core.Resource
import tm.com.balary.features.home.domain.model.BannerModel
import tm.com.balary.features.home.domain.model.HomeAdsModel
import tm.com.balary.features.home.domain.model.HomeBrandModel
import tm.com.balary.features.home.domain.model.HomeCategory
import tm.com.balary.features.home.domain.model.HomeProductModel
import tm.com.balary.features.home.domain.model.SeasonModel
import tm.com.balary.features.home.domain.model.VersionCheckModel
import tm.com.balary.features.home.domain.repository.HomeRepository

class HomeUseCase(
    private val repository: HomeRepository
) {
    suspend fun getBanners(): Flow<Resource<List<BannerModel>>> {
        return repository.getBanners()
    }
    suspend fun getSeasonImage(): Flow<Resource<SeasonModel>> {
        return repository.getSeasonImage()
    }
    suspend fun getHomeProducts(): Flow<Resource<HomeProductModel>> {
        return repository.getHomeProducts()
    }
    suspend fun getCategories(): Flow<Resource<List<HomeCategory>>> {
        return repository.getCategories()
    }
    suspend fun getBrands(): Flow<Resource<List<HomeBrandModel>>> {
        return repository.getBrands()
    }
    suspend fun checkVersion(device: String, currentVersion: String): Flow<Resource<List<VersionCheckModel>>> {
        return repository.checkVersion(device, currentVersion)
    }
    suspend fun getAds(): Flow<Resource<Pair<HomeAdsModel?, HomeAdsModel?>>> {
        return repository.getAds()
    }
}