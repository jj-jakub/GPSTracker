package com.jj.templateproject.data.app

import android.content.Context
import com.jj.templateproject.domain.app.AppInfoRepository

class DefaultAppInfoRepository : AppInfoRepository {
    override suspend fun installedFromValidSource(): Boolean = true
}