package tm.com.balary.features.home.presentation.ui.banner

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.scan
import balary.composeapp.generated.resources.search
import org.jetbrains.compose.resources.painterResource

@Composable
fun SearchInput(
    modifier: Modifier = Modifier,
    showScan: Boolean = true,
    onSearch: (String) -> Unit,
) {
    TextField(
        modifier = modifier.height(50.dp),
        value = "",
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            focusedContainerColor = MaterialTheme.colorScheme.background,
            errorContainerColor = MaterialTheme.colorScheme.background,
            disabledContainerColor = MaterialTheme.colorScheme.background,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch("")
            }
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(Res.drawable.search),
                tint = MaterialTheme.colorScheme.outline,
                contentDescription = "search"
            )
        },
        trailingIcon = {
            if(showScan) {
                Icon(
                    painter = painterResource(Res.drawable.scan),
                    tint = MaterialTheme.colorScheme.outline,
                    contentDescription = "scan"
                )
            }
        },
        onValueChange = {

        },
        placeholder = {
            Text(
                "Gözleg", style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.outline
            )
        }
    )
}