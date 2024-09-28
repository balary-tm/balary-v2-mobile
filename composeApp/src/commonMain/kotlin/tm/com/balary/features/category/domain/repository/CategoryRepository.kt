package tm.com.balary.features.category.domain.repository

import kotlinx.coroutines.flow.Flow
import tm.com.balary.core.Resource
import tm.com.balary.features.category.domain.model.CategoryModel
import tm.com.balary.features.category.domain.model.ChildCategoryModel

interface CategoryRepository {
    suspend fun getCategories(): Flow<Resource<List<CategoryModel>>>
    suspend fun getChildCategories(id: String): Flow<Resource<ChildCategoryModel>>
}