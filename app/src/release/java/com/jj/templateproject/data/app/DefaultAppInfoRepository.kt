package com.jj.gpstracker.data.app

import com.jj.gpstracker.domain.app.AppInfoRepository

class DefaultAppInfoRepository : AppInfoRepository {
    override suspend fun installedFromValidSource(): Boolean = true
}