package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.Range
import com.sys1yagi.mastodon4j.api.entity.Status
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.extension.genericType

/**
 * see more https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#timelines
 */
class Timelines(val client: MastodonClient) {

    fun getHome(range: Range = Range()): List<Status> {
        val response = client.get(
                "timelines/home",
                range.toParameter()
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

    fun getPublic(range: Range = Range()): List<Status> {
        val response = client.get(
                "timelines/pubic",
                range.toParameter()
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

    fun getTag(tag: String, range: Range = Range()): List<Status> {
        val response = client.get(
                "timelines/tag/$tag",
                range.toParameter()
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
