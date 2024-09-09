package tm.com.balary.features.onboarding.presentation.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.launch

class OnBoardingScreen : Screen {
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val state = rememberPagerState { 3 }
        val coroutine = rememberCoroutineScope()
        Box(Modifier.fillMaxSize().background(
            color = MaterialTheme.colorScheme.background
        )) {
            HorizontalPager(
                state = state
            ) { index->
                when (index) {
                    0 -> {
                        FirstScreen(
                            onNext = {
                                coroutine.launch {
                                    state.scrollToPage(1)
                                }
                            }
                        )
                    }
                    1 -> {
                        SecondScreen(
                            onNext = {
                                coroutine.launch {
                                    state.scrollToPage(2)
                                }
                            }
                        )
                    }
                    else -> {
                        ThirdScreen()
                    }
                }
            }
        }
    }
}