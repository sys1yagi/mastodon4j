package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.Parameter
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.extension.genericType

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#timelines
 */
class Timelines(val client: MastodonClient) {

    fun getHome(maxId: Long? = null, sinceId: Long? = null, limit: Int = 20): List<Status> {
        val response = client.get(
                "timelines/getHome",
                Parameter().apply {
                    maxId?.let { append("max_id", it) }
                    sinceId?.let { append("since_id", it) }
                    append("limit", limit)
                }
        )
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson<List<Status>>(
                    body,
                    genericType<List<Status>>()
            )
        } else {
            throw Mastodon4jRequestException(response.message())
        }
    }

    fun getPublic(maxId: Long? = null, sinceId: Long? = null, limit: Int = 20): List<Status> {
        val response = client.get(
                "timelines/getPublic",
                Parameter().apply {
                    maxId?.let { append("max_id", it) }
                    sinceId?.let { append("since_id", it) }
                    append("limit", limit)
                }
        )
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson<List<Status>>(
                    body,
                    genericType<List<Status>>()
            )
        } else {
            throw Mastodon4jRequestException(response.message())
        }
    }

    fun getTag(tag: String, maxId: Long? = null, sinceId: Long? = null, limit: Int = 20): List<Status> {
        val response = client.get(
                "timelines/getTag/$tag",
                Parameter().apply {
                    maxId?.let { append("max_id", it) }
                    sinceId?.let { append("since_id", it) }
                    append("limit", limit)
                }
        )
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson<List<Status>>(
                    body,
                    genericType<List<Status>>()
            )
        } else {
            throw Mastodon4jRequestException(response.message())
        }
    }
}
