package tm.com.balary.features.home.presentation.ui.banner

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import balary.composeapp.generated.resources.Res
import balary.composeapp.generated.resources.topbar_bg
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.voyager.koin.koinNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinNavViewModel
import org.koin.compose.viewmodel.koinViewModel
import tm.com.balary.features.auth.presentation.ui.AuthScreen
import tm.com.balary.features.home.presentation.viewmodel.HomeViewModel
import tm.com.balary.ui.ImageLoader

@Composable
fun HomeToolbar(
    modifier: Modifier = Modifier
) {
    val navigator = LocalNavigator.currentOrThrow
    val shape = RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp)

    val homeViewModel: HomeViewModel = koinViewModel()
    val seasonImageState = homeViewModel.seasonImage.collectAsState()

    val strings = LocalStrings.current

    LaunchedEffect(true) {
        homeViewModel.initSeasonImage()
    }

    Box(
        modifier = modifier.background(Color.Transparent).background(
            color = MaterialTheme.colorScheme.tertiary,
            shape = shape
        ).clip(shape).height(260.dp)
    ) {
        ImageLoader(
            url = if(seasonImageState.value.seasonImage!=null) seasonImageState.value.seasonImage!!.path else "",
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
            contentScale = ContentScale.FillWidth,
            placeholder = painterResource(Res.drawable.topbar_bg)
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
                    text = strings.signIn,
                    onClick = {
                        navigator.push(AuthScreen())
                        // navigation changed
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