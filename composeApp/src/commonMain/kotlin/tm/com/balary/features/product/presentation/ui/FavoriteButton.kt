package tm.com.balary.features.product.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.liked_outline
import balary.composeapp.generated.resources.liked_product
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.features.favorite.presentation.viewmodel.FavoriteViewModel
import tm.com.balary.ui.CheckAuthFeature

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    isLiked: Boolean = false,
    productId: String,
    favoriteViewModel: FavoriteViewModel,
    onFavoriteChanged: (Boolean) -> Unit
) {
    CheckAuthFeature(verticalPadding = 30.dp) {
        val loading = rememberSaveable {
            mutableStateOf(false)
        }
        val isFav = rememberSaveable(isLiked) {
            mutableStateOf(isLiked)
        }
        if(loading.value) {
            Box(Modifier.padding(4.dp)) {
                CircularProgressIndicator(Modifier.size(30.dp))
            }
        } else {
            IconButton(
                modifier = modifier,
                onClick = {
                    if(isFav.value) {
                        favoriteViewModel.deleteFavorite(
                            productId,
                            onLoading = {
                                loading.value = true
                            },
                            onError = {
                                loading.value = false
                            },
                            onSuccess = {
                                isFav.value = false
                                onFavoriteChanged(false)
                                loading.value = false
                            }
                        )
                    } else {
                        favoriteViewModel.addFavorite(
                            productId,
                            onLoading = {
                                loading.value = true
                            },
                            onError = {
                                loading.value = false
                            },
                            onSuccess = {
                                isFav.value = true
                                onFavoriteChanged(true)
                                loading.value = false
                            }
                        )
                    }
                }
            ) {
                Image(
                    painter = painterResource(
                        if (isFav.value) Res.drawable.liked_product else Res.drawable.liked_outline
                    ),
                    contentDescription = "favorite"
                )
            }
        }
    }
}