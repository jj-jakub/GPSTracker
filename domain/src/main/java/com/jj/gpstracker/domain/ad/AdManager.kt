package com.jj.gpstracker.domain.ad

interface AdManager {
    fun initAds()
    fun incrementActionsForAd()
    fun showInterstitialAd()
}