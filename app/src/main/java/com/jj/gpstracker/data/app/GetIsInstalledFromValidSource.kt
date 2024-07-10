package com.jj.gpstracker.data.app

import com.jj.gpstracker.domain.app.AppInfoRepository

class GetIsInstalledFromValidSource(
    private val appInfoRepository: AppInfoRepository,
) {
    suspend operator fun invoke() = appInfoRepository.installedFromValidSource()
}