package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Notification
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.api.method.contract.NotificationsContract
import com.sys1yagi.mastodon4j.extension.emptyRequestBody
import com.sys1yagi.mastodon4j.extension.genericType
import com.sys1yagi.mastodon4j.extension.toPageable

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#notifications
 */
class Notifications(val client: MastodonClient) : NotificationsContract.Public, NotificationsContract.AuthRequired {
    // GET /api/v1/notifications
    @Throws(Mastodon4jRequestException::class)
    override fun getNotifications(range: Range): Pageable<Notification> {
        val response = client.get(
                "notifications",
                range.toParameter()
        )
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson<List<Notification>>(
                    body,
                    genericType<List<Notification>>()
            ).toPageable(response)
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    // GET /api/v1/notifications/:id
    @Throws(Mastodon4jRequestException::class)
    override fun getNotification(id: Long): Notification {
        val response = client.get("notifications/$id")
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    Notification::class.java
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }

    //  POST /api/v1/notifications/clear
    @Throws(Mastodon4jRequestException::class)
    override fun clearNotifications() {
        val response = client.post("notifications/clear",
                emptyRequestBody()
        )
        if (!response.isSuccessful) {
            throw Mastodon4jRequestException(response)
        }
    }
}
