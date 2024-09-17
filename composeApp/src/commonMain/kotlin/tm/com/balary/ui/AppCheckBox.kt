package tm.com.balary.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppCheckBox(
    modifier: Modifier = Modifier,
    checked: Boolean = false,
    onChange: (Boolean) -> Unit = {},
) {
    val checkModifier = if(checked) {
        Modifier.background(
            color = MaterialTheme.colorScheme.primary,
            shape = CircleShape
        )
    } else {
        Modifier.border(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline,
            shape = CircleShape
        )
    }
    Box(
        modifier.size(20.dp).clip(CircleShape).clickable {
            onChange(checked.not())
        }.then(checkModifier),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(checked) {
            Icon(
                Icons.Default.Check,
                tint = MaterialTheme.colorScheme.background,
                contentDescription = "check",
                modifier = Modifier.size(15.dp)
            )
        }
    }
}