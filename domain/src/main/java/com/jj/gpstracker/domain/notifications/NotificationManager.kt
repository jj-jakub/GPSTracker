package com.jj.gpstracker.domain.notifications

import android.content.Intent

interface NotificationManager {
    fun showPushNotification(
        title: String,
        body: String,
        intent: Intent,
    )
}