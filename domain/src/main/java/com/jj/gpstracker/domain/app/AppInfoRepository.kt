package com.jj.gpstracker.domain.app

interface AppInfoRepository {
    suspend fun installedFromValidSource(): Boolean
}