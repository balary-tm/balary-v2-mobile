package tm.com.balary.features.product.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.liked_outline
import balary.composeapp.generated.resources.liked_product
import org.jetbrains.compose.resources.painterResource

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    isLiked: Boolean = false
) {
   IconButton(
       modifier = modifier,
       onClick = {

       }
   ) {
       Image(
           painter = painterResource(
               if(isLiked) Res.drawable.liked_product else Res.drawable.liked_outline
           ),
           contentDescription = "favorite"
       )
   }
}