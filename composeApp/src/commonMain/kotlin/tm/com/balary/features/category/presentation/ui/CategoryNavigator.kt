package tm.com.balary.features.category.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tm.com.balary.features.category.presentation.ui.subcategory.SubCategory
import tm.com.balary.features.home.presentation.ui.commonRoutes
import tm.com.balary.router.CategoryScreen
import tm.com.balary.router.SubCategoryScreen

@Composable
fun CategoryNavigator() {
    val navHostController = rememberNavController()

    NavHost(navHostController, startDestination = CategoryScreen) {
        composable<CategoryScreen> {
            Category(navHostController)
        }

        commonRoutes(navHostController)
    }
}