package tm.com.balary.features.category.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.fruit
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.ui.ImageLoader

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    containerColor: Color,
    image: String,
    title: String
) {
    val shape = RoundedCornerShape(30.dp)
    Box(modifier = modifier.height(185.dp), contentAlignment = Alignment.Center) {
        Box(Modifier.align(Alignment.BottomCenter).fillMaxWidth().fillMaxHeight(0.8f).background(
            color = containerColor,
            shape = shape
        ).clip(shape).clickable {
            onClick()
        })
        Column(modifier = Modifier.fillMaxSize(0.9f), horizontalAlignment = Alignment.CenterHorizontally) {
            ImageLoader(
                modifier = Modifier.size(115.dp),
                url = image,
                placeholder = painterResource(Res.drawable.fruit)
            )
            Text(
                title,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.W700
                ),
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}