package tm.com.balary.features.home.presentation.ui.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.bag
import balary.composeapp.generated.resources.banner
import balary.composeapp.generated.resources.placeholder
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.ui.ImageLoader

@Composable
fun HomeCategory(modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(10) {
            HomeCategoryItem(modifier)
        }
    }
}

@Composable
fun HomeCategoryItem(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        ImageLoader(
            modifier = Modifier.size(40.dp).clip(
                RoundedCornerShape(10.dp)
            ),
            url = "",
            placeholder = painterResource(Res.drawable.banner),
            contentScale = ContentScale.Crop
        )
        Text(
            "Market",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 12.sp
            ),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}