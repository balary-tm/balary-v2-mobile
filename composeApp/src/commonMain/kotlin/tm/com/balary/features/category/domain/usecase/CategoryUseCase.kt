package tm.com.balary.features.category.domain.usecase

import kotlinx.coroutines.flow.Flow
import tm.com.balary.core.Resource
import tm.com.balary.features.category.domain.model.CategoryModel
import tm.com.balary.features.category.domain.model.ChildCategoryModel
import tm.com.balary.features.category.domain.repository.CategoryRepository

class CategoryUseCase(
    private val repository: CategoryRepository
) {
    suspend fun getCategories(): Flow<Resource<List<CategoryModel>>> {
        return repository.getCategories()
    }
    suspend fun getChildCategories(id: String): Flow<Resource<ChildCategoryModel>> {
        return repository.getChildCategories(id)
    }
}