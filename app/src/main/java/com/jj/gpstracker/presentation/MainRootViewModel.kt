package com.jj.gpstracker.presentation

import androidx.lifecycle.ViewModel
import com.jj.gpstracker.data.ad.GetMainAdUnitId
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@Suppress("EmptyMethod")
class MainRootViewModel(
    getMainAdUnitId: GetMainAdUnitId,
) : ViewModel() {

    private val _viewState =
        MutableStateFlow(
            MainRootViewState(
                adMainUnitId = getMainAdUnitId(),
            )
        )
    val viewState = _viewState.asStateFlow()

    fun onAdClicked() {
        /* no-op */
    }
}