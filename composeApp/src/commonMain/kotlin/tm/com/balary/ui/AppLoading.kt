package tm.com.balary.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AppLoading(
    modifier: Modifier = Modifier,
    indicatorSize: Dp = 35.dp
) {
    Box(modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator(Modifier.size(indicatorSize))
    }
}