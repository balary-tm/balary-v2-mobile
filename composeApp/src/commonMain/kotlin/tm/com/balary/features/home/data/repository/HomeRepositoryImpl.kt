package tm.com.balary.features.home.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.parameters
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList
import tm.com.balary.core.Constant
import tm.com.balary.core.Resource
import tm.com.balary.database.AppDatabase
import tm.com.balary.features.home.data.datasource.local.BannerLocalEntity
import tm.com.balary.features.home.data.datasource.local.ProductLocalType
import tm.com.balary.features.home.data.entity.BannerEntity
import tm.com.balary.features.home.data.entity.SeasonImageEntity
import tm.com.balary.features.home.data.entity.ads.AdsType
import tm.com.balary.features.home.data.entity.ads.HomeAds
import tm.com.balary.features.home.data.entity.brand.BrandApiItem
import tm.com.balary.features.home.data.entity.category.HomeCategoryEntityItem
import tm.com.balary.features.home.data.entity.product.HomeProductEntity
import tm.com.balary.features.home.data.entity.version.VersionCheckItem
import tm.com.balary.features.home.domain.model.BannerModel
import tm.com.balary.features.home.domain.model.HomeAdsModel
import tm.com.balary.features.home.domain.model.HomeBrandModel
import tm.com.balary.features.home.domain.model.HomeCategory
import tm.com.balary.features.home.domain.model.HomeProductModel
import tm.com.balary.features.home.domain.model.SeasonModel
import tm.com.balary.features.home.domain.model.VersionCheckModel
import tm.com.balary.features.home.domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val httpClient: HttpClient,
    private val appDatabase: AppDatabase
) : HomeRepository {
    override suspend fun getBanners(): Flow<Resource<List<BannerModel>>> = flow {
        emit(Resource.Loading())
        val dao = appDatabase.getBannerDao()
        val list = dao.getBanners()
        if (list.isNotEmpty()) {
            emit(Resource.Success(list.map { it.toUIEntity() }))
        }
        delay(2000L)
        try {
            val result = httpClient.get("${Constant.BASE_URL}/home/banners")
            if (result.status.value in 200..300) {
                val banners = result.body<List<BannerEntity>>()
                banners.forEach { item ->
                    dao.insertBanner(item.toCacheEntity())
                }
                emit(Resource.Success(banners.map { it.toUIEntity() }))
            } else {
                if (list.isNotEmpty()) {
                    emit(Resource.Success(list.map { it.toUIEntity() }))
                } else {
                    emit(Resource.Error(result.status.description, result.status.value))
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            if (list.isNotEmpty()) {
                emit(Resource.Success(list.map { it.toUIEntity() }))
            } else {
                emit(Resource.Error(ex.message))
            }
        }
    }

    override suspend fun getSeasonImage(): Flow<Resource<SeasonModel>> = flow {
        emit(Resource.Loading())
        val dao = appDatabase.getSeasonDao()
        val season = dao.getSeason()
        if (season != null) {
            emit(Resource.Success(season.toUIEntity()))
        }
        delay(2000L)
        try {
            val result = httpClient.get("${Constant.BASE_URL}/home/season-img")
            if (result.status.value in 200..300) {
                val image = result.body<SeasonImageEntity>()
                dao.deleteAll()
                dao.insertSeason(image.toCacheEntity())
                emit(Resource.Success(image.toUIEntity()))
            } else {
                if (season != null) {
                    emit(Resource.Success(season.toUIEntity()))
                } else {
                    emit(Resource.Error(result.status.description, result.status.value))
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            if (season != null) {
                emit(Resource.Success(season.toUIEntity()))
            } else {
                emit(Resource.Error(ex.message))
            }
        }
    }

    override suspend fun getHomeProducts(): Flow<Resource<HomeProductModel>> = flow {
        emit(Resource.Loading())
        val dao = appDatabase.getProductDao()
        val discountProducts = dao.getProductsByType(ProductLocalType.DISCOUNT_PRODUCT)
        val newProducts = dao.getProductsByType(ProductLocalType.NEW_PRODUCT)
        val slides = appDatabase.getSlidesDao().getSlides()
        val cache = HomeProductModel(
            discountProducts = discountProducts.map { it.toUIEntity() },
            newProducts = newProducts.map { it.toUIEntity() },
            discountSlides = slides.map { it.toUIEntity() }
        )
        if (discountProducts.isNotEmpty() || newProducts.isNotEmpty()) {
            emit(Resource.Success(cache))
        }

        delay(2000L)
        try {
            val result = httpClient.get("${Constant.BASE_URL}/products/home")
            if (result.status.value in 200..300) {
                val products = result.body<HomeProductEntity>()
                dao.insertProducts(products.discount_products.map {
                    it.toCacheEntity(
                        ProductLocalType.DISCOUNT_PRODUCT
                    )
                })
                dao.insertProducts(products.new_products.map { it.toCacheEntity(ProductLocalType.NEW_PRODUCT) })
                appDatabase.getSlidesDao().deleteAll()
                appDatabase.getSlidesDao()
                    .insertSlides(products.discount_images.map { it.toCacheEntity() })
                val response = HomeProductModel(
                    discountProducts = products.discount_products.map { it.toUIEntity() },
                    newProducts = products.new_products.map { it.toUIEntity() },
                    discountSlides = products.discount_images.map { it.toUIEntity() }
                )
                emit(Resource.Success(response))
            } else {
                if (discountProducts.isNotEmpty() || newProducts.isNotEmpty()) {
                    emit(Resource.Success(cache))
                } else {
                    emit(Resource.Error(result.status.description, result.status.value))
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            if (discountProducts.isNotEmpty() || newProducts.isNotEmpty()) {
                emit(Resource.Success(cache))
            } else {
                emit(Resource.Error(ex.message))
            }
        }
    }

    override suspend fun getCategories(): Flow<Resource<List<HomeCategory>>> = flow {
        emit(Resource.Loading())
        val categoryDao = appDatabase.getHomeCategoryDao()
        val cachedCategory = categoryDao.getCategories()
        val products = appDatabase.getProductDao().getProductsByType(ProductLocalType.HOME_CATEGORY_PRODUCT)
        val list = cachedCategory.map { it.toUIEntity(products.filter { p-> p.categoryId == it.id }) }
        if(list.isNotEmpty()) {
           emit(Resource.Success(list))
        }
        delay(2000L)
        try {
            val result = httpClient.get("${Constant.BASE_URL}/home/categories")
            if (result.status.value in 200..300) {
                val category = result.body<List<HomeCategoryEntityItem>>()
                categoryDao.insert(category.map { it.toCacheEntity() })
                category.forEach { cat->
                    cat.products?.map { it.toCacheEntity(ProductLocalType.HOME_CATEGORY_PRODUCT, cat.id) }
                        ?.let { appDatabase.getProductDao().insertProducts(it) }
                }
                emit(Resource.Success(category.map { it.toUIEntity() }))
            } else {
                if(list.isNotEmpty()) {
                    emit(Resource.Success(list))
                } else {
                    emit(Resource.Error(result.status.description, result.status.value))
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            if(list.isNotEmpty()) {
                emit(Resource.Success(list))
            } else {
                emit(Resource.Error(ex.message))
            }
        }
    }

    override suspend fun getBrands(): Flow<Resource<List<HomeBrandModel>>> = flow {
        emit(Resource.Loading())
        delay(2000L)
        try {
            val result = httpClient.get("${Constant.BASE_URL}/home/brands")
            if (result.status.value in 200..300) {
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
        delay(2000L)
        try {
            val result = httpClient.get("${Constant.BASE_URL}/home/version-info") {
                url {
                    parameters.append("device", device)
                    parameters.append("client-version", currentVersion)
                }
            }
            if (result.status.value in 200..300) {
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

    override suspend fun getAds(): Flow<Resource<Pair<HomeAdsModel?, HomeAdsModel?>>> = flow {
        emit(Resource.Loading())
        delay(2000L)
        try {
            val result = httpClient.get("${Constant.BASE_URL}/home/ads")
            if (result.status.value in 200..300) {
                val ads = result.body<List<HomeAds>>()
                val popupAds = ads.filter { it.getAdsType() == AdsType.POPUP }
                val sheetAds = ads.filter { it.getAdsType() == AdsType.SHEET }
                val popup = if (popupAds.isNotEmpty()) popupAds.last() else null
                val sheet = if (sheetAds.isNotEmpty()) sheetAds.last() else null
                emit(Resource.Success(Pair(popup?.toUIEntity(), sheet?.toUIEntity())))
            } else {
                emit(Resource.Error(result.status.description, result.status.value))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(Resource.Error(ex.message))
        }
    }
}