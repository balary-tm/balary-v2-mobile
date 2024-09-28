package tm.com.balary.features.category.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import tm.com.balary.core.Constant
import tm.com.balary.core.Resource
import tm.com.balary.features.category.data.entity.ParentCategoryEntityItem
import tm.com.balary.features.category.data.entity.SubCategoryEntity
import tm.com.balary.features.category.domain.model.CategoryModel
import tm.com.balary.features.category.domain.model.ChildCategoryModel
import tm.com.balary.features.category.domain.repository.CategoryRepository

class CategoryRepositoryImpl(
    private val httpClient: HttpClient
) : CategoryRepository {
    override suspend fun getCategories(): Flow<Resource<List<CategoryModel>>> = flow {
        emit(Resource.Loading())
        delay(3000L)
        try {
            val response = httpClient.get("${Constant.BASE_URL}/categories/tree")
            if(response.status.value in 200..300) {
                val categories = response.body<List<ParentCategoryEntityItem>>()
                emit(Resource.Success(categories.map { it.toUIEntity() }))
            } else {
                emit(Resource.Error(response.status.description, response.status.value))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(Resource.Error(ex.message))
        }
    }

    override suspend fun getChildCategories(id: String): Flow<Resource<ChildCategoryModel>> = flow {
        emit(Resource.Loading())
        delay(3000L)
        try {
            val response = httpClient.get("${Constant.BASE_URL}/categories/$id")
            if(response.status.value in 200..300) {
                val subCategory = response.body<SubCategoryEntity>()
                val result = ChildCategoryModel(
                    categories = subCategory.children.map { it.toUIEntity() },
                    parent = subCategory.category.toUIEntity()
                )
                emit(Resource.Success(result))
            } else {
                emit(Resource.Error(response.status.description, response.status.value))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(Resource.Error(ex.message))
        }
    }
}