package com.jj.gpstracker.presentation.ui.main.model

data class MainScreenViewState(
    val loading: Boolean = false,
    val text: String = "",
    val data: String = "",
    val status: String = "",
    val requiredPermissions: List<String> = listOf(),
    val installedFromValidSource: Boolean? = null,
)
