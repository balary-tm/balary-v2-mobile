package tm.com.balary.features.home.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tm.com.balary.features.about.presentation.ui.About
import tm.com.balary.features.address.presentation.ui.AddAddress
import tm.com.balary.features.address.presentation.ui.Address
import tm.com.balary.features.address.presentation.ui.AddressFields
import tm.com.balary.features.category.presentation.ui.subcategory.SubCategory
import tm.com.balary.features.contact.presentation.ui.Chats
import tm.com.balary.features.order.presentation.ui.OrderDetails
import tm.com.balary.features.order.presentation.ui.OrderHistory
import tm.com.balary.features.order.presentation.ui.OrderedProducts
import tm.com.balary.features.payment.presentation.ui.Payments
import tm.com.balary.features.privacy.presentation.ui.Privacy
import tm.com.balary.features.product.presentation.ui.detail.ProductDetail
import tm.com.balary.features.product.presentation.ui.filter.Filter
import tm.com.balary.features.product.presentation.ui.products.ProductList
import tm.com.balary.features.product.presentation.ui.review.MyComments
import tm.com.balary.features.product.presentation.ui.review.ProductReview
import tm.com.balary.features.profile.presentation.ui.edit.EditProfile
import tm.com.balary.router.AboutScreen
import tm.com.balary.router.AddAddressScreen
import tm.com.balary.router.AddressFieldScreen
import tm.com.balary.router.AddressScreen
import tm.com.balary.router.ChatScreen
import tm.com.balary.router.EditProfileScreen
import tm.com.balary.router.FilterScreen
import tm.com.balary.router.HomeScreen
import tm.com.balary.router.MyCommentScreen
import tm.com.balary.router.OrderDetailsScreen
import tm.com.balary.router.OrderHistoryScreen
import tm.com.balary.router.OrderedProductScreen
import tm.com.balary.router.PaymentScreen
import tm.com.balary.router.PrivacyScreen
import tm.com.balary.router.ProductDetailScreen
import tm.com.balary.router.ProductReviewScreen
import tm.com.balary.router.ProductsScreen
import tm.com.balary.router.SubCategoryScreen

@Composable
fun HomeNavigation() {
    val navHostController = rememberNavController()

    NavHost(navHostController, startDestination = HomeScreen) {
        composable<HomeScreen> {
            Home(navHostController)
        }

        commonRoutes(navHostController)
    }
}

fun NavGraphBuilder.commonRoutes(navHostController: NavHostController) {
    composable<SubCategoryScreen> {
        SubCategory(navHostController = navHostController)
    }
    composable<ProductsScreen> {
        ProductList(Modifier.fillMaxSize(), navHostController)
    }
    composable<ProductDetailScreen> {
        ProductDetail(Modifier.fillMaxSize(), navHostController)
    }
    composable<ProductReviewScreen> {
        ProductReview(navHostController = navHostController)
    }

    composable<FilterScreen> {
        Filter(
            navHostController = navHostController
        )
    }

    composable<MyCommentScreen> {
        MyComments(
            navHostController = navHostController
        )
    }

    composable<AddressScreen> {
        Address(
            navHostController = navHostController
        )
    }

    composable<AddAddressScreen> {
        AddAddress(
            navHostController = navHostController
        )
    }

    composable<AddressFieldScreen> {
        AddressFields(
            navHostController = navHostController
        )
    }

    composable<OrderHistoryScreen> {
        OrderHistory(
            navHostController = navHostController
        )
    }

    composable<OrderedProductScreen> {
        OrderedProducts(
            navHostController
        )
    }

    composable<OrderDetailsScreen> {
        OrderDetails(navHostController = navHostController)
    }

    composable<PaymentScreen> {
        Payments(
            navHostController = navHostController
        )
    }

    composable<ChatScreen> {
        Chats(
            navHostController = navHostController
        )
    }

    composable<AboutScreen> {
        About(
            navHostController = navHostController
        )
    }

    composable<PrivacyScreen> {
        Privacy(
            navHostController = navHostController
        )
    }

    composable<EditProfileScreen> {
        EditProfile(
            navHostController = navHostController
        )
    }
}