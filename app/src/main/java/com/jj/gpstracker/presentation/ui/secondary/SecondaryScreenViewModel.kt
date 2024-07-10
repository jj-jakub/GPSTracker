package com.jj.gpstracker.presentation.ui.secondary

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.jj.gpstracker.framework.navigation.model.Route
import com.jj.gpstracker.framework.navigation.model.getArgument
import com.jj.gpstracker.presentation.ui.secondary.model.SecondaryScreenViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SecondaryScreenViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val name = savedStateHandle.getArgument(Route.SecondaryScreen::text) ?: ""
    private val secondaryName =
        savedStateHandle.getArgument(Route.SecondaryScreen::textSecondary) ?: ""
    private val tertiaryName =
        savedStateHandle.getArgument(Route.SecondaryScreen::textTertiary) ?: ""

    private val _viewState = MutableStateFlow(
        SecondaryScreenViewState(
            text = name,
            secondaryText = secondaryName,
            tertiaryText = tertiaryName,
        )
    )
    val viewState = _viewState.asStateFlow()
}