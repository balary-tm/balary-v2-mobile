package tm.com.balary.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun<T> VerticalGrid(
    modifier: Modifier = Modifier,
    gridCount: Int = 2,
    items: List<T>,
    content: @Composable (T) -> Unit
) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        repeat(items.size / gridCount) { index->
            // 0 [0,1]
            // 1 [2,3]
            // 2 [4,5]
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(gridCount) { grid->
                    Box(Modifier.weight(1f)) {
                        content(items[index + grid])
                    }
                }
            }
        }

    }
}