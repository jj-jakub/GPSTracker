package com.jj.gpstracker.data.ad

import com.jj.gpstracker.BuildConfig

class GetInterstitialAdUnitId {
    operator fun invoke(): String = BuildConfig.adInterstitialAdUnitId
}