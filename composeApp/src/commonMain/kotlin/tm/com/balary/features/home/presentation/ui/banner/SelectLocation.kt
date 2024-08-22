package tm.com.balary.features.home.presentation.ui.banner

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.location
import org.jetbrains.compose.resources.painterResource

@Composable
fun SelectLocation(
    modifier: Modifier = Modifier
) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                painter = painterResource(Res.drawable.location),
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = "location"
            )
            Text(
                "Aşgabat",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
            Icon(
                Icons.Default.ArrowDropDown,
                contentDescription = "arrow",
                tint = Color.Black
            )
        }

}