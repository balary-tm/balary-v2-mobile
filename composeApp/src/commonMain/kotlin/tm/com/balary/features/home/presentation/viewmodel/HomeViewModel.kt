package tm.com.balary.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import tm.com.balary.core.Resource
import tm.com.balary.features.home.domain.usecase.HomeUseCase
import tm.com.balary.features.home.presentation.state.BannerState
import tm.com.balary.features.home.presentation.state.SeasonImageState

class HomeViewModel(
    private val useCase: HomeUseCase
) : ViewModel() {
    private val _bannerState = MutableStateFlow(BannerState())
    val bannerState = _bannerState.asStateFlow()

    private val _seasonImage = MutableStateFlow(SeasonImageState())
    val seasonImage = _seasonImage.asStateFlow()

    fun initBanners() {
        if(_bannerState.value.banners.isNullOrEmpty()) {
            getBanners()
        }
    }

    fun getBanners() {
        viewModelScope.launch {
            useCase.getBanners().onEach {
                when(it) {
                    is Resource.Error -> {
                        _bannerState.value = _bannerState.value.copy(
                            loading = false,
                            error = it.message,
                            banners = it.data
                        )
                    }
                    is Resource.Loading -> {
                        _bannerState.value = _bannerState.value.copy(
                            loading = true,
                            error = it.message,
                            banners = it.data
                        )
                    }
                    is Resource.Success -> {
                        _bannerState.value = _bannerState.value.copy(
                            loading = false,
                            error = it.message,
                            banners = it.data
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    fun initSeasonImage() {
        if(_seasonImage.value.seasonImage==null) {
            getSeasonImage()
        }
    }

    fun getSeasonImage() {
        viewModelScope.launch {
            useCase.getSeasonImage().onEach {
                when(it) {
                    is Resource.Error -> {
                        _seasonImage.value = _seasonImage.value.copy(
                            loading = false,
                            error = it.message,
                            seasonImage = it.data
                        )
                    }
                    is Resource.Loading -> {
                        _seasonImage.value = _seasonImage.value.copy(
                            loading = true,
                            error = it.message,
                            seasonImage = it.data
                        )
                    }
                    is Resource.Success -> {
                        _seasonImage.value = _seasonImage.value.copy(
                            loading = false,
                            error = it.message,
                            seasonImage = it.data
                        )
                    }
                }
            }.launchIn(this)
        }
    }
}