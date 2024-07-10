package com.jj.gpstracker.data.ad

import com.jj.gpstracker.BuildConfig

class GetMainAdUnitId {
    operator fun invoke(): String = BuildConfig.adMainBannerViewAdUnitId
}