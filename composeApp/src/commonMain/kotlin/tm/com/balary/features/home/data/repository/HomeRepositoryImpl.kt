package tm.com.balary.features.home.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.parameters
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tm.com.balary.core.Constant
import tm.com.balary.core.Resource
import tm.com.balary.features.home.data.entity.BannerEntity
import tm.com.balary.features.home.data.entity.SeasonImageEntity
import tm.com.balary.features.home.data.entity.brand.BrandApiItem
import tm.com.balary.features.home.data.entity.category.HomeCategoryEntityItem
import tm.com.balary.features.home.data.entity.product.HomeProductEntity
import tm.com.balary.features.home.data.entity.version.VersionCheckItem
import tm.com.balary.features.home.domain.model.BannerModel
import tm.com.balary.features.home.domain.model.HomeBrandModel
import tm.com.balary.features.home.domain.model.HomeCategory
import tm.com.balary.features.home.domain.model.HomeProductModel
import tm.com.balary.features.home.domain.model.SeasonModel
import tm.com.balary.features.home.domain.model.VersionCheckModel
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

    override suspend fun getHomeProducts(): Flow<Resource<HomeProductModel>> = flow {
        emit(Resource.Loading())
        try {
            val result = httpClient.get("${Constant.BASE_URL}/products/home")
            if(result.status.value in 200..300) {
                val products = result.body<HomeProductEntity>()
                val response = HomeProductModel(
                    discountProducts = products.discount_products.map { it.toUIEntity() },
                    newProducts = products.new_products.map { it.toUIEntity() },
                    discountSlides = products.discount_images.map { it.toUIEntity() }
                )
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error(result.status.description, result.status.value))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(Resource.Error(ex.message))
        }
    }

    override suspend fun getCategories(): Flow<Resource<List<HomeCategory>>> = flow {
        emit(Resource.Loading())
        try {
            val result = httpClient.get("${Constant.BASE_URL}/home/categories")
            if(result.status.value in 200..300) {
                val category = result.body<List<HomeCategoryEntityItem>>()
                emit(Resource.Success(category.map { it.toUIEntity() }))
            } else {
                emit(Resource.Error(result.status.description, result.status.value))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(Resource.Error(ex.message))
        }
    }

    override suspend fun getBrands(): Flow<Resource<List<HomeBrandModel>>> = flow {
        emit(Resource.Loading())
        try {
            val result = httpClient.get("${Constant.BASE_URL}/home/brands")
            if(result.status.value in 200..300) {
                val brand = result.body<List<BrandApiItem>>()
                emit(Resource.Success(brand.map { it.toUIEntity() }))
            } else {
                emit(Resource.Error(result.status.description, result.status.value))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(Resource.Error(ex.message))
        }
    }

    override suspend fun checkVersion(
        device: String,
        currentVersion: String
    ): Flow<Resource<List<VersionCheckModel>>> = flow {
        emit(Resource.Loading())
        try {
            val result = httpClient.get("${Constant.BASE_URL}/home/version-info") {
                url {
                    parameters.append("device", device)
                    parameters.append("client-version", currentVersion)
                }
            }
            if(result.status.value in 200..300) {
                val version = result.body<List<VersionCheckItem>>()
                emit(Resource.Success(version.map { it.toUIEntity() }))
            } else {
                emit(Resource.Error(result.status.description, result.status.value))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(Resource.Error(ex.message))
        }
    }
}