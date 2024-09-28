package tm.com.balary.router

import kotlinx.serialization.Serializable

@Serializable
data class ProductDetailScreen(
    val productId: String
)

@Serializable
data class ProductsScreen(
    val categoryId: String? = null,
    val title_tm: String,
    val title_ru: String
)

@Serializable
object FilterScreen

@Serializable
object HomeScreen

@Serializable
data class SubCategoryScreen(
    val parentId: String
)

@Serializable
object CategoryScreen

@Serializable
object ProductReviewScreen

@Serializable
object MyCommentScreen

@Serializable
object BasketScreen

@Serializable
object BasketDetailScreen

@Serializable
object FavoriteScreen

@Serializable
object ProfileScreen

@Serializable
object AddressScreen

@Serializable
object AddAddressScreen

@Serializable
object AddressFieldScreen

@Serializable
object OrderHistoryScreen

@Serializable
object OrderedProductScreen

@Serializable
object OrderDetailsScreen

@Serializable
object PaymentScreen

@Serializable
object ChatScreen

@Serializable
object AboutScreen

@Serializable
object PrivacyScreen

@Serializable
object EditProfileScreen


class Router {
    companion object {
        const val HOME: UShort = 1U
        const val HOME_ROUTE: String = "home"
        const val CATEGORY: UShort = 2U
        const val CATEGORY_ROUTE: String = "category"
        const val BASKET: UShort = 3U
        const val BASKET_ROUTE: String = "basket"
        const val FAVORITE: UShort = 4U
        const val FAVORITE_ROUTE: String = "favorite"
        const val PROFILE: UShort = 5U
        const val PROFILE_ROUTE: String = "profile"


    }



}