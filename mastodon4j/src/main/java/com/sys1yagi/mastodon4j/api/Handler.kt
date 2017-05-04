package com.sys1yagi.mastodon4j.api

import com.sys1yagi.mastodon4j.api.entity.Notification
import com.sys1yagi.mastodon4j.api.entity.Status


interface Handler {

    fun onStatus(status: Status)

    //ignore if public streaming
    fun onNotification(notification: Notification)

    //ignore if public streaming
    fun onDelete(id: Long)
}