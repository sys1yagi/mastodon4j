package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.MastodonRequest
import com.sys1yagi.mastodon4j.api.Pageable
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Notification
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.extension.emptyRequestBody
import com.sys1yagi.mastodon4j.extension.fromJson
import com.sys1yagi.mastodon4j.extension.genericType
import com.sys1yagi.mastodon4j.extension.toPageable

/**
 * See more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#notifications
 */
class Notifications(private val client: MastodonClient) {
    // GET /api/v1/notifications
    @JvmOverloads
    fun getNotifications(range: Range = Range(), excludeTypes: List<Notification.Type>? = null): MastodonRequest<Pageable<Notification>> {
        val parameter = range.toParameter()
        if (excludeTypes != null) {
            parameter.append("exclude_types", excludeTypes.map { it.value })
        }
        return MastodonRequest<Pageable<Notification>>(
                {
                    client.get(
                            "notifications",
                            parameter
                    )
                },
                {
                    client.getSerializer().fromJson(it, Notification::class.java)
                }
        ).toPageable()
    }

    // GET /api/v1/notifications/:id
    fun getNotification(id: Long): MastodonRequest<Notification> {
        return MastodonRequest<Notification>(
                {
                    client.get("notifications/$id")
                },
                {
                    client.getSerializer().fromJson(it, Notification::class.java)
                }
        )
    }

    //  POST /api/v1/notifications/clear
    @Throws(Mastodon4jRequestException::class)
    fun clearNotifications() {
        val response = client.post("notifications/clear",
                emptyRequestBody()
        )
        if (!response.isSuccessful) {
            throw Mastodon4jRequestException(response)
        }
    }
}
