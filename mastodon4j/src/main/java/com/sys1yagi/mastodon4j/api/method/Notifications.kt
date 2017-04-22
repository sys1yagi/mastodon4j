package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Notification
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.extension.emptyRequestBody
import com.sys1yagi.mastodon4j.extension.genericType

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#notifications
 */
class Notifications(val client: MastodonClient) {
    // GET /api/v1/notifications
    fun getNotifications(range: Range): List<Notification> {
        val response = client.get(
                "notifications",
                range.toParameter()
        )
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson<List<Notification>>(
                    body,
                    genericType<List<Notification>>()
            )
        } else {
            throw Mastodon4jRequestException(response.message())
        }
    }

    // GET /api/v1/notifications/:id
    fun getNotification(id: Long): Notification {
        val response = client.get("notifications/$id")
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    Notification::class.java
            )
        } else {
            throw Mastodon4jRequestException(response.message())
        }
    }

    //  POST /api/v1/notifications/clear
    fun clearNotifications() {
        val response = client.post("notifications/clear",
                emptyRequestBody()
        )
        if (!response.isSuccessful) {
            throw Mastodon4jRequestException(response.message())
        }
    }
}
