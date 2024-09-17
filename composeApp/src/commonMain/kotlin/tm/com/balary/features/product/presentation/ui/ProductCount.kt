package tm.com.balary.features.product.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.comment
import balary.composeapp.generated.resources.star
import cafe.adriel.lyricist.LocalStrings
import org.jetbrains.compose.resources.painterResource

@Composable
fun ProductCount(
    modifier: Modifier = Modifier,
    star: Double,
    commentCount: Int
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        Image(
            painter = painterResource(Res.drawable.star),
            contentDescription = "star",
            modifier = Modifier.size(16.dp)
        )
        Text(
            "$star", style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.W700,
                fontSize = 10.sp
            ),
            color = MaterialTheme.colorScheme.onSurface
        )

        Image(
            painter = painterResource(Res.drawable.comment),
            contentDescription = "star",
            modifier = Modifier.size(16.dp)
        )
        Text(
            "$commentCount ${LocalStrings.current.comment}", style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.W700,
                fontSize = 10.sp
            ),
            color = MaterialTheme.colorScheme.outline
        )

    }
}