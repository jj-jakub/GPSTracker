package com.jj.gpstracker.presentation.ui.main

import android.Manifest
import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jj.gpstracker.domain.google.GetGoogleDataUseCase
import com.jj.gpstracker.domain.google.GetGoogleStatusUseCase
import com.jj.gpstracker.data.app.GetIsInstalledFromValidSource
import com.jj.gpstracker.data.config.VersionTextProvider
import com.jj.gpstracker.domain.BaseResult
import com.jj.gpstracker.domain.ad.AdManager
import com.jj.gpstracker.presentation.ui.main.model.MainScreenNavigation
import com.jj.gpstracker.presentation.ui.main.model.MainScreenViewState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainScreenViewModel(
    versionTextProvider: VersionTextProvider,
    adManager: AdManager,
    private val getGoogleStatusUseCase: GetGoogleStatusUseCase,
    private val getGoogleDataUseCase: GetGoogleDataUseCase,
    private val getIsInstalledFromValidSource: GetIsInstalledFromValidSource,
) : ViewModel() {

    private val _viewState = MutableStateFlow(
        MainScreenViewState(
            loading = true,
            requiredPermissions = getRequiredPermissions(),
        )
    )
    val viewState = _viewState.asStateFlow()

    private val _navigation = MutableSharedFlow<MainScreenNavigation>()
    val navigation = _navigation.asSharedFlow()

    init {
        _viewState.value = viewState.value.copy(
            text = versionTextProvider.getAboutVersionText(),
        )

        fetchGoogleData()
        fetchInstallationValidity()
        adManager.incrementActionsForAd()
    }

    private fun fetchGoogleData() {
        viewModelScope.launch {
            val status = when (val result = getGoogleStatusUseCase.invoke()) {
                is BaseResult.Error -> result.error.message
                is BaseResult.Success -> "Ok"
            }

            _viewState.value = viewState.value.copy(
                status = status,
            )

            val data = when (val result = getGoogleDataUseCase.invoke()) {
                is BaseResult.Error -> "Error"
                is BaseResult.Success -> result.data
            }

            _viewState.value = viewState.value.copy(
                data = data,
                loading = false,
            )
        }
    }

    private fun fetchInstallationValidity() {
        viewModelScope.launch {
            _viewState.value = viewState.value.copy(
                installedFromValidSource = getIsInstalledFromValidSource()
            )
        }
    }

    fun navigateWithoutOptionalArgs() {
        navigate(
            MainScreenNavigation.SecondaryScreen(
                text = "First text1",
                secondaryText = null,
                tertiaryText = null,
            )
        )
    }

    fun navigateWithFirstOptionalArg() {
        navigate(
            MainScreenNavigation.SecondaryScreen(
                text = "First text1",
                secondaryText = "Secondary text2",
                tertiaryText = null,
            )
        )
    }

    fun navigateWithSecondOptionalArg() {
        navigate(
            MainScreenNavigation.SecondaryScreen(
                text = "First text1",
                secondaryText = null,
                tertiaryText = "Tertiary text3",
            )
        )
    }

    fun navigateWithAllOptionalArgs() {
        navigate(
            MainScreenNavigation.SecondaryScreen(
                text = "First text1",
                secondaryText = "Secondary text2",
                tertiaryText = "Tertiary text3",
            )
        )
    }

    private fun navigate(mainScreenNavigation: MainScreenNavigation) {
        viewModelScope.launch {
            _navigation.emit(mainScreenNavigation)
        }
    }

    private fun getRequiredPermissions() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            listOf(Manifest.permission.POST_NOTIFICATIONS)
        } else {
            emptyList()
        }
}