package tm.com.balary.features.product.presentation.ui.review

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.voyager.core.screen.Screen
import tm.com.balary.features.auth.presentation.ui.BackScreen

class MyComment : Screen {
    @Composable
    override fun Content() {

    }
}

@Composable
fun MyComments(modifier: Modifier = Modifier, navHostController: NavHostController) {
    val strings = LocalStrings.current
    BackScreen(
        modifier = modifier.fillMaxSize(),
        title = strings.myComments,
        spacing = 0.dp,
        navHostController = navHostController
    ) {
        LazyColumn(Modifier.fillMaxSize().background(
            color = MaterialTheme.colorScheme.surface
        ), verticalArrangement = Arrangement.spacedBy(8.dp), contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 12.dp
        )
        ) {
            items(20) {
                MiniReview(
                    Modifier.fillMaxWidth(),
                    username = "Jahan",
                    stars = 3.0,
                    image = "",
                    date = "08.06.2024",
                    review = "Öran gowy haryt.",
                    isOwn = true
                )
            }
        }
    }
}