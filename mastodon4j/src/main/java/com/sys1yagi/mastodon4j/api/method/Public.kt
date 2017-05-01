package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.MastodonClient
import com.sys1yagi.mastodon4j.api.entity.Instance
import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException

class Public(private val client: MastodonClient) {
    /**
     * GET /api/v1/instance
     * @see https://github.com/tootsuite/documentation/blob/master/Using-the-API/API.md#instances
     */
    @Throws(Mastodon4jRequestException::class)
    fun getInstance(): Instance {
        val response = client.get("instance")
        if (response.isSuccessful) {
            val body = response.body().string()
            return client.getSerializer().fromJson(
                    body,
                    Instance::class.java
            )
        } else {
            throw Mastodon4jRequestException(response)
        }
    }
}