package com.jj.templateproject.data.app

import com.jj.templateproject.domain.app.AppInfoRepository

class DefaultAppInfoRepository : AppInfoRepository {
    override suspend fun installedFromValidSource(): Boolean = true
}