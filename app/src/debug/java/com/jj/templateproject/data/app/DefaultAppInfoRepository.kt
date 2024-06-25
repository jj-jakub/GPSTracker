package com.jj.templateproject.data.app

import android.content.Context
import com.jj.templateproject.domain.app.AppInfoRepository

@Suppress("unused")
class DefaultAppInfoRepository(
    private val context: Context,
) : AppInfoRepository {
    override suspend fun installedFromValidSource(): Boolean = true
}