package tm.com.balary.features.home.presentation.ui.banner

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.topbar_bg
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.painterResource
import tm.com.balary.features.auth.presentation.ui.AuthScreen
import tm.com.balary.state.LocalDarkMode

@Composable
fun HomeToolbar(
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp)
    val navigator = LocalNavigator.currentOrThrow



    Box(
        modifier = modifier.background(Color.Transparent).background(
            color = MaterialTheme.colorScheme.tertiary,
            shape = shape
        ).clip(shape).height(260.dp)
    ) {
        Image(
            painter = painterResource(Res.drawable.topbar_bg),
            contentDescription = "toolbar",
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
            contentScale = ContentScale.FillWidth
        )
        Column(Modifier.fillMaxWidth().padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SelectLocation(
                    modifier = Modifier
                )
                LogoText(modifier = Modifier.weight(1f))
                BlackButton(
                    text = "Ulgama gir",
                    onClick = {
                        navigator.push(AuthScreen())
                    }
                )
            }
            Spacer(Modifier.height(6.dp))
            SearchInput(
                modifier = Modifier.fillMaxWidth(),
                onSearch = { query ->

                }
            )
        }


    }


}