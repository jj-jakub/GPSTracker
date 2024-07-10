package com.jj.gpstracker.data.config

import com.jj.gpstracker.BuildConfig

class VersionTextProvider {

    fun getAboutVersionText(): String = "Revision: ${BuildConfig.currentRevisionHash}, " +
            "Build number: ${BuildConfig.ciBuildNumber}, Version: ${BuildConfig.VERSION_NAME}"
}