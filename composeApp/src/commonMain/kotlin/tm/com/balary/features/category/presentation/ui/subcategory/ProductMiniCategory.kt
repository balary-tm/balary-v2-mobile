package tm.com.balary.features.category.presentation.ui.subcategory

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.fruit
import balary.composeapp.generated.resources.placeholder
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.features.category.domain.model.CategoryModel
import tm.com.balary.locale.translateValue
import tm.com.balary.router.ProductsScreen
import tm.com.balary.ui.ImageLoader
import tm.com.balary.ui.skeleton.SkeletonRounded

@Composable
fun ProductMiniCategorySkeleton(modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(10) {
            SkeletonRounded(Modifier.height(52.dp).width(105.dp), borderRadius = 10.dp)
        }
    }

}

@Composable
fun ProductMiniCategory(
    modifier: Modifier = Modifier,
    model: CategoryModel,
    selected: Boolean = false,
    navHostController: NavHostController = rememberNavController()
) {
    Row(modifier = modifier.clip(RoundedCornerShape(10.dp)).background(
        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
        shape = RoundedCornerShape(10.dp)
    ).border(
        width = 1.4.dp,
        color = if(selected) MaterialTheme.colorScheme.primary else Color.Transparent,
        shape = RoundedCornerShape(10.dp)
    ).clickable {
        navHostController.navigate(
            ProductsScreen(
            model.id.toString(),
            title_ru = model.title_ru?:"",
            title_tm = model.title_tm?:""
        )
        )
    }.padding(vertical = 2.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        ImageLoader(
            modifier = Modifier.size(48.dp),
            url = model.icon_sm?:"",
            placeholder = painterResource(Res.drawable.placeholder)
        )

        Text(
            translateValue(model,"title"),
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.W700
            ),
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}