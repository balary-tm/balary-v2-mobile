package tm.com.balary.features.home.presentation.viewmodel

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeToolbarViewModel: ScreenModel {
    private val _toolbarHeight = MutableStateFlow(200.dp)
    val toolbarHeight = _toolbarHeight.asStateFlow()

    fun changeToolBarHeight(height: Dp) {
        _toolbarHeight.update { height }
    }

    private val _actionHeight = MutableStateFlow(50.dp)
    val actionHeight = _actionHeight.asStateFlow()

    fun changeActionHeight(height: Dp) {
        _actionHeight.update { height }
    }
}