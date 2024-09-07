package tm.com.balary.features.profile.presentation.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import tm.com.balary.ui.ImageLoader

@Composable
fun ProfileAvatar(modifier: Modifier = Modifier) {
    ImageLoader(
        modifier = modifier.background(
            color = MaterialTheme.colorScheme.inversePrimary,
            shape = CircleShape
        ).clip(CircleShape).size(60.dp),
        url = "",
        contentScale = ContentScale.Inside
    )
}