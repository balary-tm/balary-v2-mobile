package tm.com.balary.features.home.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tm.com.balary.core.Constant
import tm.com.balary.core.Resource
import tm.com.balary.features.home.data.entity.BannerEntity
import tm.com.balary.features.home.data.entity.SeasonImageEntity
import tm.com.balary.features.home.domain.model.BannerModel
import tm.com.balary.features.home.domain.model.SeasonModel
import tm.com.balary.features.home.domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val httpClient: HttpClient
) : HomeRepository {
    override suspend fun getBanners(): Flow<Resource<List<BannerModel>>> = flow {
        emit(Resource.Loading())
        delay(3000L)
        try {
            val result = httpClient.get("${Constant.BASE_URL}/home/banners")
            if (result.status.value in 200..300) {
                val banners = result.body<List<BannerEntity>>()
                emit(Resource.Success(banners.map { it.toUIEntity() }))
            } else {
                emit(Resource.Error(result.status.description, result.status.value))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(Resource.Error(ex.message))
        }
    }

    override suspend fun getSeasonImage(): Flow<Resource<SeasonModel>> = flow {
        emit(Resource.Loading())
        try {
            val result = httpClient.get("${Constant.BASE_URL}/home/season-img")
            if (result.status.value in 200..300) {
                val image = result.body<SeasonImageEntity>()
                emit(Resource.Success(image.toUIEntity()))
            } else {
                emit(Resource.Error(result.status.description, result.status.value))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(Resource.Error(ex.message))
        }
    }
}