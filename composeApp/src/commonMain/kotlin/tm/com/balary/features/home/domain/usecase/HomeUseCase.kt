package tm.com.balary.features.home.domain.usecase

import kotlinx.coroutines.flow.Flow
import tm.com.balary.core.Resource
import tm.com.balary.features.home.domain.model.BannerModel
import tm.com.balary.features.home.domain.model.SeasonModel
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
}